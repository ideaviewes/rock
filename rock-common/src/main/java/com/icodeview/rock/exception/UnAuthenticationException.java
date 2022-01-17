package com.icodeview.rock.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnAuthenticationException extends RuntimeException {
    private Integer code=403;
    public UnAuthenticationException(String message){
        super(message);
    }
}
