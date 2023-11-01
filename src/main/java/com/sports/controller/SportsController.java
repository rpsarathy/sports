package com.sports.controller;

import com.sports.entity.Sports;
import com.sports.services.SportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/sports")
public class SportsController {

    private final SportsService service;

    public SportsController(SportsService service) {
        this.service = service;
    }

    /*
      This method is to return sports with Players
     */
    @GetMapping(value = "/sportswithplayers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sports>> sportsWithPlayers() {
        try{
        return ResponseEntity.ok(service.getSportsWithPlayers());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     *  This method is to delete sports with name
     * @param name
     * @return
     */

    @DeleteMapping("{name}")
    public ResponseEntity<String> deleteSportsWithName(@RequestParam String name) {
        try {
            service.deleteByName(name);
            return new ResponseEntity<String>("Sport deleted successfully!.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is to upodate sporst based on the player name
     * @param playerName
     * @param sportsList
     * @return
     */
    @PutMapping(value = "/update/{playerName}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sports>> updatePlayerSports(@RequestParam String playerName, @RequestBody List<Sports> sportsList) {
      try{
        return ResponseEntity.ok(service.updatePlayerSports(playerName,sportsList));
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
}
