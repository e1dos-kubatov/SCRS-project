package comsep23.srcspro.dto.complaint;

import comsep23.srcspro.enums.ComplaintCategory;
import comsep23.srcspro.enums.ComplaintStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ComplaintListDTO {

    private Long id;
    private String title;
    private ComplaintStatus status;
    private ComplaintCategory category;

    private String departmentName;

    private int upvoteCount;

    private LocalDateTime createdAt;
}

