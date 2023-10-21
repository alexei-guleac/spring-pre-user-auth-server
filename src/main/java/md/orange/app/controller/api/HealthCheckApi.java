package md.orange.app.controller.api;


import static md.orange.app.constants.ApiDescriptionConstants.API_RESPONSE_BAD_REQUEST;
import static md.orange.app.constants.ApiDescriptionConstants.API_RESPONSE_FORBIDDEN;
import static md.orange.app.constants.ApiDescriptionConstants.API_RESPONSE_INTERNAL_SERVER_ERROR;
import static md.orange.app.constants.ApiDescriptionConstants.API_RESPONSE_NOT_FOUND;
import static md.orange.app.constants.ApiDescriptionConstants.API_RESPONSE_OK;
import static md.orange.app.constants.ApiDescriptionConstants.API_RESPONSE_UNAUTHORIZED;
import static md.orange.app.constants.ApiDescriptionConstants.API_RESPONSE_UNAVAILABLE;
import static md.orange.app.constants.ApiDescriptionConstants.API_RESPONSE_UNPROCESSABLE_ENTITY;
import static md.orange.app.constants.ApiDescriptionConstants.BAD_REQUEST;
import static md.orange.app.constants.ApiDescriptionConstants.FORBIDDEN;
import static md.orange.app.constants.ApiDescriptionConstants.INTERNAL_SERVER_ERROR;
import static md.orange.app.constants.ApiDescriptionConstants.NOT_FOUND;
import static md.orange.app.constants.ApiDescriptionConstants.OK;
import static md.orange.app.constants.ApiDescriptionConstants.UNAUTHORIZED;
import static md.orange.app.constants.ApiDescriptionConstants.UNAVAILABLE;
import static md.orange.app.constants.ApiDescriptionConstants.UNPROCESSABLE_ENTITY;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import md.orange.app.model.data.healthcheck.HealthCheckResponseData;
import md.orange.app.model.exception.ExceptionDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Api(value = "HealthCheckApi", description = "The Health check API")
public interface HealthCheckApi {

  @ApiOperation(value = "Check APP health status", nickname = "healthCheck",
      response = HealthCheckResponseData.class, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED, response = ExceptionDetails.class),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN, response = ExceptionDetails.class),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND, response = ExceptionDetails.class),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY,
          response = ExceptionDetails.class),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR, response = ExceptionDetails.class),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE, response = ExceptionDetails.class)})
  @RequestMapping(
      method = RequestMethod.GET)
  ResponseEntity<HealthCheckResponseData> healthCheck();

  @ApiOperation(value = "Get BE version endpoint", nickname = "getBackEndVersion",
      response = String.class, tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED, response = ExceptionDetails.class),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN, response = ExceptionDetails.class),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND, response = ExceptionDetails.class),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY,
          response = ExceptionDetails.class),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR, response = ExceptionDetails.class),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE, response = ExceptionDetails.class)})
  @GetMapping("/getBackEndVersion")
  ResponseEntity<String> getBackEndVersion();

}
