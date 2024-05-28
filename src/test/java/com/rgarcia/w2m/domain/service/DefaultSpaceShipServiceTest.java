package com.rgarcia.w2m.domain.service;

import com.rgarcia.w2m.common.dto.PaginationDto;
import com.rgarcia.w2m.common.dto.SearchSpaceShipParameters;
import com.rgarcia.w2m.domain.dao.SpaceShipDao;
import com.rgarcia.w2m.domain.model.SpaceShip;
import com.rgarcia.w2m.exceptions.SpaceShipNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DefaultSpaceShipServiceTest {

    @Mock
    SpaceShipDao dao;

    @InjectMocks
    private DefaultSpaceShipService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenGetHeroBygetId() {
        Long id =1l;
        Optional<SpaceShip> hero = Optional.of(new SpaceShip(
            1l,"Cygnus","El Abismo Negro"
        ));

        when(dao.get(id)).thenReturn(hero);
        SpaceShip result= service.get(id);
        assertEquals(result.getId(),id);
        assertEquals(result.getName(),"Cygnus");

    }

    @Test
    void whenGetHeroByIdThrowNotFound() {
        Long id =1l;
        Optional<SpaceShip> hero = Optional.ofNullable(null);

        when(dao.get(id)).thenReturn(hero);
        SpaceShipNotFoundException thrown = assertThrows(
                SpaceShipNotFoundException.class,
                () -> service.get(id),
                "Superhero not found"
        );
        assertTrue(thrown.getMessage().contains("SpaceShip not found"));
    }

    @Test
    void whenCreateHero() {
        SpaceShip hero = new SpaceShip(
                1l,"Cygnus","El Abismo Negro"
        );

        when(dao.createOrUpdate(hero)).thenReturn(hero);
        SpaceShip result= service.create(hero);
        assertEquals(result.getId(),hero.getId());
        assertEquals(result.getName(),hero.getName());

    }

    @Test
    void whenUpdateHero() {
        Long id = 1l;
        SpaceShip hero = new SpaceShip(
                1l,"Cygnus","El Abismo Negro"
        );

        Optional<SpaceShip> heroPersisted = Optional.of(new SpaceShip(
                1l,"Cygnus X","El Abismo Negro"
        ));

        when(dao.get(id)).thenReturn(heroPersisted);
        when(dao.createOrUpdate(hero)).thenReturn(hero);
        SpaceShip result= service.update(id,hero);
        assertEquals(result.getId(),hero.getId());
        assertEquals(result.getName(),hero.getName());

    }

    @Test
    void whenDeleteHero() {
        Long id = 1l;
        Optional<SpaceShip> heroPersisted = Optional.of(new SpaceShip(
                1l,"Cygnus X","El Abismo Negro"
        ));

        when(dao.get(id)).thenReturn(heroPersisted);
        service.delete(id);
        assertTrue(true);

    }

    @Test
    void whenSearch() {
        List<SpaceShip> heroes = List.of(
            new SpaceShip(
                1l,"Cygnus X","El Abismo Negro"
            ),
            new SpaceShip(
                    1l,"Estrella Oscura","Dark Star"
            )
        );

        SearchSpaceShipParameters parameters = new SearchSpaceShipParameters(1,2,"");
        when(dao.search(parameters)).thenReturn(heroes);
        when(dao.count(parameters.getQuery())).thenReturn(10l);

        PaginationDto<SpaceShip> result = service.search(parameters);

        assertEquals(result.getData().size(),2);
        assertEquals(result.getTotal(),10);
        assertEquals(result.getPages(),5);
    }

    @Test
    void whenSearchWithoutQuery() {
        List<SpaceShip> heroes = List.of(
                new SpaceShip(
                        1l,"Cygnus X","El Abismo Negro"
                ),
                new SpaceShip(
                        1l,"Estrella Oscura","Dark Star"
                )
        );

        SearchSpaceShipParameters parameters = new SearchSpaceShipParameters(1,2,null);
        when(dao.search(parameters)).thenReturn(heroes);
        when(dao.count(parameters.getQuery())).thenReturn(10l);

        PaginationDto<SpaceShip> result = service.search(parameters);

        assertEquals(result.getData().size(),2);
        assertEquals(result.getTotal(),10);
        assertEquals(result.getPages(),5);
    }

    @Test
    void whenSearchWithFilterBygetName() {
        List<SpaceShip> heroes = List.of(
                new SpaceShip(
                        1l,"Estrella Oscura","Dark Star"
                )
        );

        SearchSpaceShipParameters parameters = new SearchSpaceShipParameters(1,2,"Oscura");
        when(dao.search(parameters)).thenReturn(heroes);
        when(dao.count(parameters.getQuery())).thenReturn(10l);

        PaginationDto<SpaceShip> result = service.search(parameters);

        assertEquals(result.getData().size(),1);
        assertEquals(result.getTotal(),10);
        assertEquals(result.getPages(),5);
    }

}