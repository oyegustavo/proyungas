//package gov.justucuman.zuul.filters;
//
//
//
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.core.context.ReactiveSecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//
//import lombok.extern.slf4j.Slf4j;
//import reactor.core.publisher.Mono;
//
//@Component
//@Slf4j
//public class CurrentUserFilter implements GlobalFilter, Ordered {
//
//    public static final String USER_HEADER = "X-User-Header";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//    	return ReactiveSecurityContextHolder.getContext()
//    		    .map(ctx -> ctx.getAuthentication())
//    		    .flatMap(authentication -> {
//    		        if (authentication != null && authentication.isAuthenticated()) {
//    		            HttpHeaders headers = exchange.getRequest().getHeaders();
//    		            String authHeader = headers.getFirst(HttpHeaders.AUTHORIZATION);
//    		            if (authHeader != null) {
//    		                exchange.getRequest().mutate()
//    		                        .header(USER_HEADER, authHeader)
//    		                        .build();
//    		            }
//    		        }
//    		        log.info("Gateway CurrentUserFilter invoked for path: " + exchange.getRequest().getPath());
//    		        return chain.filter(exchange);
//    		    });
//
//    }
//
//    @Override
//    public int getOrder() {
//        // Run before routing
//        return Ordered.HIGHEST_PRECEDENCE;
//    }
//}
//
