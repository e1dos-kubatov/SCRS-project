package comsep23.srcspro.repositories;

import comsep23.srcspro.entity.Complaint;
import comsep23.srcspro.enums.ComplaintCategory;
import comsep23.srcspro.enums.ComplaintStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    // Home page: recently resolved complaints (for landing page)
    List<Complaint> findTop5ByStatusOrderByResolutionAnnouncedAtDesc(ComplaintStatus status);

    // Student: "My complaints"
    List<Complaint> findByStudentIdOrderByCreatedAtDesc(Long studentId);

    // Staff/Admin filtering
    List<Complaint> findByStatusOrderByCreatedAtDesc(ComplaintStatus status);

    List<Complaint> findByCategoryOrderByCreatedAtDesc(ComplaintCategory category);

    List<Complaint> findByStatusAndCategoryOrderByCreatedAtDesc(
            ComplaintStatus status,
            ComplaintCategory category
    );

    Page<Complaint> findByStudentId(
            Long studentId,
            Pageable pageable
    );

    Page<Complaint> findByStatus(
            ComplaintStatus status,
            Pageable pageable
    );

    Page<Complaint> findByStatusAndCategory(
            ComplaintStatus status,
            ComplaintCategory category,
            Pageable pageable
    );
}
