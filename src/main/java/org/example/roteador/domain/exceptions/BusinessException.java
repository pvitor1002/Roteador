package org.example.roteador.domain.exceptions;

import lombok.Data;
import lombok.Getter;

@Data
public class BusinessException extends RuntimeException {

    @Getter
    private final String httpStatus;
    @Getter
    private final String httpMessage;

    public BusinessException(String httpMessage, String httpStatus) {
        super();
        this.httpMessage = httpMessage;
        this.httpStatus = httpStatus;
    }
}
