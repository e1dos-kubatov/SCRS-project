package comsep23.srcspro.dto.user_dto;

import comsep23.srcspro.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String fullName;
    private String email;
    private Role role;

    private String departmentName;
    private boolean enabled;

    private LocalDateTime createdAt;
}

