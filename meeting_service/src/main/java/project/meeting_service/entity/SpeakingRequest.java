package project.meeting_service.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "meeting_speech")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpeakingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    @JsonManagedReference
    Attendee attendee;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    @JsonManagedReference
    Meeting meeting;

    LocalDateTime requestTime;

    public SpeakingRequest(Attendee attendee, Meeting meeting, LocalDateTime requestTime) {
        this.attendee = attendee;
        this.meeting = meeting;
        this.requestTime = requestTime;
    }
}

