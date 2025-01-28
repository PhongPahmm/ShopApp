package spring.shopapp.services.role;

import spring.shopapp.dtos.response.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();
}
