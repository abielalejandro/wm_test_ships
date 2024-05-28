package com.rgarcia.w2m.domain.service;

import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.common.dto.SearchSpaceShipParameters;
import com.rgarcia.w2m.domain.dao.SpaceShipDao;
import com.rgarcia.w2m.domain.model.SpaceShip;
import com.rgarcia.w2m.exceptions.SpaceShipNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class DefaultSpaceShipService implements SpaceShipService{

    SpaceShipDao dao;

    @Override
    public SpaceShip get(Long id) {
        return dao
                .get(id)
                .orElseThrow(SpaceShipNotFoundException::new);
    }


    @Override
    public SpaceShip create(SpaceShip hero) {
        return dao.createOrUpdate(hero);
    }


    @Override
    public SpaceShip update(Long id, SpaceShip ship) {
        SpaceShip persisted = get(id);
        persisted.setName(ship.getName());
        persisted.setRelatedTo(ship.getRelatedTo());
        return dao.createOrUpdate(persisted);
    }


    @Override
    public void delete(Long id) {
        get(id);
        dao.delete(id);
    }


    @Override
    public PaginationDto<SpaceShip> search(SearchSpaceShipParameters parameters) {
        List<SpaceShip> superHeroes= dao.search(parameters);
        Long total= dao.count(parameters.getQuery());
        PaginationDto<SpaceShip> paginationDto= new PaginationDto<SpaceShip>(
                superHeroes,
                total,
                parameters.getPage(),
                parameters.getLimit()
        );

        return paginationDto;
    }
}
