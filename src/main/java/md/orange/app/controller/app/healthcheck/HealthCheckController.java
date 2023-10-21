package md.orange.app.controller.app.healthcheck;

import static md.orange.app.constants.RequestMappings.HEALTH_CHECK_ROOT;

import md.orange.app.controller.api.HealthCheckApi;
import md.orange.app.model.data.healthcheck.HealthCheckResponseData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(HEALTH_CHECK_ROOT)
public class HealthCheckController implements HealthCheckApi {

  private static final String STATUS = "OK";
  @Value("${info.app}")
  private String appName;

  @Override
  public ResponseEntity<HealthCheckResponseData> healthCheck() {
    return ResponseEntity.ok(HealthCheckResponseData.builder()
        .name(appName)
        .status(STATUS)
        .build());
  }

  @Override
  public ResponseEntity<String> getBackEndVersion() {
    return null;
  }
}
