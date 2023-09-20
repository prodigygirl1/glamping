package ru.promo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.promo.domain.CreateProfileRequest;
import ru.promo.domain.entity.RoleEnum;
import ru.promo.service.ProfileService;

import static ru.promo.domain.entity.RoleEnum.getRoleName;

@Configuration
@Slf4j
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final ProfileService profileService;

    public SecurityConfig(JwtFilter jwtFilter, ProfileService profileService) {
        this.jwtFilter = jwtFilter;
        this.profileService = profileService;
        createAdminOnStartup();
    }

    private void createAdminOnStartup() {
        final String admin = "admin@admin.ru";
        if (profileService.findByEmail(admin) == null) {
            profileService.createProfile(CreateProfileRequest.builder()
                    .surname("admin")
                    .name("admin")
                    .password("admin")
                    .email(admin)
                    .phoneNumber("89999999999")
                    .build(), getRoleName(RoleEnum.ADMIN));
            log.info("Admin user successfully created");
        }
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(req ->
                        req.antMatchers("/api/v1/profile/**").permitAll())
                .authorizeRequests(req -> req.antMatchers("/api/v1/admin/**").hasAnyRole("ADMIN"))
                .authorizeRequests().anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .cors().disable();

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/swagger-ui/**", "/v3/api-docs/**",
                "/configuration/ui", "/swagger-resources", "/configuration/security",
                "/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui",
                "/swagger-ui.html");
    }
}
