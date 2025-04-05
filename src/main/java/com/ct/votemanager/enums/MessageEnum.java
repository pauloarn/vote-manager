package com.ct.votemanager.enums;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum MessageEnum {
    REQUISICAO_CONCLUIDA("message.api.requisicao.concluida"),
    ROUTE_NOT_FOUND("message.api.rout.not.found"),
    ENDPOINT_ERROR("message.api.endpoint.error"),
    VALIDATION_ERROR("message.api.validacao.error"),
    METHOD_NOT_SUPPORTED("message.api.method.not.supported"),
    MISSING_REQUEST_PARAMETER("message.api.missing.request.parameter"),
    UNKNOWN_ERROR("message.api.error.desconhecido"),
    INVALID_TOKEN("message.api.invalid.token"),
    FAIL_TO_CREATE_TOKEN("message.api.fail.to.create.token");

    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
    private String description;

    MessageEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public String getMessage(String... args) {
        String msg = resourceBundle.getString(this.description);
        return args == null ? msg : MessageFormat.format(msg, args);
    }
}
