package comsep23.srcspro.service;

import comsep23.srcspro.dto.user_dto.AuthUserDTO;
import comsep23.srcspro.dto.user_dto.LoginDTO;
import comsep23.srcspro.dto.user_dto.RegisterDTO;
import comsep23.srcspro.dto.user_dto.UserCreateDTO;
import comsep23.srcspro.entity.User;
import comsep23.srcspro.enums.Role;
import comsep23.srcspro.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Optional<AuthUserDTO> login(LoginDTO dto) {
        return userRepository.findByEmail(dto.getEmail())
                .filter(user -> passwordEncoder.matches(
                        dto.getPassword(),
                        user.getPasswordHash()
                ))
                .map(user -> new AuthUserDTO(user.getId(), user.getRole()));
    }

    public void register(RegisterDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already in use");
        }

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Role.STUDENT);

        userRepository.save(user);
    }
}
