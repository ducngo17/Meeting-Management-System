package project.meeting_service.dto.request;

import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class MeetingAttendanceRequestDTO {
    @Min(1)
    Integer attendeeId;
    @Min(1)
    Integer meetingId;
}
