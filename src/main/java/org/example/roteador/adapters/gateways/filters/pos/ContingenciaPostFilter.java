package org.example.roteador.adapters.gateways.filters.pos;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.roteador.adapters.gateways.entities.DataOut;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ContingenciaPostFilter extends ModifyResponseBodyGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Config config) {
        config.setInClass(String.class);
        config.setOutClass(ResponseEntity.class);
        config.setNewContentType(MediaType.APPLICATION_JSON_VALUE);
        config.setRewriteFunction((RewriteFunction<String, Object>)
                (ServerWebExchange serverWebExchange, String o) ->
                        Mono
                                .just(o)
                                .map(value -> {
                                    System.out.println("Resposta recebida -> " + value);
                                    return ResponseEntity.ok().body("Contingencia ativa");
                                }));
        return super.apply(config);
    }
}
