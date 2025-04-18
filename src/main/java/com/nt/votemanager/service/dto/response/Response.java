package com.nt.votemanager.service.dto.response;

import com.nt.votemanager.enums.MessageEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.Serializable;

@Getter
public class Response<D> implements Serializable {
    D body;
    private Integer statusCode = 400;
    private String messageCode;
    private String message;

    public Response<D> setStatusCode(HttpStatusCode httpStatus, MessageEnum messageApiEnum) {
        this.statusCode = httpStatus.value();
        this.messageCode = messageApiEnum.name();
        this.message = messageApiEnum.getMessage();
        return this;
    }

    public Response<D> setStatusCode(HttpStatusCode httpStatus, String messageApiEnum, String message) {
        this.statusCode = httpStatus.value();
        this.messageCode = messageApiEnum;
        this.message = message;
        return this;
    }

    public void setBadRequest(MessageEnum messageApiEnum) {
        this.statusCode = HttpStatus.BAD_REQUEST.value();
        this.messageCode = messageApiEnum.name();
        this.message = messageApiEnum.getMessage();
    }

    public Response<D> setOk(MessageEnum messageApiEnum) {
        this.statusCode = 200;
        this.messageCode = messageApiEnum.name();
        this.message = messageApiEnum.getMessage();
        return this;
    }

    public Response<D> setOk() {
        MessageEnum messageApiEnum = MessageEnum.REQUEST_FINISHED;
        this.statusCode = 200;
        this.messageCode = messageApiEnum.name();
        this.message = messageApiEnum.getMessage();
        return this;
    }

    public Response<D> setCreated() {
        MessageEnum messageApiEnum = MessageEnum.REQUEST_FINISHED;
        this.statusCode = 201;
        this.messageCode = messageApiEnum.name();
        this.message = messageApiEnum.getMessage();

        return this;
    }

    public void setData(D data) {
        this.body = data;
    }
}
