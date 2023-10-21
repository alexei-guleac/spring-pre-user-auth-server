package md.orange.app.service.impl.auth;


import md.orange.app.model.data.auth.LoginData;
import md.orange.app.model.data.auth.TokenInfo;

public interface AuthenticationService {

    TokenInfo loginUser(LoginData loginData);

    TokenInfo getToken(TokenInfo tokenInfo);

    void logout();
}
