package com.mins.corona.api.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    @Rollback
    @DisplayName("이벤트 생성 성공 테스트")
    void test1() throws Exception {

        // GIVE
        EventDTO event = EventDTO.builder()
                .name("Corona Project")
                .description("REST Test Project")
                .beginEnrollmentDateTime(LocalDateTime.now())
                .closeEnrollmentDateTime(LocalDateTime.now().plusDays(7))
                .beginEventDateTime(LocalDateTime.now())
                .endEventDateTime(LocalDateTime.now().plusHours(5))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역")
                .build();

        // WHEN
        mockMvc.perform(
                post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(event)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("id").exists())
                .andExpect(header().exists(HttpHeaders.LOCATION))
//                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonPath("_links.self").exists())
                .andExpect(jsonPath("_links.query-events").exists())
                .andExpect(jsonPath("_links.update-event").exists())
//                .andExpect(jsonPath("profile").exists())
        ;
    }

    @Test
    @Order(2)
    @Rollback
    @DisplayName("이벤트 생성 실패 테스트")
    void test2() throws Exception {

        // GIVE
        Event event = Event.builder()
                .id(10000L)
                .name("Corona Project")
                .description("REST Test Project")
                .beginEnrollmentDateTime(LocalDateTime.now())
                .closeEnrollmentDateTime(LocalDateTime.now().plusDays(7))
                .beginEventDateTime(LocalDateTime.now())
                .endEventDateTime(LocalDateTime.now().plusHours(5))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역")
                .build();

        // WHEN
        mockMvc.perform(
                post("/api/events")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaTypes.HAL_JSON_VALUE)
                        .content(objectMapper.writeValueAsBytes(event)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    @Order(3)
    @Rollback
    @DisplayName("1. 이벤트DTO 유효성 검사 테스트")
    void test3() throws Exception {
        EventDTO eventDTO = EventDTO.builder().build();

        mockMvc.perform(post("/api/events")
                .content(objectMapper.writeValueAsBytes(eventDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Order(4)
    @Rollback
    @DisplayName("2. 이벤트DTO 유효성 검사 테스트")
    void test4() throws Exception {
        // GIVE
        EventDTO eventDTO = EventDTO.builder()
                .name("Corona Project")
                .description("REST Test Project")
                .beginEnrollmentDateTime(LocalDateTime.now().plusDays(8))
                .closeEnrollmentDateTime(LocalDateTime.now().plusDays(7))
                .beginEventDateTime(LocalDateTime.now().plusHours(6))
                .endEventDateTime(LocalDateTime.now().plusHours(5))
                .basePrice(1234)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역")
                .build();

        mockMvc.perform(post("/api/events")
                .content(objectMapper.writeValueAsBytes(eventDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    //TODO @Parameters 를 활용해 중복된 테스트 코드를 줄이고 가독성을 높이는 코드 만들기.
}