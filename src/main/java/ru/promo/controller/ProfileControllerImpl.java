package ru.promo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.promo.domain.AccessResponse;
import ru.promo.domain.AuthRequest;
import ru.promo.domain.CreateProfileRequest;
import ru.promo.service.ProfileService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ProfileControllerImpl implements ProfileController {
    private final ProfileService profileService;

    @Override
    public void createProfile(CreateProfileRequest request) {
        if (log.isDebugEnabled()) {
            log.debug("Create profile with email {}", request.getEmail());
        }
        profileService.createProfile(request);
    }

    @Override
    public AccessResponse auth(AuthRequest request) {
        return profileService.auth(request.username(), request.password());
    }
}
