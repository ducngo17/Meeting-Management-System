package project.meeting_service.dto.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MeetingResponseDTO {
    @Min(1)
    Integer meetingId;
    @NotBlank
    String meetingName;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String description;
    String location;
    String status;
}
