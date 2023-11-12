package ru.job4j.accidents.controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.AccidentServiceSD;

import java.util.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvcAccident;

    @MockBean
    private AccidentServiceSD accidents;

    @Test
    @WithMockUser
    public void shouldReturnViewCreateAccident() throws Exception {
        this.mockMvcAccident.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/createAccident"));
    }

    @Test
    @WithMockUser
    void shouldReturnFormUpdate() throws Exception {
        var accident = new Accident(1, "Name", "Text",
                "Address", new AccidentType(), Set.of(new Rule()));
        when(accidents.findById(accident.getId())).thenReturn(Optional.of(accident));
        this.mockMvcAccident.perform(get("/updateAccident/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/editAccident"));
    }
}