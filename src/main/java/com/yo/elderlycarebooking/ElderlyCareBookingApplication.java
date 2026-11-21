package com.yo.elderlycarebooking;

import com.yo.elderlycarebooking.config.AppJwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppJwtProperties.class)   // Kích hoạt bind JWT config từ YAML
public class ElderlyCareBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElderlyCareBookingApplication.class, args);
    }
}
