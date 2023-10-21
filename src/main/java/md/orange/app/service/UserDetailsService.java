package md.orange.app.service;


import java.util.List;
import java.util.Set;
import java.util.UUID;
import md.orange.app.model.data.auth.UserData;
import md.orange.app.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserDetailsService {

  User findById(UUID id);

  Set<User> getUserListByIds(List<UUID> userIds);

  User findByEmail(String email);

  UserData map(User user);

  List<UserData> map(Set<User> users);

  Page<UserData> searchByEmail(String email, Pageable pageable);

  Page<UserData> findAllUsers(Pageable pageable);

}
