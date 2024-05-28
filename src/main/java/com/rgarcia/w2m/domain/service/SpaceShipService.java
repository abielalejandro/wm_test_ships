package com.rgarcia.w2m.domain.service;

import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.common.dto.SearchSpaceShipParameters;
import com.rgarcia.w2m.domain.model.SpaceShip;

import java.util.List;

public interface SpaceShipService {

    SpaceShip get(Long id);
    SpaceShip create(SpaceShip ship);
    SpaceShip update(Long id, SpaceShip ship);
    void delete(Long id);
    PaginationDto<SpaceShip> search(SearchSpaceShipParameters parameters);

}
