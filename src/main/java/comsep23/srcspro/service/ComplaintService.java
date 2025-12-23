package comsep23.srcspro.service;

import comsep23.srcspro.dto.complaint.ComplaintCreateDTO;
import comsep23.srcspro.dto.complaint.ComplaintDetailDTO;
import comsep23.srcspro.dto.complaint.ComplaintListDTO;
import comsep23.srcspro.dto.department.PageResponseDTO;
import comsep23.srcspro.entity.Complaint;
import comsep23.srcspro.entity.ComplaintStatusHistory;
import comsep23.srcspro.entity.Department;
import comsep23.srcspro.entity.User;
import comsep23.srcspro.enums.ComplaintCategory;
import comsep23.srcspro.enums.ComplaintStatus;
import comsep23.srcspro.mapper.ComplaintMapper;
import comsep23.srcspro.repositories.ComplaintRepository;
import comsep23.srcspro.repositories.DepartmentRepository;
import comsep23.srcspro.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintMapper complaintMapper;
    private final UserRepository userRepository;

    // =========================
    // STAFF DASHBOARD (NEW)
    // =========================
    public List<ComplaintListDTO> getNewComplaintsForStaff() {
        return complaintRepository
                .findByStatusOrderByCreatedAtDesc(ComplaintStatus.NEW)
                .stream()
                .map(complaintMapper::toListDTO)
                .toList();
    }

    // =========================
    // STAFF PAGED LIST
    // =========================
    public PageResponseDTO<ComplaintListDTO> getComplaintsForStaffPaged(
            ComplaintStatus status,
            ComplaintCategory category,
            int page,
            int size
    ) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.DESC, "createdAt")
        );

        Page<Complaint> complaints;

        if (status != null && category != null) {
            complaints = complaintRepository
                    .findByStatusAndCategory(status, category, pageable);
        } else if (status != null) {
            complaints = complaintRepository.findByStatus(status, pageable);
        } else {
            complaints = complaintRepository.findAll(pageable);
        }

        return new PageResponseDTO<>(
                complaints.getContent()
                        .stream()
                        .map(complaintMapper::toListDTO)
                        .toList(),
                complaints.getNumber(),
                complaints.getSize(),
                complaints.getTotalElements(),
                complaints.getTotalPages()
        );
    }

    // =========================
    // STAFF DETAIL VIEW
    // =========================
    public ComplaintDetailDTO getComplaintForStaff(Long complaintId) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new IllegalStateException("Complaint not found"));

        return complaintMapper.toDetailDTO(complaint);
    }

    // =========================
    // UPDATE STATUS
    // =========================
    public void updateStatus(
            Long complaintId,
            ComplaintStatus newStatus,
            Long staffId,
            String note
    ) {
        Complaint complaint = complaintRepository.findById(complaintId)
                .orElseThrow(() -> new IllegalStateException("Complaint not found"));

        User staff = userRepository.findById(staffId)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        complaint.setStatus(newStatus);

        // (если есть history)
        ComplaintStatusHistory history = new ComplaintStatusHistory();
        history.setComplaint(complaint);
        history.setPerformedBy(staff);
        history.setStatus(newStatus);
        history.setNote(note);

        complaint.getHistory().add(history);
    }
}
