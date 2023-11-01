package com.sports.controller;

import com.sports.entity.Players;
import com.sports.services.PlayersService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(PlayersController.class)
public class PlayersControllerTest {
    @MockBean
    PlayersService playersService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfindAll() throws Exception {

        List<Players> list = new ArrayList<>();
        Players players = new Players();
        players.setEmail("daniel@gmail.com");
        list.add(players);
        Mockito.when(playersService.playersWithNoSports()).thenReturn(list);

        mockMvc.perform(get("/players/playerswithnosports"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].email", Matchers.is("daniel@gmail.com")));
    }

}
