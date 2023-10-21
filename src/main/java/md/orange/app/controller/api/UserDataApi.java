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
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import md.orange.app.model.data.auth.UserData;
import md.orange.app.model.entity.User;
import md.orange.app.model.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Api(value = "User", tags = {
    "User - get controller",})
public interface UserDataApi {

  @ApiOperation(value = "Get user by id", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK, response = UserData.class),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE)})
  @GetMapping("/{id}")
  @PreAuthorize("#user.id == #id")
  @ResponseStatus(HttpStatus.OK)
  UserData user(@AuthenticationPrincipal User user, @PathVariable UUID id);

  @ApiOperation(value = "Get users list by id", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK, response = UserData.class),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE)})
  @GetMapping("/id")
  @ResponseStatus(HttpStatus.OK)
  Set<UserData> getUserListByIds(@Valid @RequestBody List<UUID> userIds);

  @ApiOperation(value = "Get all users list", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK, response = UserData.class),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE)})
  @GetMapping("/page/{pageNumber}/{pageSize}")
  @ResponseStatus(HttpStatus.OK)
  Set<UserData> getAllUsers(@PathVariable int pageNumber, @PathVariable int pageSize);

  @ApiOperation(value = "Search users by email", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK, response = UserData.class),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE)})
  @GetMapping("/{email}/page/{pageNumber}/{pageSize}")
  @ResponseStatus(HttpStatus.OK)
  List<UserData> searchByEmail(@PathVariable String email, @PathVariable int pageNumber,
      @PathVariable int pageSize);

  @ApiOperation(value = "Get user by email", tags = {})
  @ApiResponses(value = {
      @ApiResponse(code = OK, message = API_RESPONSE_OK, response = UserData.class),
      @ApiResponse(code = BAD_REQUEST, message = API_RESPONSE_BAD_REQUEST, response = ExceptionDetails.class),
      @ApiResponse(code = UNAUTHORIZED, message = API_RESPONSE_UNAUTHORIZED),
      @ApiResponse(code = FORBIDDEN, message = API_RESPONSE_FORBIDDEN),
      @ApiResponse(code = NOT_FOUND, message = API_RESPONSE_NOT_FOUND),
      @ApiResponse(code = UNPROCESSABLE_ENTITY, message = API_RESPONSE_UNPROCESSABLE_ENTITY),
      @ApiResponse(code = INTERNAL_SERVER_ERROR, message = API_RESPONSE_INTERNAL_SERVER_ERROR),
      @ApiResponse(code = UNAVAILABLE, message = API_RESPONSE_UNAVAILABLE)})
  @GetMapping("/email/{email}")
  @ResponseStatus(HttpStatus.OK)
  UserData getByEmail(@PathVariable String email);

}
