package project.meeting_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.meeting_service.entity.PollOption;

import java.util.List;

public interface PollOptionRepository extends JpaRepository<PollOption, Integer> {
    List<PollOption> findAllByPoll_PollId(Integer pollPollId);

    List<PollOption> findAllByPoll_PollIdOrderByPollOptionId(Integer pollId);
}
