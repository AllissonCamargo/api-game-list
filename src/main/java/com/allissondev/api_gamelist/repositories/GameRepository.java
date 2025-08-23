package com.allissondev.api_gamelist.repositories;

import com.allissondev.api_gamelist.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
