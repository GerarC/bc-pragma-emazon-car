package com.emazon.cart.configuration;

import com.emazon.cart.domain.api.CartServicePort;
import com.emazon.cart.domain.api.usecase.CartUseCase;
import com.emazon.cart.domain.spi.*;
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
    private final CartPersistencePort cartPersistencePort;
    private final UserPersistencePort userPersistencePort;
    private final ItemPersistencePort itemPersistencePort;
    private final ProductPersistencePort productPersistencePort;
    private final SupplyPersistencePort supplyPersistencePort;

    @Bean
    CartServicePort carServicePort() {
        return new CartUseCase(cartPersistencePort, userPersistencePort, productPersistencePort, itemPersistencePort, supplyPersistencePort);
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
