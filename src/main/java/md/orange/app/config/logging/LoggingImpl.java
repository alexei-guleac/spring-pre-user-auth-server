package md.orange.app.config.logging;

import lombok.extern.slf4j.Slf4j;
import md.orange.app.config.ProfileConstants;
import md.orange.app.model.data.healthcheck.HealthCheckResponseData;
import md.orange.app.service.UserSecurityContextService;
import md.orange.app.util.DateUtils;
import md.orange.app.util.JwtUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;


@Component
@Aspect
@Profile({
    ProfileConstants.SPRING_PROFILE_DEVELOPMENT,
    ProfileConstants.SPRING_PROFILE_TEST,
    ProfileConstants.SPRING_PROFILE_TEST_LOCAL
})
@Slf4j
public class LoggingImpl extends LoggingBaseImpl {

  public LoggingImpl(Environment env,
      UserSecurityContextService userSecurityContextService,
      DateUtils dateUtils, md.orange.app.util.StringUtils stringUtils, JwtUtils jwtUtil) {
    super(env, userSecurityContextService, dateUtils, stringUtils, jwtUtil);
  }

  @Override
  @AfterReturning(pointcut = "controllerExecutionLogger()", returning = "response")
  public void afterReturningControllerAdvice(
      JoinPoint joinPoint, ResponseEntity response
  ) {
//    log.info(String.valueOf(response));
    if (response != null) {
      String responseBody = String.valueOf(response.getBody());
      if (!(responseBody.contains(HealthCheckResponseData.class.getSimpleName()) && responseBody
          .contains(("status=OK")))) {
        log.info(
            this.generateTitle("Response")
                + nl + "Response status: " + response.getStatusCodeValue()
                + nl + "Response body: " + responseBody + nl
        );
      }
    }
  }
}
