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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                .content(json).accept(MediaType.APPLICATION_JSON));


        // then
        actions
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name", Matchers.equalTo("owner_test")));
    }

    @Test
    public void 모든_회원_조회() throws Exception {
        // given
        OwnerEntity owner1 = OwnerEntity.builder()
                .name("owner1")
                .build();
        OwnerEntity owner2 = OwnerEntity.builder()
                .name("owner2")
                .build();
        OwnerEntity owner3 = OwnerEntity.builder()
                .name("owner3")
                .build();

        ownerRepository.save(owner1);
        ownerRepository.save(owner2);
        ownerRepository.save(owner3);

        // when
        ResultActions actions = mockMvc.perform(get("/api/owner")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(jsonPath("$.data.[*]",hasSize(3)));

        // then
        actions
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.[0].name",Matchers.equalTo(owner1.getName())))
                .andExpect(jsonPath("$.data.[1].name",Matchers.equalTo(owner2.getName())))
                .andExpect(jsonPath("$.data.[2].name",Matchers.equalTo(owner3.getName())));

    }


}
