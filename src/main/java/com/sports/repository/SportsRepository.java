package com.sports.repository;

import com.sports.entity.Players;
import com.sports.entity.Sports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SportsRepository extends JpaRepository<Sports, String> {
    /**
     *  Find the sports with number of players greater than two.
     * @param level
     * @param age
     * @param gender
     * @return
     */
    @Query(
            //value = "SELECT sports FROM PLAYERS players,Sports sports WHERE count(players.level) >=2",
            value = "select SPORTS_ID, name from PLAYERS_SPORTS ps inner join SPORTS s where s.ID =ps.SPORTS_ID \n" +
                    "GROUP BY ps.SPORTS_ID  having count(ps.SPORTS_ID)>=2",
            nativeQuery = true)
    List<Sports> findSportsWithGreaterThenTwoPlayers();

    /**
     *  Sports with Number of Players as zero
     * @return
     */
    @Query(
            //value = "SELECT sports FROM PLAYERS players,Sports sports WHERE count(players.level) =0",
            value="select id,name  from PLAYERS LEFT JOIN SPORTS on ID =PLAYERS_ID where PLAYERS_ID  is null",
            nativeQuery = true)
    List<Sports> findSportsWithNoPlayers();
}
