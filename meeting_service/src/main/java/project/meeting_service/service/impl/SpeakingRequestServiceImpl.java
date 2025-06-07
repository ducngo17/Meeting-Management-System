package project.meeting_service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import project.meeting_service.dto.response.SpeakingRequestResponseDTO;
import project.meeting_service.entity.Attendee;
import project.meeting_service.entity.Meeting;
import project.meeting_service.entity.SpeakingRequest;
import project.meeting_service.kafka.KafkaConsumerService;
import project.meeting_service.kafka.KafkaProducerService;
import project.meeting_service.mapper.SpeakingRequestMapper;
import project.meeting_service.repository.AttendeeRepository;
import project.meeting_service.repository.MeetingRepository;
import project.meeting_service.repository.SpeakingRequestRepository;
import project.meeting_service.service.SpeakingRequestService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpeakingRequestServiceImpl implements SpeakingRequestService {
    private final SpeakingRequestRepository speakingRequestRepository;
    private final AttendeeRepository attendeeRepository;
    private final MeetingRepository meetingRepository;
    private final SpeakingRequestMapper speakingRequestMapper;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public SpeakingRequestServiceImpl(
            SpeakingRequestRepository speakingRequestRepository,
            AttendeeRepository attendeeRepository,
            MeetingRepository meetingRepository,
            SpeakingRequestMapper speakingRequestMapper,
            SimpMessagingTemplate simpMessagingTemplate,
            KafkaProducerService kafkaProducerService) {
        this.speakingRequestRepository = speakingRequestRepository;
        this.attendeeRepository = attendeeRepository;
        this.meetingRepository = meetingRepository;
        this.speakingRequestMapper = speakingRequestMapper;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.kafkaProducerService = kafkaProducerService;
    }

    @Override
    public List<SpeakingRequestResponseDTO> getAllSpeakingRequests(Integer meetingId) {
        Meeting meeting = meetingRepository.findById(meetingId).orElseThrow(
                () -> new RuntimeException("Meeting not found")
        );
        List<SpeakingRequest> speakingRequests = speakingRequestRepository.findSpeakingRequestsByMeeting(meeting);
        return speakingRequests.stream().map(speakingRequestMapper::toSpeakingRequestResponseDTO).collect(Collectors.toList());
    }

    @Override
    public boolean isRequest(Integer attendeeId, Integer meetingId) {
        SpeakingRequest speakingRequest = speakingRequestRepository.findSpeakingRequestsByAttendee_AttendeeIdAndMeeting_MeetingId(attendeeId, meetingId);
        if (speakingRequest == null) {
            return false;
        };
        return true;
    }

    @Override
    public String requestSpeaking(Integer attendeeId, Integer meetingId) {
        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found"));
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));
        if (!meetingRepository.isAttend(attendeeId, meetingId)) {
            throw new IllegalArgumentException("This attendee has not attended this meeting");
        }
        if (isRequest(attendeeId, meetingId)) {
            throw new IllegalArgumentException("This attendee has requested speaking");
        }
        SpeakingRequest speakingRequest = new SpeakingRequest(attendee, meeting, LocalDateTime.now());
        speakingRequestRepository.save(speakingRequest);

        kafkaProducerService.sendMessage("meeting-" + meetingId, "request-speaking/" + meetingId);

        return "Speaking request submitted successfully";
    }


//    private void broadcastSpeakingRequest(SpeakingRequest speakingRequest) {
//        SpeakingRequestResponseDTO speakingRequestResponseDTO = speakingRequestMapper.toSpeakingRequestResponseDTO(speakingRequest);
//        simpMessagingTemplate.convertAndSend("/topic/speaking-requests", speakingRequestResponseDTO );
//    }
}
