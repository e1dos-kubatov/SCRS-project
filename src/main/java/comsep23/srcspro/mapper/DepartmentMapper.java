package comsep23.srcspro.mapper;

import comsep23.srcspro.dto.department.DepartmentDTO;
import comsep23.srcspro.dto.department.DepartmentSummaryDTO;
import comsep23.srcspro.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentSummaryDTO toSummaryDTO(Department department);

    @Mapping(target = "complaintCount",
            expression = "java(department.getComplaints() == null ? 0 : department.getComplaints().size())")
    DepartmentDTO toDTO(Department department);
}
