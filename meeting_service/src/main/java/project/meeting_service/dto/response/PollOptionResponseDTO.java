package project.meeting_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PollOptionResponseDTO {
    Integer pollOptionId;
    String text;
    Integer numOfVotes;
}
