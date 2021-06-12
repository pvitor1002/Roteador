package org.example.roteador.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostPayloadRequest {

    private String contaOrigem;
    private String agenciaOrigem;
    private String senha;
    private String contaDestino;
    private String agenciaDestino;
    private BigDecimal valor;
}
