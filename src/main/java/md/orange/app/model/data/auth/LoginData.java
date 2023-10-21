package md.orange.app.model.data.auth;


import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginData {

  @NotBlank
  private String email;

  @NotBlank
  @Size(min = 6, message = "Password should have at least 6 characters")
  private String password;

  @Builder
  public LoginData(String email, @NotBlank String password) {
    this.email = email;
    this.password = password;
  }
}