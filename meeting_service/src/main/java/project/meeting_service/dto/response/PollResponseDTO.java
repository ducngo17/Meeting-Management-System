package project.meeting_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PollResponseDTO {
    Integer pollId;
    String title;
    LocalDateTime startTime;
    LocalDateTime endTime;
    Integer totalVotes;
}
