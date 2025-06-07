package project.meeting_service.service;

import project.meeting_service.dto.response.MeetingResponseDTO;

import java.util.List;

public interface MeetingService {

    List<MeetingResponseDTO> getAllMeetings();

    MeetingResponseDTO getMeetingById(Integer meetingId);


}
