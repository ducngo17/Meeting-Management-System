package project.meeting_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpeakingRequestResponseDTO {
    Integer id;
    Integer attendeeId;
    String attendeeName;
    LocalDateTime requestTime;
}
