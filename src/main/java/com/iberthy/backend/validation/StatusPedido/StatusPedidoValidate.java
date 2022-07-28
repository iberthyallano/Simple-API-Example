package com.iberthy.backend.validation.StatusPedido;

import com.iberthy.backend.utils.Message;

import javax.validation.Payload;

public @interface StatusPedidoValidate {
    String message() default Message.statusPedidoValidate;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
