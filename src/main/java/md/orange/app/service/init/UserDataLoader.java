package md.orange.app.service.init;


import static org.apache.commons.collections.CollectionUtils.isEmpty;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.orange.app.model.entity.Role;
import md.orange.app.model.entity.User;
import md.orange.app.model.enums.ERole;
import md.orange.app.repository.RoleRepository;
import md.orange.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDataLoader {

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  @PostConstruct
  public void addAdminUser() {
    if (isEmpty(userRepository.findAll())) {

      final Role roleAdmin = roleRepository.saveAndFlush(Role.builder()
          .role(ERole.ADMIN).build());
      final Role roleUser = roleRepository.saveAndFlush(Role.builder()
          .role(ERole.MANAGER).build());

      Set<Role> roles = new HashSet<>();
      roles.add(roleAdmin);
      roles.add(roleUser);

      userRepository.save(User.builder()
          .email("admin@example.com")
          .password(passwordEncoder.encode("adminpassword"))
          .surname("Admin")
          .forename("Admin")
          .createDateTime(Timestamp.valueOf(LocalDateTime.now()))
          .createUserName("Admin")
          .updateDateTime(Timestamp.valueOf(LocalDateTime.now()))
          .updateUserName("Admin")
          .active(true)
          .roles(roles)
          .build());
    }
  }
}
