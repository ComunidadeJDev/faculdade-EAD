package com.jdev.gateway.Security.config;

import com.jdev.gateway.Security.utils.RsaKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import reactor.core.publisher.Flux;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Autowired
    private final RsaKeyProperties keyProperties;

    public SecurityConfiguration(RsaKeyProperties keyProperties) {
        this.keyProperties = keyProperties;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService detailsService) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(detailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(daoAuthenticationProvider);
    }

    @Bean
    public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/login").permitAll()
                        .pathMatchers("/auth/user/list").hasRole("ADMIN")
                        .pathMatchers("/auth/roles/list").hasRole("ADMIN")
                                .anyExchange().authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .build();
    }

    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(keyProperties.getPublicKey()).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(keyProperties.getPublicKey()).privateKey(keyProperties.getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter() {
        ReactiveJwtAuthenticationConverter jwtConverter = new ReactiveJwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
            // A conversão direta para Flux<GrantedAuthority> deve ser feita aqui.
            JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();
            authoritiesConverter.setAuthorityPrefix("ROLE_");
            authoritiesConverter.setAuthoritiesClaimName("roles");
            var authorities = authoritiesConverter.convert(jwt);
            // Transformando a Collection<GrantedAuthority> em Flux<GrantedAuthority>
            return Flux.fromIterable(authorities);
        });
        return jwtConverter;
    }
}
