package project.meeting_service.service;

import project.meeting_service.dto.response.PollOptionResponseDTO;
import project.meeting_service.dto.response.PollResponseDTO;
import project.meeting_service.dto.response.PollResultResponseDTO;

import java.util.List;

public interface PollService {
    List<PollResponseDTO> findAllPollsOfAMeeting(Integer meetingId);

    PollResponseDTO findPollById(Integer pollId);

    List<PollOptionResponseDTO> findAllPollOptionsOfAPoll(Integer pollId);

    Integer createPoll(String title, Integer meetingId);

    PollResponseDTO startPoll(Integer pollId, Integer duration);

    String vote(Integer attendeeId, Integer pollOptionId);

    PollResultResponseDTO getPollResult(Integer pollId);

    boolean isVoted(Integer attendeeId, Integer pollId);
}
