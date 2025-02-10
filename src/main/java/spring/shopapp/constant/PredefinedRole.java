package spring.shopapp.constant;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.shopapp.models.Role;
import spring.shopapp.repositories.RoleRepository;

@Configuration
public class PredefinedRole {
    public static final String USER_ROLE = "USER";
    public static final String ADMIN_ROLE = "ADMIN";

    public PredefinedRole() {}

//    @Bean
//    @Transactional
//    public CommandLineRunner initRoles(RoleRepository roleRepository) {
//        return args -> {
//            if(roleRepository.findByName("ADMIN").isEmpty()) {
//                Role adminRole = Role.builder().name("ADMIN").build();
//                roleRepository.save(adminRole);
//            }
//            if(roleRepository.findByName("USER").isEmpty()) {
//                Role userRole = Role.builder().name("USER").build();
//                roleRepository.save(userRole);
//            }
//        };
//    }
}