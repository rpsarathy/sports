package com.sports.controller;

import com.sports.entity.Players;
import com.sports.entity.Sports;
import com.sports.services.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/players")
public class PlayersController {

    private final PlayersService service;

    public PlayersController(PlayersService service) {
        this.service = service;
    }

    /**
     * Players with No Sports
     *
     * @param
     * @return
     */
    @GetMapping(value = "/playerswithnosports", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Players>> getPlayersWithNoSport() {
        try {
            return ResponseEntity.ok(service.playersWithNoSports());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is to display playlist based on sports name filtering using pagination
     *
     * @param sportsName
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/playerwithsportsfilter", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Players>> paginatedPlayersWithSporstFilter(@RequestParam String sportsName,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            return ResponseEntity.ok(service.playersWithPagination(sportsName, paging));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
