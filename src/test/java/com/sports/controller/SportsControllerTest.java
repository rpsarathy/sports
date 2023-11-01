package com.sports.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sports.entity.Players;
import com.sports.entity.Sports;
import com.sports.services.PlayersService;
import com.sports.services.SportsService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SportsController.class)
public class SportsControllerTest {
    @MockBean
    private SportsService service;

    @MockBean
    private PlayersService playersService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSportsWithPlayers() throws Exception {

        List<Sports> list = new ArrayList<>();
        Sports sports = new Sports();
        sports.setName("Tennis");
        list.add(sports);
        Mockito.when(service.getSportsWithPlayers()).thenReturn(list);

        mockMvc.perform(get("/sports/sportswithplayers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Tennis")));
    }

    @Test
    public void testDeleteSportsWithName() throws Exception {
        Mockito.when(service.deleteByName(any())).thenReturn("SUCCESS");

        mockMvc.perform(delete("/sports/deletesport/{name}", "tennis"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatePlayerSports() throws Exception {
        List<Sports> sportsList = new ArrayList<>();
        List<Players> updatedPlayerList = new ArrayList<>();
        Players player = new Players();
        Mockito.when(service.updatePlayerSports("tennis", sportsList)).thenReturn(updatedPlayerList);

        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("playerName", "Daniel");

        ResultActions response = mockMvc.perform(put("/sports/update")
                .params(requestParams)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPlayerList)));
        response.andExpect(status().isOk());
    }
}
