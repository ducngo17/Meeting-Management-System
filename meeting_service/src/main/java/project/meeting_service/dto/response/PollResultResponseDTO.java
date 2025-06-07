package project.meeting_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class PollResultResponseDTO {
    Integer pollId;
    String title;
    Map<Integer, Integer> voteCounts; // optionId -> count
    Map<Integer, String> optionTexts; // optionId -> optionText
}