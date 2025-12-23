package comsep23.srcspro.dto.complaint;

import comsep23.srcspro.enums.ComplaintCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintCreateDTO {

    @NotBlank
    @Size(max = 150)
    private String title;

    @NotBlank
    @Size(max = 2000)
    private String description;

    @NotNull
    private ComplaintCategory category;

    @NotNull
    private Long departmentId;
}

