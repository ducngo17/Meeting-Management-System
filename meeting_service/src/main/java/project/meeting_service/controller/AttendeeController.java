package project.meeting_service.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.meeting_service.dto.request.LoginRequestDTO;
import project.meeting_service.dto.request.MeetingAttendanceRequestDTO;
import project.meeting_service.dto.response.LoginResponseDTO;
import project.meeting_service.service.AttendeeService;
import project.meeting_service.service.MeetingService;

@RestController
@Validated
@RequestMapping("attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;
    private final MeetingService meetingService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService, MeetingService meetingService) {
        this.attendeeService = attendeeService;
        this.meetingService = meetingService;
    }

    @GetMapping("is-attended")
    public ResponseEntity<Boolean> isAttended(@RequestParam Integer attendeeId, @RequestParam Integer meetingId) {
        return ResponseEntity.ok(attendeeService.isAttended(attendeeId, meetingId));
    }


    @PostMapping("/attend")
    public ResponseEntity<String> attendMeeting(@RequestBody @Valid MeetingAttendanceRequestDTO meetingAttendanceRequestDTO) {
        Integer meetingId = meetingAttendanceRequestDTO.getMeetingId();
        Integer attendeeId = meetingAttendanceRequestDTO.getAttendeeId();
        return ResponseEntity.ok(attendeeService.attendMeeting(attendeeId, meetingId));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(attendeeService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));
    }
}
