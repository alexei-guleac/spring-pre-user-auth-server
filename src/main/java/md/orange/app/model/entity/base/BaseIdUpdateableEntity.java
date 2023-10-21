package md.orange.app.model.entity.base;


import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Getter
@Setter
@ToString
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseIdUpdateableEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "create_user_name", nullable = false)
  private String createUserName;

  @Column(name = "update_user_name")
  private String updateUserName;

  @Column(name = "create_datetime", nullable = false)
  @CreationTimestamp
  private Timestamp createDateTime;

  @Column(name = "update_datetime")
  @UpdateTimestamp
  private Timestamp updateDateTime;

  @Column(name = "deleted", nullable = false)
  private boolean deleted;

}
