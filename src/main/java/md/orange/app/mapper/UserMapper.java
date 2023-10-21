package md.orange.app.mapper;


import md.orange.app.model.data.auth.UserData;
import md.orange.app.model.entity.User;
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
public interface UserMapper {

  @Mappings({
      @Mapping(target = "email", source = "mail"),
      @Mapping(target = "password", source = "password"),
      @Mapping(target = "surname", source = "firstName"),
      @Mapping(target = "forename", source = "lastName"),
  })
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  User map(UserData userData);

  @Mappings({
      @Mapping(target = "id", source = "id"),
      @Mapping(target = "mail", source = "email"),
      @Mapping(target = "password", source = "password"),
      @Mapping(target = "firstName", source = "surname"),
      @Mapping(target = "lastName", source = "forename"),
  })
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  UserData map(User user);

//  @Named("mapID")
//  default List<ProductOverview> mapProducts(ProductType value){
//    List<ProductOverview> products = new ArrayList<>();
//
//    //add your custom mapping implementation
//
//    return products;
//  }

}
