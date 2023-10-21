package md.orange.app.service.impl.user;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import md.orange.app.exception.UserNotFoundException;
import md.orange.app.mapper.UserMapper;
import md.orange.app.model.data.auth.UserData;
import md.orange.app.model.entity.User;
import md.orange.app.repository.UserRepository;
import md.orange.app.service.UserDetailsService;
import org.hibernate.validator.internal.util.stereotypes.Lazy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  @Lazy
  PasswordEncoder passwordEncoder;

  @Autowired
  UserMapper userMapper;

  @Autowired
  UserDetailsManager userDetailsManager;


  @Override
  public User findById(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Id", id));
  }

  @Override
  public Set<User> getUserListByIds(List<UUID> userIds) {

    Set<User> users = new HashSet<>();
    for (UUID userId : userIds) {
      User user = findById(userId);
      users.add(user);
    }
    return users;
  }


  @Override
  public Page<UserData> searchByEmail(String email, Pageable pageable) {
    Page<User> foundUsersPage = userRepository.findByEmailContaining(email, pageable);
    return foundUsersPage.map(this::map);
  }

  @Override
  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UserNotFoundException("Email", email));
  }

  @Override
  public Page<UserData> findAllUsers(Pageable pageable) {
    Page<User> users = userRepository.findAll(pageable);
    return users.map(userMapper::map);
  }

  @Override
  public UserData map(User user) {
    return userMapper.map(user);
  }

  @Override
  public List<UserData> map(Set<User> users) {
    return users.stream().map(this::map).collect(Collectors.toList());
  }
}
