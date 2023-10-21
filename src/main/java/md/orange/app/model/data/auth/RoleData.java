package md.orange.app.model.data.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleData {

  @JsonProperty(value = "id", access = JsonProperty.Access.READ_ONLY)
  private Long id;

  @JsonProperty("roleName")
  @NotEmpty
  private String roleName;
}
