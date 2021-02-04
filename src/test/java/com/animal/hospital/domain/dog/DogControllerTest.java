package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;
import com.animal.hospital.domain.owner.OwnerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
public class DogControllerTest {

    @Autowired
    private OwnerRepository ownerRepository;

    private MockMvc mockMvc;

    // JSON 검증
    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void before(@Autowired DogController dogController) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(dogController).build();
    }

    @Test
    public void Dog_등록() throws Exception {

        // given
        // 주인 등록
        OwnerEntity owner = OwnerEntity.builder()
                .name("owner_one")
                .build();
        ownerRepository.save(owner);

        // 강아지 등록
        DogDTO dogDTO = DogDTO.builder()
                .ownerEntity(owner)
                .dogName("first_dog")
                .build();

        String json = mapper.writeValueAsString(dogDTO);
        log.info("json : {}", json);

        // when
        ResultActions actions = mockMvc.perform(post("/api/dog")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.dogName", Matchers.equalTo("first_dog")))
                .andExpect(jsonPath("$.data.ownerEntity.name", Matchers.equalTo("owner_one")));

        // then
        actions
                .andDo(print())
                .andExpect(status().isCreated());

    }

}