package com.nt.votemanager.service;

import com.nt.votemanager.enums.MessageEnum;
import com.nt.votemanager.exceptions.ApiErrorException;
import com.nt.votemanager.utils.JsonUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;

import java.util.Objects;

import static com.nt.votemanager.utils.ExceptionUtils.logException;

@Log4j2
public abstract class AbstractService {
    @PersistenceContext
    public EntityManager em;
    public ApplicationEventPublisher publisher;
    private JsonUtils jsonUtils;

    public final void setJsonUtils(JsonUtils jsonUtils) {
        this.jsonUtils = jsonUtils;
    }

    protected void throwBadRequest(MessageEnum messageEnum) throws ApiErrorException {
        throw ApiErrorException.builder()
                .badRequest()
                .messageEnum(messageEnum)
                .build();
    }

    protected void throwNotFound(MessageEnum messageEnum) throws ApiErrorException {
        throw ApiErrorException.builder()
                .notFound()
                .messageEnum(messageEnum)
                .build();
    }

    protected void throwUnprocessableEntity(MessageEnum messageEnum) throws ApiErrorException {
        throw ApiErrorException.builder()
                .unprocessabelEntity()
                .messageEnum(messageEnum)
                .build();
    }

    protected void throwInternalErrorServer(MessageEnum messageEnum) throws ApiErrorException {
        throw ApiErrorException.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .messageEnum(messageEnum)
                .build();
    }

    public boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }

    public boolean isNotNull(Object obj) {
        return Objects.nonNull(obj);
    }

    public boolean isEquals(Object objectLeft, Object objectRight) {
        return Objects.equals(objectLeft, objectRight);
    }

    public boolean isNotEquals(Object objectLeft, Object objectRight) {
        return !this.isEquals(objectLeft, objectRight);
    }

    public void logDto(String msg, Object dto) {
        try {
            log.debug(msg + ": {}", jsonUtils.objectToJson(dto));
        } catch (Exception e) {
            log.debug(msg + ": {}", dto);
            log.warn("Ocorreu um problema ao mostrar o dto em json: {}", dto.getClass().getSimpleName());
            logException(e);
        }
    }

    public void logDto(Object dto) {
        var className = dto.getClass().getSimpleName();
        this.logDto(String.format("Dados de %s", className), dto);
    }
}
