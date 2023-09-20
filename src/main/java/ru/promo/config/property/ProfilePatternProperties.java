package ru.promo.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;
import java.util.regex.Pattern;

import static ru.promo.config.property.BaseProperties.APP_PREFIX;
import static ru.promo.config.property.BaseProperties.PATTERN_PROPERTIES_INTERFIX;

@Data
@ConfigurationProperties(prefix = APP_PREFIX + PATTERN_PROPERTIES_INTERFIX)
public class ProfilePatternProperties {
    @NotNull
    private Pattern email;
    @NotNull
    private Pattern phoneNumber;
}
