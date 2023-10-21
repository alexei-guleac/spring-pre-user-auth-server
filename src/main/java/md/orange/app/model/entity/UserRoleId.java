package md.orange.app.model.entity;


import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserRoleId implements Serializable {

  @Column(name = "user_id", nullable = false)
  @NotNull
  private UUID userId;

  @Column(name = "role_id", nullable = true)
  private Long roleId;

}
