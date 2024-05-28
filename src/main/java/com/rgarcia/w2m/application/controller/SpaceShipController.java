package com.rgarcia.w2m.application.controller;


import com.rgarcia.w2m.application.dto.request.CreateSpaceShipDto;
import com.rgarcia.w2m.application.dto.response.GetSpaceShipsPaginationResponseDto;
import com.rgarcia.w2m.common.NegativeIdParam;
import com.rgarcia.w2m.common.dto.GeneralResponseDto;
import com.rgarcia.w2m.application.dto.response.GetSpaceShipResponseDto;
import com.rgarcia.w2m.application.mapper.SpaceShipAppMapper;
import com.rgarcia.w2m.common.dto.SearchSpaceShipParameters;
import com.rgarcia.w2m.domain.service.SpaceShipService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/spaceships")
public class SpaceShipController {

    SpaceShipService service;
    SpaceShipAppMapper mapper;

    @Cacheable("spaceships")
    @GetMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public GetSpaceShipsPaginationResponseDto search(
            @RequestParam(defaultValue = "1", name="page") @Positive Integer page,
            @RequestParam(defaultValue = "10", name="size") @Positive Integer size,
            @RequestParam(name="q") Optional<String> q
    ) {
        SearchSpaceShipParameters parameters = new SearchSpaceShipParameters();
        parameters.setQuery(q.orElse(""));
        parameters.setPage(page);
        parameters.setLimit(size);
        return mapper.toResponse(service.search(parameters));
    }

    @NegativeIdParam
    @GetMapping(path = "/{id}")
    @PreAuthorize("hasRole('ROLE_USER')") 
    public GetSpaceShipResponseDto searchById(
            @PathVariable @Positive Long id) {
        return mapper.toResponse(service.get(id));
    }

    @CacheEvict(value="spaceships", allEntries=true)
    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    public GetSpaceShipResponseDto create (@RequestBody @Valid @NotNull CreateSpaceShipDto ship) {
        return mapper.toResponse(service.create(mapper.toDomain(ship)));
    }

    @NegativeIdParam
    @CacheEvict(value="spaceships", allEntries=true)
    @RequestMapping(path = "/{id}",method = RequestMethod.PUT)
    @PreAuthorize("hasRole('ROLE_ADMIN')") 
    public GetSpaceShipResponseDto update (
            @PathVariable @NotNull @Positive Long id,
            @RequestBody @Valid @NotNull CreateSpaceShipDto ship) {
        return mapper.toResponse(service.update(id,mapper.toDomain(ship)));
    }

    @NegativeIdParam
    @CacheEvict(value="spaceships", allEntries=true)
    @RequestMapping(path = "/{id}",method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('ADMIN')") 
    public GeneralResponseDto<String> delete (
            @PathVariable @NotNull @Positive Long id) {
        service.delete(id);
        return new GeneralResponseDto<String>("OK");
    }

}
