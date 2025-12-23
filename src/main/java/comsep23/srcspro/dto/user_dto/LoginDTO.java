package comsep23.srcspro.dto.user_dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
