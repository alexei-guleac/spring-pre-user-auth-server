package md.orange.app.service.impl.auth;


import lombok.RequiredArgsConstructor;
import md.orange.app.config.security.authorization.TokenGenerator;
import md.orange.app.model.data.auth.LoginData;
import md.orange.app.model.data.auth.TokenInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthenticationServiceImpl implements AuthenticationService {


  private final TokenGenerator tokenGenerator;

  private final DaoAuthenticationProvider daoAuthenticationProvider;

  @Qualifier("jwtRefreshTokenAuthProvider")
  private final JwtAuthenticationProvider refreshTokenAuthProvider;


  @Override
  public TokenInfo loginUser(LoginData loginData) {
    Authentication authentication = daoAuthenticationProvider.authenticate(
        new UsernamePasswordAuthenticationToken
            (loginData.getEmail(), loginData.getPassword()));

    return tokenGenerator.createToken(authentication);
  }

  @Override
  public TokenInfo getToken(TokenInfo tokenInfo) {
    Authentication authentication = refreshTokenAuthProvider
        .authenticate(new BearerTokenAuthenticationToken(tokenInfo.getRefreshToken()));
    Jwt jwt = (Jwt) authentication.getCredentials();
    // check if present in db and not revoked, etc

    return tokenGenerator.createToken(authentication);
  }

  @Override
  public void logout() {
    SecurityContextHolder.clearContext();
  }
}