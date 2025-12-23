package comsep23.srcspro.service;

import comsep23.srcspro.dto.department.DepartmentSummaryDTO;
import comsep23.srcspro.mapper.DepartmentMapper;
import comsep23.srcspro.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public List<DepartmentSummaryDTO> getAllSummaries() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toSummaryDTO)
                .toList();
    }
}

