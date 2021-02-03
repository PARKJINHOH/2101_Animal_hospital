package com.animal.hospital.domain.owner;

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
public class OwnerControllerTest {

    @Autowired
    private OwnerRepository ownerRepository;

    private MockMvc mockMvc;
    private static ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void before(@Autowired OwnerController ownerController) throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    public void Owner_등록() throws Exception {
        // given
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setName("owner_test");

        String json = mapper.writeValueAsString(ownerDTO);

        // when
        ResultActions actions = mockMvc.perform(post("/api/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.name", Matchers.equalTo("owner_test")));

        // then
        actions
                .andDo(print())
                .andExpect(status().isCreated());
    }


}
