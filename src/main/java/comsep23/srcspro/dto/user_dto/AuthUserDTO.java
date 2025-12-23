package comsep23.srcspro.dto.user_dto;

import comsep23.srcspro.enums.Role;

public record AuthUserDTO(Long id, Role role) {
    public boolean isStaff() {
        return role == Role.STAFF || role == Role.ADMIN;
    }
}

