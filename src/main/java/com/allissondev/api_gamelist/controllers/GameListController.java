package com.allissondev.api_gamelist.controllers;

import com.allissondev.api_gamelist.dto.GameListDTO;
import com.allissondev.api_gamelist.dto.GameMinDTO;
import com.allissondev.api_gamelist.dto.ReplacementDTO;
import com.allissondev.api_gamelist.services.GameListService;
import com.allissondev.api_gamelist.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {

    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameListDTO> findAll() {
        return gameListService.findAll();
    }


    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByList (@PathVariable Long listId) {
        List<GameMinDTO> result = gameService.findByList(listId);
        return result;
    }

    @PostMapping(value = "/{listId}/replacement")
    public void move(@PathVariable Long listId, @RequestBody ReplacementDTO body) {
        gameListService.move(listId, body.getSourceIndex(), body.getDestinationIndex());
    }


}
