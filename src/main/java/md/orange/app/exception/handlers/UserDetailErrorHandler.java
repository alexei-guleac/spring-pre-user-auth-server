package md.orange.app.exception.handlers;

import lombok.RequiredArgsConstructor;
import md.orange.app.exception.PasswordUpdateException;
import md.orange.app.exception.UserNotFoundException;
import md.orange.app.model.exception.ErrorCode;
import md.orange.app.model.exception.ValidationExceptionData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserDetailErrorHandler extends ResponseEntityExceptionHandler {

  private final ExceptionHandlerMapper exceptionHandlerMapper;

  @ExceptionHandler(value = {UsernameNotFoundException.class})
  protected ResponseEntity<Object> validationUsernameNotFound(
      UsernameNotFoundException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.NOT_FOUND,
            ErrorCode.NOT_FOUND, ValidationExceptionData.builder().build()));
  }

  @ExceptionHandler(value = {UserNotFoundException.class})
  protected ResponseEntity<Object> validationUserNotFound(
      UserNotFoundException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.NOT_FOUND,
            ErrorCode.NOT_FOUND, ex.getValidationExceptionData()));
  }

  @ExceptionHandler(value = {PasswordUpdateException.class})
  protected ResponseEntity<Object> processPasswordUpdate(
      PasswordUpdateException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.NOT_FOUND,
            ErrorCode.NOT_FOUND, ex.getValidationExceptionData()));
  }

  @ExceptionHandler(value = {BadCredentialsException.class})
  protected ResponseEntity<Object> validationUserNotFound(
      BadCredentialsException ex, WebRequest request) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionHandlerMapper
        .getCustomExceptionDetails(ex,
            exceptionHandlerMapper.getContextPath(request), HttpStatus.NOT_FOUND,
            ErrorCode.NOT_FOUND, ValidationExceptionData.builder().build()));
  }

}
