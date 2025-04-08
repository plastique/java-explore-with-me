package ru.practicum.explore_with_me.stat.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.practicum.explore_with_me.stat.dto.HitAddRequestDto;
import ru.practicum.explore_with_me.stat.dto.HitStatDto;
import ru.practicum.explore_with_me.stat.server.dto.StatRequestDto;
import ru.practicum.explore_with_me.stat.server.service.contracts.StatsServiceInterface;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StatsController.class)
class StatsControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private StatsServiceInterface statsService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void addHit() throws Exception {

        HitAddRequestDto hitDto = new HitAddRequestDto(
                "app name 1",
                "/some-path",
                "127.1.0.1",
                LocalDateTime.now()
        );

        mockMvc.perform(
                       post("/hit")
                               .content(mapper.writeValueAsString(hitDto))
                               .characterEncoding(StandardCharsets.UTF_8)
                               .contentType(MediaType.APPLICATION_JSON)
                               .accept(MediaType.APPLICATION_JSON)
               )
               .andExpect(status().isCreated())
               .andExpect(content().string(""));
    }

    @Test
    void getStats() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<HitStatDto> stats = List.of(
                new HitStatDto("app1", "/", 1),
                new HitStatDto("app2", "/path2", 2)
        );

        when(statsService.getStats(Mockito.any(StatRequestDto.class)))
                .thenReturn(stats);

        mockMvc.perform(
                       get("/stats")
                               .param("start", LocalDateTime.now().format(formatter))
                               .param("end", LocalDateTime.now().plusDays(1).format(formatter))
                               .characterEncoding(StandardCharsets.UTF_8)
                               .contentType(MediaType.APPLICATION_JSON)
                               .accept(MediaType.APPLICATION_JSON)
               )
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].app").value(stats.get(0).getApp()));
    }

}