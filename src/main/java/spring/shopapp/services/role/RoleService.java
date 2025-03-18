package spring.shopapp.services.role;

import spring.shopapp.dtos.request.RoleCreationRequest;
import spring.shopapp.dtos.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleCreationRequest request);
    List<RoleResponse> getAllRoles();
}
