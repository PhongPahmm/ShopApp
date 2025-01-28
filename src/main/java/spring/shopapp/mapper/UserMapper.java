package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import spring.shopapp.dtos.request.UserCreationRequest;
import spring.shopapp.dtos.request.UserUpdateRequest;
import spring.shopapp.dtos.response.UserResponse;
import spring.shopapp.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    @Mapping(source = "role.name", target = "role_name")
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
