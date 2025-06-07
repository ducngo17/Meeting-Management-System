package project.meeting_service.mapper;

import org.mapstruct.Mapper;
import project.meeting_service.dto.response.PollOptionResponseDTO;
import project.meeting_service.entity.PollOption;

@Mapper(componentModel = "spring")
public interface PollOptionMapper {
    PollOptionResponseDTO toPollOptionDTO(PollOption pollOption);
}
