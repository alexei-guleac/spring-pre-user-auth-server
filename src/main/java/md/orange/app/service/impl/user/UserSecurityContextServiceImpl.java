package md.orange.app.service.impl.user;


import static md.orange.app.util.DataUtils.resolve;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import md.orange.app.exception.UserNotFoundException;
import md.orange.app.mapper.UserMapper;
import md.orange.app.model.data.auth.TokenInfo;
import md.orange.app.model.data.auth.UserData;
import md.orange.app.model.entity.User;
import md.orange.app.repository.UserRepository;
import md.orange.app.service.UserSecurityContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserSecurityContextServiceImpl implements UserSecurityContextService {


  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMapper userMapper;

  @Override
  public User findById(UUID id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("Id", id));
  }

  @Override
  public UserData currentUser() {
    User user = getCurrentUser();
    return userMapper.map(user);
  }

  @Override
  public User getCurrentUser() {
    return findById(getCurrentUserId());
  }

  @Override
  public UUID getCurrentUserId() {
    SecurityContext context = SecurityContextHolder.getContext();
    final boolean validUserExistsInContext = resolve(
        () -> context.getAuthentication().getPrincipal()).isPresent();

    if (validUserExistsInContext) {
      Object principal = context.getAuthentication().getPrincipal();
      log.error(String.valueOf(principal));
      if (principal instanceof TokenInfo) {
        return UUID.fromString(((TokenInfo) principal).getUserId());
      }
      return ((User) principal).getId();
    }
    return null;
  }

  @Override
  public String getUserPrincipalInfo() {
    SecurityContext context = SecurityContextHolder.getContext();
    final boolean validUserExistsInContext = resolve(
        () -> context.getAuthentication().getPrincipal()).isPresent();

    if (validUserExistsInContext) {
      Object principal = context.getAuthentication().getPrincipal();
      log.error(String.valueOf(principal));
      if (principal instanceof String) {
        return (String) context.getAuthentication()
            .getPrincipal();
      }
      return ((User) principal).getId().toString();
    }
    return null;
  }

  @Override
  public Map<String, String> getRequestHeaders(HttpServletRequest httpServletRequest) {
    Map<String, String> headers = new HashMap<>();

    Enumeration headerNames = httpServletRequest.getHeaderNames();
    while (headerNames.hasMoreElements()) {
      String key = (String) headerNames.nextElement();
      String value = httpServletRequest.getHeader(key);
      headers.put(key, value);
    }

    return headers;
  }
}
