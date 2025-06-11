package project.meeting_service.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final SimpMessagingTemplate messagingTemplate;

    public KafkaConsumerService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(topicPattern = "meeting-.*")
    public void listen(String message) {
        // Extract meetingId from message, e.g., "meeting/123/..."
//        System.out.println("Received message from Kafka: " + message);

        String meetingId = extractMeetingId(message);
        messagingTemplate.convertAndSend("/topic/meeting/" + meetingId, message);
//        System.out.println("Message sent to WebSocket topic: /topic/meeting/" + meetingId);
    }

    private String extractMeetingId(String message) {
        // Implement logic to extract meetingId from the message
        // For example, if message is "meeting/123/...", extract "123"
        return message.split("/")[1];
    }
}