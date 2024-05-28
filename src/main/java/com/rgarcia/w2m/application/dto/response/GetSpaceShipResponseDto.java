package com.rgarcia.w2m.application.dto.response;

import com.rgarcia.w2m.common.dto.GeneralResponseDto;
import com.rgarcia.w2m.domain.model.SpaceShip;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GetSpaceShipResponseDto extends GeneralResponseDto<SpaceShip> {
    public GetSpaceShipResponseDto(SpaceShip ship) {
        super(ship);
    }
}
