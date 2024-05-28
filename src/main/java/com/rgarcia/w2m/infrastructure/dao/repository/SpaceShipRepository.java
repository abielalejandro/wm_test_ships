package com.rgarcia.w2m.infrastructure.dao.repository;

import com.rgarcia.w2m.infrastructure.model.SpaceShipEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpaceShipRepository extends JpaRepository<SpaceShipEntity, Long> {
    List<SpaceShipEntity> findByNameLikeIgnoreCase(String name, Pageable pageable);
    Long countByNameLikeIgnoreCase(String name);
}
