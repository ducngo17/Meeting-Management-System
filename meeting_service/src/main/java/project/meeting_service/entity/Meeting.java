package project.meeting_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer meetingId;

    @Column(name ="meeting_name")
    String meetingName;

    @Column(name = "start_time")
    LocalDateTime startTime;

    @Column(name = "end_time")
    LocalDateTime endTime;

    @Column(name = "description")
    String description;

    @Column(name = "location")
    String location;

    @Column(name = "status")
    String status;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "meeting_attendance",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "attendee_id")
    )
    List<Attendee> attendees;

    @OneToMany(mappedBy = "meeting")
    @JsonBackReference
    List<SpeakingRequest> speakingRequests;

    @OneToMany(mappedBy = "meeting")
    @JsonManagedReference
    List<Poll> polls;
}
