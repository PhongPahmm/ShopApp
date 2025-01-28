package spring.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import spring.shopapp.dtos.response.RoleResponse;
import spring.shopapp.models.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "id", target = "id")
    RoleResponse toRoleResponse(Role role);
}
