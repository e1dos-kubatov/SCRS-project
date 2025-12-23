package comsep23.srcspro.dto.complaint;

import comsep23.srcspro.enums.ComplaintStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComplaintUpdateStatusDTO {

    @NotNull
    private ComplaintStatus status;

    @Size(max = 1000)
    private String note;
}

