package md.orange.app.exception;


import md.orange.app.model.exception.ValidationExceptionData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ElementNotFoundException extends RuntimeException {

  public ValidationExceptionData validationExceptionData;

  public ElementNotFoundException(ValidationExceptionData validationExceptionData) {
    super(validationExceptionData.getErrorMessage());
    this.validationExceptionData = validationExceptionData;
  }

  public ElementNotFoundException(String errMessage) {
    super(errMessage);
  }
}
