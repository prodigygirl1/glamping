package ru.promo.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.promo.config.JwtProvider;
import ru.promo.domain.AccessResponse;
import ru.promo.domain.CreateProfileRequest;
import ru.promo.domain.entity.ProfileEntity;
import ru.promo.repository.ProfileRepository;
import ru.promo.repository.RoleRepository;
import ru.promo.service.ProfileService;

import java.util.UUID;

import static ru.promo.util.exception.BadRequestException.invalidMessage;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtProvider jwtProvider;

    @Override
    @Transactional
    public ProfileEntity createProfile(CreateProfileRequest request) {

        if (findByEmail(request.getEmail()) != null) {
            throw invalidMessage("Указанный email уже используется", request.getEmail());
        }
        var profile = new ProfileEntity();
        profile.setEmail(request.getEmail());
        profile.setName(request.getName());
        profile.setSurname(request.getSurname());
        profile.setPatronymic(request.getPatronymic());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setPassword(passwordEncoder.encode(request.getPassword()));

        var role = roleRepository.findByName("ROLE_USER").get();
        profile.setRole(role);
        return profileRepository.save(profile);
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
                .orElseThrow(() -> invalidMessage("Пользователь с id %s не найден", id));
    }

    @Override
    @Transactional(readOnly = true)
    public ProfileEntity findByEmail(String email) {
        return profileRepository.findByEmail(email).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByEmail(username);
    }
}
