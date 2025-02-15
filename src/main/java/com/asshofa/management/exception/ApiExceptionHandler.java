package com.asshofa.management.exception;

import com.asshofa.management.exception.custom.BadRequestException;
import com.asshofa.management.exception.custom.ForbiddenException;
import com.asshofa.management.exception.custom.NotFoundException;
import com.asshofa.management.exception.custom.UnauthorizedException;
import com.asshofa.management.model.response.DefaultResponse;
import com.asshofa.management.model.response.ErrorsResponse;
import com.asshofa.management.model.response.ResponseMessage;
import com.asshofa.management.util.Constant;
import com.asshofa.management.util.interceptor.LoggingHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {

    private final LoggingHolder loggingHolder;

    private static final Logger logger = LogManager.getLogger(ApiExceptionHandler.class);

    public ApiExceptionHandler(LoggingHolder loggingHolder) {
        this.loggingHolder = loggingHolder;
    }

    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity<DefaultResponse> handleAlreadyExistsException(DuplicateKeyException e) {
        logger.error(Constant.VAR_ERRORS, e);
        HttpStatus status = HttpStatus.CONFLICT;
        DefaultResponse response = new DefaultResponse(Constant.VAR_ERRORS, ResponseMessage.DATA_ALREADY_EXISTS, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion());
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        logger.error(Constant.VAR_ERRORS, e);
        HttpStatus status = HttpStatus.NOT_FOUND;
        DefaultResponse response = new DefaultResponse(Constant.VAR_ERRORS, ResponseMessage.DATA_NOT_FOUND, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion());
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException e) {
        logger.error(Constant.VAR_ERRORS, e);
        HttpStatus status = HttpStatus.NOT_FOUND;
        DefaultResponse response = new DefaultResponse(Constant.VAR_ERRORS, ResponseMessage.DATA_NOT_FOUND, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion());
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorsResponse response = new ErrorsResponse(Constant.VAR_ERRORS, ResponseMessage.DATA_INVALID, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion(), errors);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleNotReadableExceptionErrors(HttpMessageNotReadableException ex) {
        logger.error(Constant.VAR_ERRORS, ex);
        List<String> errors = new ArrayList<>();
        errors.add(ResponseMessage.DATA_INVALID);

        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorsResponse response = new ErrorsResponse(Constant.VAR_ERRORS, ResponseMessage.DATA_INVALID, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion(), errors);
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchExceptionErrors(NoSuchElementException ex) {
        logger.error(Constant.VAR_ERRORS, ex);
        HttpStatus status = HttpStatus.NOT_FOUND;
        DefaultResponse response = new DefaultResponse(Constant.VAR_ERRORS, ResponseMessage.DATA_NOT_FOUND, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion());
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        logger.error(Constant.VAR_ERRORS, e);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        DefaultResponse response = new DefaultResponse(Constant.VAR_ERRORS, ResponseMessage.DATA_INVALID, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion());
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Object> badRequestException(BadRequestException e) {
        logger.error(Constant.VAR_ERRORS, e);
        HttpStatus status = HttpStatus.BAD_REQUEST;
        DefaultResponse response = new DefaultResponse(Constant.VAR_ERRORS, ResponseMessage.SOMETHING_WENT_WRONG, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion());
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = ForbiddenException.class)
    public ResponseEntity<Object> forbiddenException(ForbiddenException e) {
        logger.error(Constant.VAR_ERRORS, e);
        HttpStatus status = HttpStatus.FORBIDDEN;
        DefaultResponse response = new DefaultResponse(Constant.VAR_ERRORS, ResponseMessage.ROLE_INVALID, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion());
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> unauthorizedException(UnauthorizedException e) {
        logger.error(Constant.VAR_ERRORS, e);
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        DefaultResponse response = new DefaultResponse(Constant.VAR_ERRORS, ResponseMessage.UNAUTHORIZED, loggingHolder.getPath(), loggingHolder.getDate(), status.value(), loggingHolder.getVersion());
        return new ResponseEntity<>(response, new HttpHeaders(), status);
    }

}
