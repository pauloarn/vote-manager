package com.nt.votemanager.enums;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public enum MessageEnum {
    REQUEST_FINISHED("message.api.request.finished"),
    ROUTE_NOT_FOUND("message.api.rout.not.found"),
    ENDPOINT_ERROR("message.api.endpoint.error"),
    VALIDATION_ERROR("message.api.validate.error"),
    METHOD_NOT_SUPPORTED("message.api.method.not.supported"),
    MISSING_REQUEST_PARAMETER("message.api.missing.request.parameter"),
    UNKNOWN_ERROR("message.api.unknown.error"),
    AGENDA_NOT_FOUND("message.api.agenda.not.found"),
    AGENDA_VOTE_SESSION_ALREADY_STARTED("message.api.vote.session.already.started"),
    AGENDA_VOTE_SESSION_ALREADY_ENDED("message.api.vote.session.already.ended");

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
