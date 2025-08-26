package com.ms.api_gateway.filter;

import com.ms.api_gateway.util.JwtUtil;
import com.ms.api_gateway.util.RouterValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class GlobalAuthenticationFilter implements GlobalFilter, Ordered {

    private final RouterValidator routerValidator;
    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        log.info("Request path: {}", request.getURI().getPath());

        if (routerValidator.isSecured.test(request)) {
            if (isAuthMissing(request)) {
                log.error("Authorization header is missing");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            final String token = getAuthHeader(request);
            if (token == null || !jwtUtil.isValid(token)) {
                log.error("Authorization token is invalid");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }

            populateRequestWithHeaders(exchange, token);
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1; // High priority
    }

    private Mono<Void> onError(ServerWebExchange exchange, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        return response.setComplete();
    }

    private String getAuthHeader(ServerHttpRequest request) {
        String authHeader = request.getHeaders().getFirst("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, String token) {
        try {
            String username = jwtUtil.getUsernameFromToken(token);
            exchange.getRequest().mutate()
                    .header("X-auth-user", username)
                    .build();
        } catch (Exception e) {
            log.error("Error populating request headers", e);
        }
    }
}
