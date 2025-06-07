package project.meeting_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer pollId;

    @Column(name = "title")
    String title;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "end_time")
    LocalDateTime endTime;

    @Column(name = "status")
    String status;

    @Column(name = "total_votes")
    Integer totalVotes;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    @JsonBackReference
    Meeting meeting;

    @OneToMany(mappedBy = "poll")
    @JsonManagedReference
    List<PollOption> pollOptions;

}
