package ru.promo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import ru.promo.config.property.BaseProperties;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = {BaseProperties.class})
public class GlampingApplication {
    public static void main(String[] args) {
        SpringApplication.run(GlampingApplication.class, args);
    }
}
