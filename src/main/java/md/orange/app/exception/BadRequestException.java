package md.orange.app.exception;

import md.orange.app.model.exception.ValidationExceptionData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BadRequestException extends RuntimeException {

  public ValidationExceptionData validationExceptionData;

  public BadRequestException(ValidationExceptionData validationExceptionData) {
    super(validationExceptionData.getErrorMessage());
    this.validationExceptionData = validationExceptionData;
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable exception) {
    super(message, exception);
  }

}
