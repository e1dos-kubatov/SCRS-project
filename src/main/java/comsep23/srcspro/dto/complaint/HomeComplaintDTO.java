package comsep23.srcspro.dto.complaint;


import comsep23.srcspro.enums.ComplaintCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class HomeComplaintDTO {

    private Long id;
    private String title;
    private ComplaintCategory category;
    private String departmentName;
    private LocalDateTime resolvedAt;
}

