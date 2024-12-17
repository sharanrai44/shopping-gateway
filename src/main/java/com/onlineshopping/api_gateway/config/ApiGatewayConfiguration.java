package com.onlineshopping.api_gateway.config;

import com.onlineshopping.api_gateway.filter.AuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    private final AuthenticationFilter authenticationFilter;

    // Inject the AuthenticationFilter into the configuration
    public ApiGatewayConfiguration(AuthenticationFilter authenticationFilter) {
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
//        return  builder.routes().build();
//         builder.routes().
//                route(p->p.path("/product-service/**")
//                        .filters(f ->
//                                f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
//                        .uri("lb://PRODUCT-SERVICE")
//
//                ).build();

        return builder.routes()
                .route("product-service",
                        r -> r.path("/products/**") // Match paths starting with /products
                                .filters(f ->
                                        f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
                                .uri("lb://PRODUCT-SERVICE"))
                .route("user-service",
                        r -> r.path("/auth/**") // Match paths starting with /products
                                .uri("lb://USER-SERVICE"))
                .build();
    }
}
