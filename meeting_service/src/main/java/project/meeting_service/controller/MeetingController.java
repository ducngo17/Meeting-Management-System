package project.meeting_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.meeting_service.dto.request.MeetingRequestDTO;
import project.meeting_service.dto.response.MeetingResponseDTO;
import project.meeting_service.service.MeetingService;

import java.util.List;

@RestController
@RequestMapping("meetings")
@Validated
public class MeetingController {
    private final MeetingService meetingService;

    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping()
    public ResponseEntity<List<MeetingResponseDTO>> getAllmeetings() {
        return ResponseEntity.ok(meetingService.getAllMeetings());
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<MeetingResponseDTO> getmeetingById(@PathVariable Integer meetingId) {
        return ResponseEntity.ok(meetingService.getMeetingById(meetingId));
    }



}
