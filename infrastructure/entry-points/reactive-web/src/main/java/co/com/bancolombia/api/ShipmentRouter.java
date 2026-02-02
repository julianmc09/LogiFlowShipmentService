package co.com.bancolombia.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ShipmentRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(ShipmentHandler handler) {
        return route(POST("/api/shipments").and(accept(MediaType.APPLICATION_JSON)), handler::create)
                .andRoute(PATCH("/api/shipments/{id}/status").and(accept(MediaType.APPLICATION_JSON)),
                        handler::changeStatus);
    }
}
