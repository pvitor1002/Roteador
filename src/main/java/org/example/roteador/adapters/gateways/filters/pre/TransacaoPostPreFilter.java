package org.example.roteador.adapters.gateways.filters.pre;

import org.example.roteador.domain.entities.PostPayloadRequest;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyRequestBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TransacaoPostPreFilter extends ModifyRequestBodyGatewayFilterFactory {

    @Override
    public GatewayFilter apply(Config config){
        config.setContentType(MediaType.APPLICATION_JSON_VALUE);
        config.setInClass(PostPayloadRequest.class);
        config.setOutClass(PostPayloadRequest.class);

        return (exchange, chain) ->
                chain.filter(exchange);
    }
}
