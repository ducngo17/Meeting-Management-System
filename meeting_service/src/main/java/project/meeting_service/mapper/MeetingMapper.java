package project.meeting_service.mapper;

import org.mapstruct.Mapper;
import project.meeting_service.dto.response.MeetingResponseDTO;
import project.meeting_service.entity.Meeting;

@Mapper(componentModel = "spring")
public interface MeetingMapper {
    public MeetingResponseDTO toMeetingResponseDTO(Meeting meeting);
}
