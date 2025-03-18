package spring.shopapp.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import spring.shopapp.dtos.request.RoleCreationRequest;
import spring.shopapp.dtos.response.RoleResponse;
import spring.shopapp.models.Role;
import spring.shopapp.services.role.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping("")
    RoleResponse createRole(@RequestBody RoleCreationRequest request) {
        return roleService.createRole(request);
    }
    @GetMapping("")
    public List<RoleResponse> getRoles() {
        return roleService.getAllRoles();
    }
}
