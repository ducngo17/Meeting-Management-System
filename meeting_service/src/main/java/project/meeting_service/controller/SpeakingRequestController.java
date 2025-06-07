package project.meeting_service.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.meeting_service.dto.request.SpeakingRequestRequestDTO;
import project.meeting_service.dto.response.SpeakingRequestResponseDTO;
import project.meeting_service.service.SpeakingRequestService;

import java.util.List;

@RestController
@RequestMapping("speaking-requests")
@Validated
public class SpeakingRequestController {
    private final SpeakingRequestService speakingRequestService;

    @Autowired
    public SpeakingRequestController(SpeakingRequestService speakingRequestService) {
        this.speakingRequestService = speakingRequestService;
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<List<SpeakingRequestResponseDTO>> getAllSpeakingRequests(@PathVariable @Min(1) Integer meetingId) {
        return ResponseEntity.ok(speakingRequestService.getAllSpeakingRequests(meetingId));
    }

    @PostMapping()
    public ResponseEntity<String> requestSpeaking(@Valid @RequestBody SpeakingRequestRequestDTO speakingRequestRequestDTO) {
        Integer attendeeId = speakingRequestRequestDTO.getAttendeeId();
        Integer meetingId = speakingRequestRequestDTO.getMeetingId();
        return ResponseEntity.ok(speakingRequestService.requestSpeaking(attendeeId, meetingId));
    }

    @GetMapping("/is-requested")
    public ResponseEntity<Boolean> isRequest(@RequestParam @Min(1) Integer attendeeId, @RequestParam @Min(1) Integer meetingId) {
        return ResponseEntity.ok(speakingRequestService.isRequest(attendeeId, meetingId));
    }
}
