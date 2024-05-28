package com.rgarcia.w2m.infrastructure.mapper;

import com.rgarcia.w2m.domain.model.SpaceShip;
import com.rgarcia.w2m.infrastructure.model.SpaceShipEntity;
import com.rgarcia.w2m.utils.ConvertDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class SpaceShipMapper {
    private final ConvertDto convert = ConvertDto.getInstance();
    public SpaceShip toDomain(SpaceShipEntity entity) {
        return (SpaceShip) convert.create(entity, SpaceShip.class);
    }

    public SpaceShipEntity toEntity(SpaceShip domain) {
        return (SpaceShipEntity) convert.create(domain, SpaceShipEntity.class);
    }
}
