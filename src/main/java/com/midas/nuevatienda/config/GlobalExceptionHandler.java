package com.midas.nuevatienda.config;

import com.midas.nuevatienda.controller.CodigoRespuestaEnum;
import com.midas.nuevatienda.exceptions.BaseException;
import com.midas.nuevatienda.exceptions.ContrasenasDiferentesException;
import com.midas.nuevatienda.exceptions.EmailInvalidoException;
import com.midas.nuevatienda.exceptions.NombreInvalidoException;
import com.midas.nuevatienda.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ValidationException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleRuntimeException(final RuntimeException exception,
                                                                       final WebRequest request){
        return this.builErrorResponse(CodigoRespuestaEnum.ERROR_GENERICO,exception,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ContrasenasDiferentesException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> handleContraseñasDiferentesException(final ContrasenasDiferentesException exception,
                                                                       final WebRequest request){
        return this.builErrorResponse(exception,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NombreInvalidoException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> handleNombreInvalidoException(final NombreInvalidoException exception,
                                                                       final WebRequest request){
        return this.builErrorResponse(exception,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> handleValidationException(final ValidationException exception,
                                                                final WebRequest request){
        return this.builErrorResponse(CodigoRespuestaEnum.ERROR_PARAMETROS_INCORRECTOS,exception,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EmailInvalidoException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> handleEmailInvalidoExceptionException(final EmailInvalidoException exception,
                                                             final WebRequest request){
        return this.builErrorResponse(exception,HttpStatus.NOT_ACCEPTABLE);
    }

    private ResponseEntity<Object> builErrorResponse(CodigoRespuestaEnum codigoEnum, Exception exception, HttpStatus httpStatus) {
        return getObjectResponseEntity(codigoEnum.getCodigo(),codigoEnum.getDescripcion(), httpStatus);
    }

    private ResponseEntity<Object> builErrorResponse(BaseException exception, HttpStatus httpStatus) {
        return getObjectResponseEntity(exception.getCodigo(),exception.getDescripcion(), httpStatus);
    }

    private ResponseEntity<Object> getObjectResponseEntity(String codigo,String descripcion, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse(codigo, descripcion);
        ResponseEntity<Object> response = ResponseEntity.status(httpStatus).body(errorResponse);
        return response;
    }
}
