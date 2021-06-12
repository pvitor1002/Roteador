package org.example.roteador.adapters.gateways.filters.predicates;

import lombok.NoArgsConstructor;
import org.example.roteador.domain.entities.TransactionHeader;
import org.example.roteador.domain.exceptions.BusinessException;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Configuration
public class CanaryRoutePredicateFactory extends AbstractRoutePredicateFactory<CanaryRoutePredicateFactory.Config> {

    @Override
    public String name() { return "Canary"; }

    public static final String CANARY_KEY = "canary";

    @Override
    public List<String> shortcutFieldOrder() { return Collections.singletonList(CANARY_KEY); }

    public CanaryRoutePredicateFactory(){
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            String result;
            result = "Transacao";
            if(extractHEaderToTransactionHeader(exchange.getRequest().getHeaders()).getAgencia().equals("1000"))
                result = "Contingencia";
            return result.equals(config.getCanary());
        };
    }

    private TransactionHeader extractHEaderToTransactionHeader(HttpHeaders headers){
        return TransactionHeader.builder()
                .agencia(Optional.ofNullable(headers.get("agencia")).orElseThrow(() -> {
                    System.err.println("Erro por falta de agencia");
                    return new BusinessException("Erro por falta de agencia", "400");
                }).get(0)).build();
    }

    @NoArgsConstructor
    public static class Config {
        private String canary;
        public void setCanary(String canary) { this.canary = canary; }
        public String getCanary() { return canary; }
    }
}
