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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SportsService {

    @Autowired
    private  SportsRepository repository;
    @Autowired
    private  PlayerRepository playerRepository;

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
    public String deleteByName(String sportsName) {
        try {
            repository.deleteById(sportsName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "SUCCESS";
    }

    /**
     * This method is to update the all sports of a player
     * @param playerName
     * @param sports
     * @return
     */
    public List<Players> updatePlayerSports(String playerName, List<Sports> sports) {

        List<Players> updatedPlayerList = null;
        Optional<Players> optionalUser = playerRepository.findById(Long.valueOf(playerName));

        if (optionalUser.isEmpty()) {
            throw new NoPlayersFoundException(SportsConstant.NO_PLAYERS_FOUND + playerName);
        }

        if (optionalUser.isPresent()) {
            Set<Sports> sportsSet= sports.stream().collect(Collectors.toSet());
            Players players=optionalUser.get();
         //   players.setSports(sportsSet);
            List<Players> list=new ArrayList<>();
            list.add(players);
            updatedPlayerList= playerRepository.saveAll(list);
        }

        return updatedPlayerList;
    }
}
