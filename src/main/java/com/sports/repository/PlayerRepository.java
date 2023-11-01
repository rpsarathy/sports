package com.sports.repository;

import com.sports.entity.Players;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Players, String> {

    /**
     * This query to get the list of players based on the below parameters
     *
     * @param level
     * @param age
     * @param gender
     * @return
     */
    @Query(
            value = "SELECT players FROM PLAYERS players WHERE players.level = ?1 AND players.age=?2 and players.gender=?3",
            nativeQuery = true)
    List<Players> findPlayers(Integer level, Integer age, int gender);

    /**
     *   This Query is to find the Playesr with NO sports
     * @return
     */
    @Query(
            //value = "SELECT players FROM PLAYERS players,Sports sports WHERE count(sports.name) = 0 "
            value = "select id,email,age,gender,level from PLAYERS LEFT JOIN PLAYERS_SPORTS on ID =PLAYERS_ID where SPORTS_ID is null", nativeQuery = true)

            List<Players> findPlayersWithNoSports();



}
