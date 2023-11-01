package com.sports.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.sports.ExceptionHandling.NoSportsFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sports.entity.Players;
import com.sports.repository.PlayerRepository;
import com.sports.services.PlayersService;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayersService playersService;
    @Mock
    private PlayerRepository playerRepository;


    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_findPlayersWithNoSports() {
        List<Players> list = new ArrayList<>();
        Players players = new Players();
        list.add(players);
        Mockito.when(playerRepository.findPlayersWithNoSports()).thenReturn(list);
        List<Players> returnList = playersService.playersWithNoSports();
        assertThat(returnList).isNotNull();
        assertEquals(1, returnList.size());
    }

    @Test
    public void test_findPlayersWithNoSportsWithException() {
        List<Players> list = new ArrayList<>();
        Mockito.when(playerRepository.findPlayersWithNoSports()).thenReturn(list);

        Exception exception = assertThrows(NoSportsFoundException.class, () -> {
            playersService.playersWithNoSports();
        });

        String expectedMessage = "There is no Players without sports";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
