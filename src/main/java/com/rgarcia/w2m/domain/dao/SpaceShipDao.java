package com.rgarcia.w2m.domain.dao;

import com.rgarcia.w2m.common.dto.SearchSpaceShipParameters;
import com.rgarcia.w2m.domain.model.SpaceShip;

import java.util.List;
import java.util.Optional;

public interface SpaceShipDao {

    Optional<SpaceShip> get(Long id);
    SpaceShip createOrUpdate(SpaceShip ship);
    void delete(Long id);
    List<SpaceShip> search(SearchSpaceShipParameters parameters);
    Long count(String query);
}
