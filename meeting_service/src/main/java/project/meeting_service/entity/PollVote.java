package project.meeting_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PollVote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer pollVoteId;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    @JsonManagedReference
    Attendee attendee;

    @ManyToOne
    @JoinColumn(name="poll_option_id")
    @JsonManagedReference
    PollOption pollOption;

    LocalDateTime voteTime;

    public PollVote(Attendee attendee, PollOption pollOption, LocalDateTime voteTime) {
        this.attendee = attendee;
        this.pollOption = pollOption;
        this.voteTime = voteTime;
    }
}
