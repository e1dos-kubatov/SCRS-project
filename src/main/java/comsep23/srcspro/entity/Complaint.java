package comsep23.srcspro.entity;

import comsep23.srcspro.enums.ComplaintCategory;
import comsep23.srcspro.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "complaints",
        indexes = {
                @Index(name = "idx_complaint_status", columnList = "status"),
                @Index(name = "idx_complaint_department", columnList = "department_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Complaint extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintCategory category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    private User assignedTo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintStatus status = ComplaintStatus.NEW;

    private String photoName;

    @Column(nullable = false)
    private int upvoteCount = 0;

    @Column(length = 2000)
    private String resolutionNote;

    private LocalDateTime resolutionAnnouncedAt;
    private LocalDateTime confirmedAt;

    @OneToMany(
            mappedBy = "complaint",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy("changedAt DESC")
    private List<ComplaintStatusHistory> history;
}
