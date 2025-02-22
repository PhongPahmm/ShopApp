package spring.shopapp.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserCreationRequest {
    int id;
    String username;
    String fullName;
    LocalDate dob;
    String password;
    String email;
    String phoneNumber;
    String address;
    Boolean isActive;
}
