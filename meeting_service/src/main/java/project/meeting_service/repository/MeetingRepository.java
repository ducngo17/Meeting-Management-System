package project.meeting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.meeting_service.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    //check whether an attendee attend a meeting
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM Meeting m JOIN m.attendees a " +
            "WHERE a.attendeeId = :attendeeId AND m.meetingId = :meetingId")
    boolean isAttend(@Param("attendeeId") Integer attendeeId, @Param("meetingId") Integer meetingId);

}
