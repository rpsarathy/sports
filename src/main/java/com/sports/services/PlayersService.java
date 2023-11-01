package com.sports.services;

import com.sports.ExceptionHandling.NoPlayersFoundException;
import com.sports.ExceptionHandling.NoSportsFoundException;
import com.sports.entity.Players;
import com.sports.entity.Sports;
import com.sports.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayersService {

    @Autowired
    private  PlayerRepository repository;

    /**
     * This method returs players with no sports
     *
     * @return
     */
    public List<Players> playersWithNoSports() {

        List<Players> list = repository.findPlayersWithNoSports();
        if (CollectionUtils.isEmpty(list)) {
            throw new NoSportsFoundException("There is no Players without sports");
        }
        return list;
    }

    /**
     * Find the players and filter with the sports name
     *
     * @param sportsName
     * @param pageable
     * @return
     */
    public List<Players> playersWithPagination(String sportsName, Pageable pageable) {
        Page<Players> pageablePlayers = repository.findAll(pageable);

        if (pageablePlayers.isEmpty()) {
            throw new NoPlayersFoundException("No Player Found");
        }
        List<Players> list=new ArrayList<>();
        for(Players player:pageablePlayers){
            for(Sports sports:player.getSports()){
                if(sports.getName().equalsIgnoreCase(sportsName)){
                    list.add(player);
                }
            }
        }

        if (CollectionUtils.isEmpty(list)) {
            throw new NoPlayersFoundException("No Player Found");
        }
        return list;
    }

}
