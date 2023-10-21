package md.orange.app.controller;


import static md.orange.app.constants.RequestMappings.AUTH_ROOT;

import lombok.RequiredArgsConstructor;
import md.orange.app.config.ProfileConstants;
import md.orange.app.controller.api.AuthenticationApi;
import md.orange.app.model.data.auth.LoginData;
import md.orange.app.model.data.auth.TokenInfo;
import md.orange.app.service.impl.auth.AuthenticationService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Profile({
    "!" + ProfileConstants.SPRING_PROFILE_DEVELOPMENT
})
@RequestMapping(AUTH_ROOT)
public class AuthenticationController implements AuthenticationApi {

  private final AuthenticationService authenticateService;

  @Override
  public TokenInfo loginUser(LoginData loginData) {
    return authenticateService.loginUser(loginData);
  }

  @Override
  public void logout() {
    authenticateService.logout();
  }

  @Override
  public TokenInfo getToken(TokenInfo tokenInfo) {
    return authenticateService.getToken(tokenInfo);
  }
}