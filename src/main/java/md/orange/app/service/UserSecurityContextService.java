package md.orange.app.service;


import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import md.orange.app.model.data.auth.UserData;
import md.orange.app.model.entity.User;


public interface UserSecurityContextService {

  User findById(UUID id);

  UserData currentUser();

  User getCurrentUser();

  UUID getCurrentUserId();

  String getUserPrincipalInfo();

  Map<String, String> getRequestHeaders(HttpServletRequest httpServletRequest);

}
