package com.emazon.car.configuration;

import com.emazon.car.domain.api.CarServicePort;
import com.emazon.car.domain.api.usecase.CarUserCase;
import com.emazon.car.domain.spi.CarPersistencePort;
import com.emazon.car.domain.spi.ItemPersistencePort;
import com.emazon.car.domain.spi.ProductPersistencePort;
import com.emazon.car.domain.spi.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final UserDetailsService userDetailsService;
    private final CarPersistencePort carPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final ItemPersistencePort itemPersistencePort;
    private final ProductPersistencePort productPersistencePort;

    @Bean
    CarServicePort carServicePort() {
        return new CarUserCase(carPersistencePort, userPersistencePort, productPersistencePort, itemPersistencePort);
    }

    // security
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

}
