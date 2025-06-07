package project.meeting_service.mapper;

import org.mapstruct.Mapper;
import project.meeting_service.dto.response.PollResponseDTO;
import project.meeting_service.entity.Poll;

@Mapper(componentModel = "spring")
public interface PollMapper {
    public PollResponseDTO mapPollToPollResponseDTO(Poll poll);
}
