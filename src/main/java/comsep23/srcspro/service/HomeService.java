package comsep23.srcspro.service;

import comsep23.srcspro.dto.complaint.HomeComplaintDTO;
import comsep23.srcspro.enums.ComplaintStatus;
import comsep23.srcspro.repositories.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private final ComplaintRepository complaintRepository;

    public List<HomeComplaintDTO> getRecentResolvedComplaints() {
        return complaintRepository
                .findTop5ByStatusOrderByResolutionAnnouncedAtDesc(
                        ComplaintStatus.RESOLUTION_ANNOUNCED
                )
                .stream()
                .map(c -> new HomeComplaintDTO(
                        c.getId(),
                        c.getTitle(),
                        c.getCategory(),
                        c.getDepartment().getName(),
                        c.getResolutionAnnouncedAt()
                ))
                .toList();
    }
}

