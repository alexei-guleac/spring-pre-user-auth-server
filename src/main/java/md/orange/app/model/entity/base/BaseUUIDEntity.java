package md.orange.app.model.entity.base;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;


@Getter
@Setter
@ToString
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class BaseUUIDEntity implements Serializable {

  @Id
  @GeneratedValue(generator = "uuid-hibernate-generator")
  @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

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
