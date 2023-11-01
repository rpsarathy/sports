package com.sports.services;

import com.sports.ExceptionHandling.NoPlayersFoundException;
import com.sports.entity.Players;
import com.sports.entity.Sports;
import com.sports.repository.PlayerRepository;
import com.sports.repository.SportsRepository;
import com.sports.utils.SportsConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class SportsService {

    private final SportsRepository repository;
    private final PlayerRepository playerRepository;

    @Autowired
    public SportsService(SportsRepository repository, PlayerRepository playerRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
    }


    /*
       Get Sports with all players
     */
    public List<Sports> getSportsWithPlayers() {
        List<Sports> list = repository.findAll();
        if (CollectionUtils.isEmpty(list)) {
            throw new NoPlayersFoundException("There is no sports with players");
        }
        return list;
    }

    /**
     * This task it to delete the sports based on name
     *
     * @param sportsName
     */
    public void deleteByName(String sportsName) {
        repository.deleteById(sportsName);
    }

    /**
     * This method is to update the all sports of a player
     * @param playerName
     * @param sports
     * @return
     */
    public List<Sports> updatePlayerSports(String playerName, List<Sports> sports) {

        List<Sports> updateSportsList = null;
        Optional<Players> optionalUser = playerRepository.findById(playerName);

        if (optionalUser.isEmpty()) {
            throw new NoPlayersFoundException(SportsConstant.NO_PLAYERS_FOUND + playerName);
        }

        if (optionalUser.isPresent()) {
            updateSportsList = repository.saveAll(sports);
        }

        return updateSportsList;
    }
}
