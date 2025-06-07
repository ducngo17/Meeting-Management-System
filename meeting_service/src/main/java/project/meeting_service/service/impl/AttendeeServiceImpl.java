package project.meeting_service.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.meeting_service.dto.response.LoginResponseDTO;
import project.meeting_service.entity.Attendee;
import project.meeting_service.entity.Meeting;
import project.meeting_service.repository.AttendeeRepository;
import project.meeting_service.repository.MeetingRepository;
import project.meeting_service.service.AttendeeService;

import java.util.Optional;

@Service
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final MeetingRepository meetingRepository;

    @Autowired
    public AttendeeServiceImpl(AttendeeRepository attendeeRepository, MeetingRepository meetingRepository) {
        this.attendeeRepository = attendeeRepository;
        this.meetingRepository = meetingRepository;
    }

    @Override
    public LoginResponseDTO login(String email, String password) {
        Attendee attendee = attendeeRepository.findAttendeeByEmail(email)
                .orElseThrow(() -> new RuntimeException("Attendee not found"));
        if (!attendee.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        return LoginResponseDTO.builder()
                .attendeeId(attendee.getAttendeeId())
                .message("Login successfully")
                .build();

    }

    @Override
    public String attendMeeting(Integer attendeeId, Integer meetingId) {
        Attendee attendee = attendeeRepository.findById(attendeeId).orElseThrow(
                () -> new RuntimeException("Attendee not found")
        );
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(
                () -> new RuntimeException("Meeting not found")
        );
        if (meetingRepository.isAttend(attendeeId, meetingId)) {
            return "Already attended this meeting";
        }
        else {
            meeting.getAttendees().add(attendee);
            meetingRepository.save(meeting);
            return "Attended this meeting successfully";
        }
    }
}
