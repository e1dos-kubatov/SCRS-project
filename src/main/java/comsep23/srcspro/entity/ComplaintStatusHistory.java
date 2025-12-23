package comsep23.srcspro.entity;

import comsep23.srcspro.enums.ComplaintStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaint_status_history")
@Getter
@Setter
@NoArgsConstructor
public class ComplaintStatusHistory extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Complaint complaint;

    @ManyToOne(fetch = FetchType.LAZY)
    private User performedBy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ComplaintStatus status;

    @Column(length = 1000)
    private String note;

    private LocalDateTime changedAt;

    @PrePersist
    void init() {
        changedAt = LocalDateTime.now();
    }
}
