package org.example.roteador.adapters.gateways.filters.pos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.roteador.adapters.gateways.entities.DataOut;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.factory.rewrite.RewriteFunction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class TransacaoPostPosFilter extends ModifyResponseBodyGatewayFilterFactory {

    private final ObjectMapper objectMapper;

    @Override
    public GatewayFilter apply(Config config){
        config.setInClass(String.class);
        config.setOutClass(DataOut.class);
        config.setNewContentType(MediaType.APPLICATION_JSON_VALUE);
        config.setRewriteFunction((RewriteFunction<String, Object>)
                (ServerWebExchange serverWebExchange, String o) ->
                        Mono
                                .just(o)
                                .map(value -> {
                                    try {
                                        System.out.println("Resposta recebida -> " + value);
                                        DataOut dataOut = objectMapper.readValue(value, DataOut.class);
                                        System.out.println("Resposta enviada para o cliente -> " + dataOut.toString());
                                        return dataOut;
                                    } catch (JsonProcessingException e) {
                                        e.printStackTrace();
                                        throw new RuntimeException();
                                    }
                                }));
        return super.apply(config);
    }
}
