package comsep23.srcspro.dto.complaint;

import comsep23.srcspro.enums.ComplaintStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ComplaintHistoryDTO {

    private ComplaintStatus status;
    private String note;

    private String performedByName;
    private LocalDateTime changedAt;
}

