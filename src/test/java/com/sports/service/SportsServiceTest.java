package com.sports.service;

import com.sports.ExceptionHandling.NoPlayersFoundException;

import com.sports.entity.Players;
import com.sports.entity.Sports;
import com.sports.repository.PlayerRepository;
import com.sports.repository.SportsRepository;
import com.sports.services.SportsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SportsServiceTest {

    @Mock
    private SportsRepository repository;
    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private SportsService sportsService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getSportsWithPlayers() {
        List<Sports> list = new ArrayList<>();
        Sports sports = new Sports();
        list.add(sports);
        Mockito.when(repository.findAll()).thenReturn(list);
        List<Sports> returnList = sportsService.getSportsWithPlayers();
        assertThat(returnList).isNotNull();
        assertEquals(1, returnList.size());
    }

    @Test
    public void test_findPlayersWithNoSportsWithException() {
        List<Sports> list = new ArrayList<>();
        Mockito.when(repository.findAll()).thenReturn(list);

        Exception exception = assertThrows(NoPlayersFoundException.class, () -> {
            sportsService.getSportsWithPlayers();
        });

        String expectedMessage = "There is no sports with players";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_deleteByName() {
        sportsService.deleteByName("1");
        assertThat(repository.count()).isEqualTo(0);
        verify(repository).deleteById(any());

    }


    @Test
    public void test_updatePlayerSportsException() {
        List<Sports> sportsList = new ArrayList<>();

        Exception exception = assertThrows(NoPlayersFoundException.class, () -> {
            sportsService.updatePlayerSports("1", sportsList);
        });

        String expectedMessage = "No Player Found with NameDanial";
        String actualMessage = exception.getMessage();
        assertFalse(actualMessage.contains(expectedMessage));
    }

    @Test
    public void test_updatePlayerSports() {
        List<Sports> sportsList = new ArrayList<>();
        List<Sports> updatedSportsList = new ArrayList<>();
        List<Players> updatedPlayerList = new ArrayList<>();
        Players player = new Players();
        Mockito.when(playerRepository.findById(any())).thenReturn(Optional.of(player));
       // Mockito.when(repository.saveAll(sportsList)).thenReturn(updatedSportsList);
        updatedPlayerList = sportsService.updatePlayerSports("1", sportsList);
        assertNotNull(updatedPlayerList);
    }

}
