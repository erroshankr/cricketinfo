package com.cricket.info.services.impl;

import com.cricket.info.models.PlayerModel;
import com.cricket.info.services.PlayerService;

import java.util.List;

public class PlayerServiceImpl implements PlayerService {
    @Override
    public PlayerModel getPlayerById(Long id) {
        return null;
    }

    @Override
    public List<PlayerModel> findAllPlayers() {
        return List.of();
    }

    @Override
    public void addPlayer(PlayerModel player) {

    }

    @Override
    public void updatePlayer(PlayerModel player) {

    }

    @Override
    public void deletePlayer(Long id) {

    }
}
