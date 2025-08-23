package com.allissondev.api_gamelist.services;

import com.allissondev.api_gamelist.dto.GameMinDTO;
import com.allissondev.api_gamelist.entities.Game;
import com.allissondev.api_gamelist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameMinDTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(x -> new GameMinDTO(x)).toList();
    }
}
