package comsep23.srcspro.dto.user_dto;

import comsep23.srcspro.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserSummaryDTO {

    private Long id;
    private String fullName;
    private Role role;
}

