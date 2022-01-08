package com.way.pjatk;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SandwichRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSuccessHelloWorld() throws Exception {
        mockMvc.perform(get("/sandwich"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Hello world"));
    }

    @Test
    void shouldReturnSuccessfulPreparedSandwich() throws Exception {
        mockMvc.perform(get("/sandwich/prepare").param("name", "nameValue"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("\"name\":\"nameValue\"")));

    }
}
