package com.allissondev.api_gamelist.repositories;

import com.allissondev.api_gamelist.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long> {
}
