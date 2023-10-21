package md.orange.app.service.impl.user;


import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;
import md.orange.app.exception.AlreadyExistsException;
import md.orange.app.exception.UserNotFoundException;
import md.orange.app.model.entity.Role;
import md.orange.app.model.entity.User;
import md.orange.app.model.enums.ERole;
import md.orange.app.repository.RoleRepository;
import md.orange.app.repository.UserRepository;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsManagerImpl implements UserDetailsManager {

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  @Lazy
  PasswordEncoder passwordEncoder;

  @Override
  public void createUser(UserDetails user) {
    User currentUser = (User) user;

    if (userExists(currentUser.getEmail())) {
      throw new AlreadyExistsException(
          MessageFormat.format("User with email {0} already exists", currentUser.getEmail()));
    }

    currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
    Set<Role> roles = new HashSet<>();
//    if (currentUser.getAuthorities() != null) {
//      RoleDto roleDto = userDto.getUserRole();
//      role = roleService.findByName(roleDto.getName());
//    } else {
    roles.add(roleRepository.findByRole(ERole.ADMIN).get());
//    }
    currentUser.setRoles(roles);

    currentUser.setCreateUserName("user");
    currentUser.setUpdateUserName("user");
    currentUser.setActive(true);
    currentUser.setDeleted(false);

    save(currentUser);
  }

  @Override
  public void updateUser(UserDetails user) {
  }

  @Override
  public void deleteUser(String email) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("Email", email));
    userRepository.delete(user);
  }

  @Override
  public void changePassword(String oldPassword, String newPassword) {
  }

  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public boolean userExists(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(
            MessageFormat.format("email {0} not found", email)
        ));
  }
}
