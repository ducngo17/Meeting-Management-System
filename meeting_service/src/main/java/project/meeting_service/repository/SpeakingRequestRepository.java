package project.meeting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.meeting_service.entity.Attendee;
import project.meeting_service.entity.Meeting;
import project.meeting_service.entity.SpeakingRequest;

import java.util.List;

public interface SpeakingRequestRepository extends JpaRepository<SpeakingRequest, Integer> {
    List<SpeakingRequest> findSpeakingRequestsByMeeting_MeetingId(Integer meetingId);

    List<SpeakingRequest> findSpeakingRequestsByMeeting(Meeting meeting);

    SpeakingRequest findSpeakingRequestsByAttendee_AttendeeIdAndMeeting_MeetingId(Integer attendeeId, Integer meetingId);
}
