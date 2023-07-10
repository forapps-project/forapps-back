package com.team.forapps.common;

import com.team.forapps.jwt.exception.InvalidTokenException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import javax.security.auth.login.AccountException;

import static com.team.forapps.common.ResponseCode.*;
import static com.team.forapps.jwt.support.CookieSupport.deleteJwtTokenInCookie;

@RestControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler({InvalidTokenException.class , AuthenticationException.class})
    public ResponseEntity invalidTokenExceptionHandler(Exception e) {
        ResponseMessage message = ResponseMessage.of(INVALID_TOKEN , e.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
    }

    @ExceptionHandler({AccountException.class})
    public ResponseEntity accountExceptionHandler(Exception e) {
        ResponseMessage message = ResponseMessage.of(REQUEST_FAIL , e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }

    @ExceptionHandler({FileSizeLimitExceededException.class})
    public ResponseEntity fileUploadExceptionHandler(Exception e) {
        ResponseMessage message = ResponseMessage.of(REQUEST_FAIL , e.getMessage());

        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler({MalformedJwtException.class})
    public ResponseEntity malformedJwtExceptionHandler(Exception e , HttpServletResponse response) {
        ResponseMessage message = ResponseMessage.of(AUTHORIZATION_ERROR , "변조된 RefreshToken 입니다.");

        deleteJwtTokenInCookie(response);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
    }
}
