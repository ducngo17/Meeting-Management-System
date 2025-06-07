package project.meeting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.meeting_service.entity.Poll;

import java.util.List;

public interface PollRepository extends JpaRepository<Poll, Integer> {
    List<Poll> findAllByMeeting_MeetingIdOrderByPollId(Integer meetingId);
}
