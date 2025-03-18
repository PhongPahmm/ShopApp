package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.shopapp.dtos.request.RoleCreationRequest;
import spring.shopapp.dtos.response.RoleResponse;
import spring.shopapp.models.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleCreationRequest request);
    @Mapping(source = "id", target = "id")
    RoleResponse toRoleResponse(Role role);
}
