package com.cricket.info.services;

import com.cricket.info.models.PlayerModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {
    PlayerModel getPlayerById(Long id);
    List<PlayerModel> findAllPlayers();
    void addPlayer(PlayerModel player);
    void updatePlayer(PlayerModel player);
    void deletePlayer(Long id);
}
