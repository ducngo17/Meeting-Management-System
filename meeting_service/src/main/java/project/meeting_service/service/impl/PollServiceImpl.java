package project.meeting_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import project.meeting_service.dto.response.PollOptionResponseDTO;
import project.meeting_service.dto.response.PollResponseDTO;
import project.meeting_service.dto.response.PollResultResponseDTO;
import project.meeting_service.entity.*;
import project.meeting_service.kafka.KafkaProducerService;
import project.meeting_service.mapper.PollMapper;
import project.meeting_service.mapper.PollOptionMapper;
import project.meeting_service.repository.*;
import project.meeting_service.service.PollService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PollServiceImpl implements PollService {
    private final PollRepository pollRepository;
    private final PollMapper pollMapper;
    private final PollOptionRepository pollOptionRepository;
    private final MeetingRepository meetingRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final PollOptionMapper pollOptionMapper;
    private final AttendeeRepository attendeeRepository;
    private final PollVoteRepostiory pollVoteRepostiory;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public PollServiceImpl(
            PollRepository pollRepository,
            PollMapper pollMapper,
            PollOptionRepository pollOptionRepository,
            MeetingRepository meetingRepository,
            SimpMessagingTemplate simpMessagingTemplate,
            PollOptionMapper pollOptionMapper,
            AttendeeRepository attendeeRepository,
            PollVoteRepostiory pollVoteRepostiory,
            KafkaProducerService kafkaProducerService
    ) {
        this.pollRepository = pollRepository;
        this.pollMapper = pollMapper;
        this.pollOptionRepository = pollOptionRepository;
        this.meetingRepository = meetingRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.pollOptionMapper = pollOptionMapper;
        this.attendeeRepository = attendeeRepository;
        this.pollVoteRepostiory = pollVoteRepostiory;
        this.kafkaProducerService = kafkaProducerService;

    }

    @Override
    public List<PollResponseDTO> findAllPollsOfAMeeting(Integer meetingId) {
        List<Poll> polls = pollRepository.findAllByMeeting_MeetingIdOrderByPollId(meetingId);
        return polls.stream().map(pollMapper::mapPollToPollResponseDTO).collect(Collectors.toList());
    }

    @Override
    public PollResponseDTO findPollById(Integer pollId) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
        Integer totalVotes = poll.getTotalVotes();
        return pollMapper.mapPollToPollResponseDTO(poll);
    }

    @Override
    public List<PollOptionResponseDTO> findAllPollOptionsOfAPoll(Integer pollId) {
        List<PollOption> pollOptions = pollOptionRepository.findAllByPoll_PollIdOrderByPollOptionId(pollId);
        return pollOptions.stream().map(pollOptionMapper::toPollOptionDTO).collect(Collectors.toList());
    }

    @Override
    public Integer createPoll(String title, Integer meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));
        Poll poll = new Poll();
        poll.setTitle(title);
        poll.setMeeting(meeting);
        poll.setStatus("created");
        pollRepository.save(poll);
        return poll.getPollId();
    }

    @Override
    public PollResponseDTO startPoll(Integer pollId, Integer duration) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
        poll.setStatus("active");
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = start.plusMinutes(duration);
        poll.setStartTime(start);
        poll.setEndTime(end);
        pollRepository.save(poll);
        return pollMapper.mapPollToPollResponseDTO(poll);
    }

    @Override
    public String vote(Integer attendeeId, Integer pollOptionId) {
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found"));
        PollOption pollOption = pollOptionRepository.findById(pollOptionId)
                .orElseThrow(() -> new RuntimeException("Poll option not found"));
        Integer pollId = pollOption.getPoll().getPollId();

        // Check if attendee has already voted in this poll
        if (pollVoteRepostiory.existsByAttendee_AttendeeIdAndPollOption_Poll_PollId(attendeeId, pollId)) {
            throw new IllegalArgumentException("Attendee has already voted in this poll");
        }

        PollVote newPollVote = new PollVote(attendee, pollOption, LocalDateTime.now());
        pollVoteRepostiory.save(newPollVote);

        // Update and save poll option vote count
        pollOption.setNumOfVotes(pollOption.getNumOfVotes() + 1);
        pollOptionRepository.save(pollOption);

        // Update and save poll total votes
        Poll poll = pollOption.getPoll();
        poll.setTotalVotes(poll.getTotalVotes() + 1);
        pollRepository.save(poll);

        // Send message to Kafka
        kafkaProducerService.sendMessage("meeting-" + poll.getMeeting().getMeetingId(), "poll-vote/" + poll.getMeeting().getMeetingId());

        return "Successfully voted";
    }

    @Override
    public PollResultResponseDTO getPollResult(Integer pollId) {
        Poll poll = pollRepository.findById(pollId)
                .orElseThrow(() -> new RuntimeException("Poll not found"));
        List<PollOption> pollOptions = pollOptionRepository.findAllByPoll_PollId(pollId);

        PollResultResponseDTO result = new PollResultResponseDTO();
        result.setPollId(pollId);
        result.setTitle(poll.getTitle());

        Map<Integer, Integer> voteCounts = new HashMap<>();
        Map<Integer, String> optionTexts = new HashMap<>();

        for (PollOption option : pollOptions) {
            voteCounts.put(option.getPollOptionId(), option.getNumOfVotes());
            optionTexts.put(option.getPollOptionId(), option.getText());
        }

        result.setVoteCounts(voteCounts);
        result.setOptionTexts(optionTexts);

        return result;
    }

    //send the latest poll results to all connected WebSocket clients subscribed to the topic /topic/poll-results/{pollId}.
//    private void broadcastPollResults(Integer pollId) {
//        PollResultResponseDTO result = getPollResult(pollId);
//        simpMessagingTemplate.convertAndSend("/topic/poll-results/" + pollId, result);
//    }

    @Override
    public boolean isVoted(Integer attendeeId, Integer pollId) {
        return pollVoteRepostiory.existsByAttendee_AttendeeIdAndPollOption_Poll_PollId(attendeeId, pollId);
    }
}
