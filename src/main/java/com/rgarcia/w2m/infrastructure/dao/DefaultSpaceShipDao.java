package com.rgarcia.w2m.infrastructure.dao;

import com.rgarcia.w2m.common.dto.SearchSpaceShipParameters;
import com.rgarcia.w2m.domain.dao.SpaceShipDao;
import com.rgarcia.w2m.domain.model.SpaceShip;
import com.rgarcia.w2m.infrastructure.dao.repository.SpaceShipRepository;
import com.rgarcia.w2m.infrastructure.mapper.SpaceShipMapper;
import com.rgarcia.w2m.infrastructure.model.SpaceShipEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Repository
public class DefaultSpaceShipDao implements SpaceShipDao {

    SpaceShipMapper mapper;
    SpaceShipRepository repository;

    @Override
    public Optional<SpaceShip> get(Long id) {
        return
                repository
                        .findById(id)
                        .map(entity-> {
                            return mapper.toDomain(entity);
                        });
    }

    @Override
    public SpaceShip createOrUpdate(SpaceShip ship) {
        SpaceShipEntity entity = mapper.toEntity(ship);
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<SpaceShip> search(SearchSpaceShipParameters parameters) {
        if (!Optional.of(parameters.getQuery()).isPresent()) {
            parameters.setQuery("");
        }

        parameters.setQuery("%"+parameters.getQuery()+"%");

        return repository
                .findByNameLikeIgnoreCase(
                        parameters.getQuery(),
                        PageRequest.of(parameters.getPage()-1, parameters.getLimit()))
                .stream()
                .map((entity)->{
                    return mapper.toDomain(entity);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Long count(String query) {
        if (!Optional.of(query).isPresent()) {
            query = "";
        }
        query = "%"+query+"%";
        return repository.countByNameLikeIgnoreCase(query);
    }

}
