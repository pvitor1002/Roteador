package org.example.roteador.adapters.gateways.filters.global;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class GenericGlobalFilter extends AbstractGatewayFilterFactory<GenericGlobalFilter.Config> {

    public GenericGlobalFilter(){
        super(Config.class);
    }

    public GatewayFilter apply(Config config) {

        return ((exchange, chain) ->{
            System.out.println("Iniciando requisição do cliente");

            return chain.filter(exchange);
        });
    }

    public static class Config {
        private String name;
        public void setName(String name){ this.name = name; }
        public String getName(){ return name; }
    }
}
