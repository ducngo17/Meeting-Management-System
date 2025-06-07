package project.meeting_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import project.meeting_service.dto.response.SpeakingRequestResponseDTO;
import project.meeting_service.entity.SpeakingRequest;

@Mapper(componentModel = "spring")
public interface SpeakingRequestMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "attendee.name", target = "attendeeName"),
            @Mapping(source = "attendee.attendeeId", target = "attendeeId")
    })
    SpeakingRequestResponseDTO toSpeakingRequestResponseDTO(SpeakingRequest speakingRequest);
}
