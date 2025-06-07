package project.meeting_service.service;

import project.meeting_service.dto.response.SpeakingRequestResponseDTO;

import java.util.List;

public interface SpeakingRequestService {
    List<SpeakingRequestResponseDTO> getAllSpeakingRequests(Integer meetingId);

    boolean isRequest(Integer attendee, Integer meetingId);

    String requestSpeaking(Integer attendeeId, Integer conferenceId);
}
