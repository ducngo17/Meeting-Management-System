package project.meeting_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.meeting_service.dto.response.MeetingResponseDTO;
import project.meeting_service.entity.Meeting;
import project.meeting_service.mapper.MeetingMapper;
import project.meeting_service.repository.AttendeeRepository;
import project.meeting_service.repository.MeetingRepository;
import project.meeting_service.service.MeetingService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingServiceImpl implements MeetingService {
    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;

    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingMapper meetingMapper) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
    }

    @Override
    public List<MeetingResponseDTO> getAllMeetings() {
        List<Meeting> meetings = meetingRepository.findAll();
        return meetings.stream().map(meetingMapper::toMeetingResponseDTO).collect(Collectors.toList());
    }

    @Override
    public MeetingResponseDTO getMeetingById(Integer meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(() -> new RuntimeException("Meeting not found"));
        return meetingMapper.toMeetingResponseDTO(meeting);
    }
}
