package com.nt.votemanager.exceptions;

import com.nt.votemanager.enums.MessageEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class ExternalApiErrorException extends Exception {

    protected String messageCode = MessageEnum.EXTERNAL_ERROR.name();
    protected HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    protected String errorMessage = null;
    protected Object body = null;

    public ExternalApiErrorException(HttpStatus httpStatus, String messageCode) {
        super(messageCode);
        this.messageCode = messageCode;
        this.httpStatus = httpStatus;
    }

    public ExternalApiErrorException(HttpStatus httpStatus, String messageCode, String errorMessage) {
        super(messageCode);
        setMessageCode(messageCode);
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setMessageCode(String messageCode) {
        if (Objects.nonNull(messageCode)) {
            this.messageCode += "_" + messageCode;
        }
    }

    @Override
    public String toString() {
        return this.messageCode;
    }

    public static class Builder {
        private final ExternalApiErrorException apiErrorException;
        private final List<String> args = new ArrayList<>();

        public Builder() {
            this.apiErrorException = new ExternalApiErrorException();
        }

        public Builder httpStatus(HttpStatus httpStatus) {
            apiErrorException.httpStatus = httpStatus;
            return this;
        }

        public Builder messageCode(String messageCode) {
            this.apiErrorException.setMessageCode(messageCode);
            return this;
        }

        public Builder message(String message) {
            this.apiErrorException.errorMessage = message;
            return this;
        }

        public Builder body(Object body) {
            this.apiErrorException.body = body;
            return this;
        }

        public Builder badRequest() {
            this.apiErrorException.httpStatus = HttpStatus.BAD_REQUEST;
            return this;
        }

        public Builder unprocessabelEntity() {
            this.apiErrorException.httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            return this;
        }

        public Builder notFound() {
            this.apiErrorException.httpStatus = HttpStatus.NOT_FOUND;
            return this;
        }

        public ExternalApiErrorException build() {
            return this.apiErrorException;
        }
    }
}
