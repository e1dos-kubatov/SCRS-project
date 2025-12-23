package comsep23.srcspro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "complaint_likes",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_complaint_user",
                columnNames = {"complaint_id", "user_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor
public class ComplaintLike extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Complaint complaint;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private User user;
}
