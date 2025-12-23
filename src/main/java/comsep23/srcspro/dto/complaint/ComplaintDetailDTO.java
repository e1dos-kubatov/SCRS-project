package comsep23.srcspro.dto.complaint;

import comsep23.srcspro.enums.ComplaintCategory;
import comsep23.srcspro.enums.ComplaintStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ComplaintDetailDTO {

    private Long id;
    private String title;
    private String description;

    private ComplaintCategory category;
    private ComplaintStatus status;

    private String departmentName;

    private UserSummaryDTO student;
    private UserSummaryDTO assignedTo;

    private int upvoteCount;
    private String photoName;

    private String resolutionNote;
    private LocalDateTime resolutionAnnouncedAt;
    private LocalDateTime confirmedAt;

    private LocalDateTime createdAt;

    private List<ComplaintHistoryDTO> history;
}

