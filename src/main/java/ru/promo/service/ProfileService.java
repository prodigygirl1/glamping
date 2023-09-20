package ru.promo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.promo.domain.AccessResponse;
import ru.promo.domain.CreateProfileRequest;
import ru.promo.domain.entity.ProfileEntity;

import java.util.UUID;

public interface ProfileService extends UserDetailsService {
    ProfileEntity createProfile(CreateProfileRequest request);

    ProfileEntity createProfile(CreateProfileRequest request, String role);

    AccessResponse auth(String email, String password);

    ProfileEntity findById(UUID id);

    ProfileEntity findByEmail(String email);
}
