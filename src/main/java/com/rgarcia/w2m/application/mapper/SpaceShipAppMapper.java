package com.rgarcia.w2m.application.mapper;

import com.rgarcia.w2m.application.dto.request.CreateSpaceShipDto;
import com.rgarcia.w2m.application.dto.response.GetSpaceShipResponseDto;
import com.rgarcia.w2m.application.dto.response.GetSpaceShipsPaginationResponseDto;
import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.domain.model.SpaceShip;
import com.rgarcia.w2m.utils.ConvertDto;
import org.springframework.stereotype.Component;

@Component
public class SpaceShipAppMapper {
    private final ConvertDto convert = ConvertDto.getInstance();
    public GetSpaceShipResponseDto toResponse(SpaceShip ship) {
        return new GetSpaceShipResponseDto(ship);
    }
    public GetSpaceShipsPaginationResponseDto toResponse(PaginationDto<SpaceShip> paginationDto) {
        return new GetSpaceShipsPaginationResponseDto(paginationDto);
    }

    public SpaceShip toDomain(CreateSpaceShipDto dto) {
        return (SpaceShip) convert.create(dto, SpaceShip.class);
    }
}
