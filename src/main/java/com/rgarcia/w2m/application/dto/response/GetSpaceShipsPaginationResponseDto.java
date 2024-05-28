package com.rgarcia.w2m.application.dto.response;

import com.rgarcia.w2m.common.dto.GeneralResponseDto;
import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.domain.model.SpaceShip;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetSpaceShipsPaginationResponseDto extends GeneralResponseDto<PaginationDto<SpaceShip>> {
    public GetSpaceShipsPaginationResponseDto(PaginationDto<SpaceShip> ships) {
        super(ships);
    }
}
