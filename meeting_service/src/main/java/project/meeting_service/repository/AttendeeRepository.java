package project.meeting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.meeting_service.entity.Attendee;

import java.util.Optional;

public interface AttendeeRepository extends JpaRepository<Attendee, Integer> {


    Attendee findAttendeeByAttendeeId(Integer attendeeId);

    Optional<Attendee> findAttendeeByEmail(String email);
}
