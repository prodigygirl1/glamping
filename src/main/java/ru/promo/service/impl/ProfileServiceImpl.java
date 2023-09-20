package ru.promo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.config.JwtProvider;
import ru.promo.config.property.ProfilePatternProperties;
import ru.promo.domain.AccessResponse;
import ru.promo.domain.CreateProfileRequest;
import ru.promo.domain.entity.ProfileEntity;
import ru.promo.domain.entity.RoleEnum;
import ru.promo.repository.ProfileRepository;
import ru.promo.repository.RoleRepository;
import ru.promo.service.ProfileService;

import java.util.UUID;

import static ru.promo.domain.entity.RoleEnum.getRoleName;
import static ru.promo.util.exception.BadRequestException.invalidMessage;
import static ru.promo.util.exception.ResourceNotFoundException.notFound;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;
    private final ProfilePatternProperties properties;

    @Override
    @Transactional
    public ProfileEntity createProfile(CreateProfileRequest request) {
        return createProfile(request, getRoleName(RoleEnum.USER));
    }

    @Override
    public ProfileEntity createProfile(CreateProfileRequest request, String roleName) {
        if (!properties.getEmail().matcher(request.getEmail()).matches()) {
            throw invalidMessage("Указан некорректный формат почты, повторите попытку");
        }
        if (findByEmail(request.getEmail()) != null) {
            throw invalidMessage("Указанный email уже используется", request.getEmail());
        }
        if (!properties.getPhoneNumber().matcher(request.getPhoneNumber()).matches()) {
            throw invalidMessage("Указан некорректный формат номера телефона, повторите попытку");
        }
        var role = roleRepository.findByName(roleName);
        if (role.isEmpty()) {
            throw invalidMessage("Произошла ошибка, попробуйте позже");
        }
        var profile = ProfileEntity.builder()
                .email(request.getEmail())
                .surname(request.getSurname())
                .name(request.getName())
                .patronymic(request.getPatronymic())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()));

        profile.role(role.get());
        return profileRepository.save(profile.build());
    }

    @Override
    public AccessResponse auth(String email, String password) {
        var profile = findByEmail(email);
        if (profile == null || !passwordEncoder.matches(password, profile.getPassword())) {
            throw invalidMessage("Неверный логин или пароль");
        }
        var token = jwtProvider.generateToken(email);
        return new AccessResponse(token, profile.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileEntity findById(UUID id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> notFound("Пользователь с id %s не найден", id));
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileEntity findByEmail(String email) {
        return profileRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return profileRepository.findByEmail(username)
                .orElseThrow(() -> notFound("Пользователь с логином %s не найден", username));
    }
}
