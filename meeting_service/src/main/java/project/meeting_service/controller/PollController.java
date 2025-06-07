package project.meeting_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.meeting_service.dto.request.PollRequestDTO;
import project.meeting_service.dto.request.PollVoteRequestDTO;
import project.meeting_service.dto.response.PollOptionResponseDTO;
import project.meeting_service.dto.response.PollResponseDTO;
import project.meeting_service.service.PollService;

import java.util.List;

@RestController()
@RequestMapping("polls")
@Validated

public class PollController {
    private final PollService pollService;

    @Autowired
    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @GetMapping("/meetings/{meetingId}")
    public ResponseEntity<List<PollResponseDTO>> findAllPollsOfAmeeting(@PathVariable @Valid Integer meetingId) {
        return ResponseEntity.ok(pollService.findAllPollsOfAMeeting(meetingId));
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<PollResponseDTO> getPollById(@PathVariable @Valid Integer pollId) {
        PollResponseDTO poll = pollService.findPollById(pollId);
        return ResponseEntity.ok(poll);
    }

    @GetMapping("/{pollId}/poll-options")
    public ResponseEntity<List<PollOptionResponseDTO>> findAllPollOptionsOfAPoll(@PathVariable Integer pollId) {
        return ResponseEntity.ok(pollService.findAllPollOptionsOfAPoll(pollId));
    }

    @PostMapping("/create")
    public ResponseEntity<Integer> createPoll(@Valid @RequestBody PollRequestDTO pollRequestDTO) {
        String title = pollRequestDTO.getTitle();
        Integer meetingId = pollRequestDTO.getMeetingId();
        return ResponseEntity.ok(pollService.createPoll(title, meetingId));
    }

    @PutMapping("/start/{pollId}")
    public ResponseEntity<PollResponseDTO> startPoll(@Valid @PathVariable Integer pollId, @Valid @RequestParam Integer duration) {
        return ResponseEntity.ok(pollService.startPoll(pollId, duration));
    }

    @PostMapping("/vote")
    public ResponseEntity<String> vote(@Validated @RequestBody PollVoteRequestDTO pollVoteRequestDTO) {
        Integer pollOptionId = pollVoteRequestDTO.getPollOptionId();
        Integer attendeeId = pollVoteRequestDTO.getAttendeeId();
        return ResponseEntity.ok(pollService.vote(attendeeId, pollOptionId));

    }

    @GetMapping("/is-voted")
    public ResponseEntity<Boolean> isVoted(@RequestParam @Min(1) Integer attendeeId, @RequestParam @Min(1) Integer pollId) {
        return ResponseEntity.ok(pollService.isVoted(attendeeId, pollId));
    }
}
