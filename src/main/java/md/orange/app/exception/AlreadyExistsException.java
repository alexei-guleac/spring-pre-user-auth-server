package md.orange.app.exception;

import md.orange.app.model.exception.ValidationExceptionData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AlreadyExistsException extends RuntimeException {

  public ValidationExceptionData validationExceptionData;

  public AlreadyExistsException(String message) {
    super(message);
  }

  public AlreadyExistsException(ValidationExceptionData validationExceptionData) {
    super(validationExceptionData.getErrorMessage());
    this.validationExceptionData = validationExceptionData;
  }
}
