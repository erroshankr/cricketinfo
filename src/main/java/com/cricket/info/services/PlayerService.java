package com.cricket.info.services;

import com.cricket.info.exceptions.PlayerNotFoundException;
import com.cricket.info.models.PlayerModel;

import java.util.List;

public interface PlayerService {
    PlayerModel getPlayerById(Long id) throws PlayerNotFoundException;
    List<PlayerModel> findAllPlayers();
    void savePlayer(PlayerModel player) throws Exception;
    void deletePlayer(Long id) throws PlayerNotFoundException;
}
