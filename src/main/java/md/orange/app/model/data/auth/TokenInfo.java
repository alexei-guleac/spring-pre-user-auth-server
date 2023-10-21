package md.orange.app.model.data.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

  private String userId;
  private String accessToken;
  private String refreshToken;
}
