package md.orange.app.model.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "user_role")
@Entity
@Data
public class UserRole {

  @EmbeddedId
  private UserRoleId userRoleId;
}
