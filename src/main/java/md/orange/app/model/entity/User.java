package md.orange.app.model.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import md.orange.app.model.entity.base.BaseUUIDEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_details")
public class User extends BaseUUIDEntity  implements UserDetails {

  @Column(name = "name")
  private String name;

  @Column(name = "email")
  private String email;

  @Column(name = "username")
  private String username;

  @Column(name = "encoded_password")
  private String encodedPassword;

  @Column(name = "password")
  private String password;

  @Column(name = "surname")
  private String surname;

  @Column(name = "forename")
  private String forename;

  @Column(name = "isEnabled")
  private boolean isEnabled;

  @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
  @JoinTable(name = "user_role",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles;

  @Column(name = "active")
  private boolean active;

  @Builder
  public User(UUID id, String createUserName, String updateUserName,
      Timestamp createDateTime, Timestamp updateDateTime, boolean deleted,
      String name, String email, String username, String encodedPassword, String password,
      String surname, String forename, boolean isEnabled,
      Set<Role> roles, boolean active) {
    super(id, createUserName, updateUserName, createDateTime, updateDateTime, deleted);
    this.name = name;
    this.email = email;
    this.username = username;
    this.encodedPassword = encodedPassword;
    this.password = password;
    this.surname = surname;
    this.forename = forename;
    this.isEnabled = isEnabled;
    this.roles = roles;
    this.active = active;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Set<Role> roles = this.getRoles();

    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority(role.getRole().getRole()));
    }
    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}





