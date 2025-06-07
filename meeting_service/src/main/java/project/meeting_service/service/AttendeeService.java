package project.meeting_service.service;

import project.meeting_service.dto.response.LoginResponseDTO;

public interface AttendeeService {
    LoginResponseDTO login(String email, String password);

    String attendMeeting(Integer attendeeId, Integer meetingId);
}
