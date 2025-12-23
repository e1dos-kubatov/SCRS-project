package comsep23.srcspro.mapper;

import comsep23.srcspro.dto.complaint.ComplaintCreateDTO;
import comsep23.srcspro.dto.complaint.ComplaintDetailDTO;
import comsep23.srcspro.dto.complaint.ComplaintHistoryDTO;
import comsep23.srcspro.dto.complaint.ComplaintListDTO;
import comsep23.srcspro.entity.Complaint;
import comsep23.srcspro.entity.ComplaintStatusHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComplaintMapper {

    // ENTITY → LIST DTO
    @Mapping(source = "department.name", target = "departmentName")
    ComplaintListDTO toListDTO(Complaint complaint);

    List<ComplaintListDTO> toListDTOs(List<Complaint> complaints);

    // ENTITY → DETAIL DTO
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "student", target = "student")
    @Mapping(source = "assignedTo", target = "assignedTo")
    @Mapping(source = "history", target = "history")
    ComplaintDetailDTO toDetailDTO(Complaint complaint);

    // HISTORY
    @Mapping(source = "performedBy.fullName", target = "performedByName")
    ComplaintHistoryDTO toHistoryDTO(ComplaintStatusHistory history);

    List<ComplaintHistoryDTO> toHistoryDTOs(List<ComplaintStatusHistory> history);

    // CREATE DTO → ENTITY
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "student", ignore = true)
    @Mapping(target = "status", constant = "NEW")
    @Mapping(target = "upvoteCount", constant = "0")
    Complaint fromCreateDTO(ComplaintCreateDTO dto);
}

