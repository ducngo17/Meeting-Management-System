package project.meeting_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PollVoteRequestDTO {
    Integer attendeeId;
    Integer pollOptionId;
}
