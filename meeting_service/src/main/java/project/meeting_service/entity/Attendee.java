package project.meeting_service.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer attendeeId;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @ManyToMany(mappedBy = "attendees")
    @JsonBackReference
    List<Meeting> meetings;

    @OneToMany(mappedBy = "attendee")
    @JsonBackReference
    List<SpeakingRequest> speakingRequests;

    @OneToMany(mappedBy = "attendee")
    @JsonManagedReference
    List<PollVote> pollVotes;
}


