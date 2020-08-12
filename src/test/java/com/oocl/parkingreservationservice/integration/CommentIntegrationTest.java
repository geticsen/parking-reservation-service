package com.oocl.parkingreservationservice.integration;

import com.oocl.parkingreservationservice.controller.CommentController;
import com.oocl.parkingreservationservice.handler.GlobalExceptionHandler;
import com.oocl.parkingreservationservice.repository.CommentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentIntegrationTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentController commentController;
    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;
    MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController, globalExceptionHandler).build();
    }

    @AfterEach
    void tearDown() {
        commentRepository.deleteAll();
    }

    @Test
    void should_return_comment_response_when_hit_add_comment_endpoint_given_info() throws Exception{
        //given
        String commentInfo = "{\n" +
                "    \"id\": 1,\n" +
                "    \"orderId\":1,\n" +
                "    \"userId\": 23,\n" +
                "    \"parkingLotId\": 215,\n" +
                "    \"score\":3.5,\n" +
                "    \"content\":\"好看\"\n" +
                "}";
        //when
        mockMvc.perform(post(("/comments")).contentType(MediaType.APPLICATION_JSON).content(commentInfo))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.orderId").isNumber())
                .andExpect(jsonPath("$.userId").isNumber())
                .andExpect(jsonPath("$.parkingLotId").isNumber())
                .andExpect(jsonPath("$.score").value(3.5))
                .andExpect(jsonPath("$.content").value("好看"));
    }
}
