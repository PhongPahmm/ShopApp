package spring.shopapp.dtos.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserUpdateRequest {
    String username;
    String fullName;
    LocalDate dob;
    String email;
    String phoneNumber;
    String address;
    Boolean isActive;
}
