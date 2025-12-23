package comsep23.srcspro.mapper;

import comsep23.srcspro.dto.user_dto.UserResponseDTO;
import comsep23.srcspro.dto.user_dto.UserSummaryDTO;
import comsep23.srcspro.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserSummaryDTO toSummaryDTO(User user);

    @Mapping(source = "department.name", target = "departmentName")
    UserResponseDTO toResponseDTO(User user);
}

