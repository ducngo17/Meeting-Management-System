package project.meeting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.meeting_service.entity.PollVote;

public interface PollVoteRepostiory extends JpaRepository<PollVote, Integer> {
    PollVote findByAttendee_AttendeeIdAndPollOption_PollOptionId(Integer attendeeId, Integer pollOptionId);

    boolean existsByAttendee_AttendeeIdAndPollOption_Poll_PollId(Integer attendeeId, Integer pollId);
}
