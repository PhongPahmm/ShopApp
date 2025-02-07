package spring.shopapp.services.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.shopapp.dtos.request.UserCreationRequest;
import spring.shopapp.dtos.request.UserUpdateRequest;
import spring.shopapp.dtos.response.UserResponse;
import spring.shopapp.exception.AppException;
import spring.shopapp.exception.ErrorCode;
import spring.shopapp.mapper.UserMapper;
import spring.shopapp.models.Role;
import spring.shopapp.models.User;
import spring.shopapp.repositories.RoleRepository;
import spring.shopapp.repositories.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    @Override
    public UserResponse createUser(UserCreationRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_FOUND));
        user.setRole(userRole);
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if(userRepository.existsByPhoneNumber(request.getPhoneNumber())){
            throw new AppException(ErrorCode.PHONE_NO_EXISTED);
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    @Override
    public UserResponse updateUser(int id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        userRepository.delete(user);
    }
}
