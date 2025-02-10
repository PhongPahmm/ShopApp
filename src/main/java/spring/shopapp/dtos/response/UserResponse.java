package spring.shopapp.dtos.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    int id;
    String username;
    String fullName;
    LocalDate dob;
    String email;
    String phoneNumber;
    String address;
    Boolean isActive;
    String role_name;
}
