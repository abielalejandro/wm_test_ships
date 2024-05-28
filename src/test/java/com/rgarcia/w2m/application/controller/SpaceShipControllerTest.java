package com.rgarcia.w2m.application.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rgarcia.w2m.application.dto.request.CreateSpaceShipDto;
import org.testcontainers.containers.RabbitMQContainer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpaceShipControllerTest {
    @Autowired
    MockMvc mockMvc;

    static RabbitMQContainer rabbit = new RabbitMQContainer();

    @BeforeEach
    void setUp() {}

    @BeforeAll
    static void up() {
        rabbit.start();
    }

    @AfterAll
    static void down() {
        rabbit.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.port", rabbit::getAmqpPort);
    }

    @Test
    void whenGetHeroById() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/spaceships/1")
                        .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isOk());
    }

    @Test
    void whenGetHeroByIdThrowNotFound()  throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/spaceships/99999")
                                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isNotFound());
    }

    @Test
    void whenCreateHero()  throws Exception {
        CreateSpaceShipDto post = new CreateSpaceShipDto(
                "Libertad","Armagedon"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/spaceships")
                                .header("content-type","application/json")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                        ).content(
                                objectMapper.writeValueAsString(post)
                        )
                )
                .andExpect(status().isOk());
    }

    @Test
    void whenCreateHeroForbiden()  throws Exception {
        CreateSpaceShipDto post = new CreateSpaceShipDto(
                "Libertad","Armagedon"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/spaceships")
                                .header("content-type","application/json")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("user")
                        ).content(
                                objectMapper.writeValueAsString(post)
                        )
                )
                .andExpect(status().isForbidden());
    }


    @Test
    void whenUpdateHero()  throws Exception {
        CreateSpaceShipDto post = new CreateSpaceShipDto(
                "Cygnus","El Abismo Negro"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/spaceships/1")
                                .header("content-type","application/json")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                        ).content(
                                objectMapper.writeValueAsString(post)
                        )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Cygnus"));
    }

    @Test
    void whenUpdateHeroDuplicatedError()  throws Exception {
        CreateSpaceShipDto post = new CreateSpaceShipDto(
                "Estrella Oscura","Dark Star"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/spaceships/1")
                                .header("content-type","application/json")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                                ).content(
                                        objectMapper.writeValueAsString(post)
                                )
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenUpdateHeroNotFound()  throws Exception {
        CreateSpaceShipDto post = new CreateSpaceShipDto(
                "Estrella Oscura","Dark Star"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(
                        MockMvcRequestBuilders.put("/api/v1/spaceships/9999")
                                .header("content-type","application/json")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                                ).content(
                                        objectMapper.writeValueAsString(post)
                                )
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void whenDeleteHero() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/spaceships/3")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                                )
                )
                .andExpect(status().isOk());

    }

    @Test
    void whenDeleteHeroNotFound() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.delete("/api/v1/spaceships/9999")
                                .with(
                                        SecurityMockMvcRequestPostProcessors
                                                .user("admin")
                                                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                                )
                )
                .andExpect(status().isNotFound());

    }

    @Test
    void whenSearch() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/spaceships")
                                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isOk());
    }

    @Test
    void whenSearchWithFilterByName() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/spaceships?q=Oscura")
                                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.total").value(1));
    }

    @Test
    void whenSearchWithFilterByNameNotFound() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/spaceships?q=Wing")
                                .with(SecurityMockMvcRequestPostProcessors.user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.total").value(0));
    }
}