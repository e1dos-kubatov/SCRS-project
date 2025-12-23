package comsep23.srcspro.dto.department;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentDTO {

    private Long id;
    private String name;
    private String description;

    private long complaintCount;
}

