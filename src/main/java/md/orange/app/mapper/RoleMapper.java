package md.orange.app.mapper;


import md.orange.app.model.data.auth.RoleData;
import md.orange.app.model.entity.Role;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring",
    nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface RoleMapper {

  @Mappings({
      @Mapping(target = "id", source = "id"),
      @Mapping(target = "role", source = "roleName"),
  })
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Role map(RoleData roleData);

  @Mappings({
      @Mapping(target = "id", source = "id"),
      @Mapping(target = "roleName", source = "role"),
  })
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  RoleData map(Role role);

}
