package project.meeting_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PollOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer pollOptionId;

    @Column(name = "text")
    String text;

    @Column(name = "num_of_votes")
    Integer numOfVotes;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    @JsonBackReference
    Poll poll;

    @OneToMany(mappedBy = "pollOption")
    @JsonManagedReference
    List<PollVote> pollVotes;

}
