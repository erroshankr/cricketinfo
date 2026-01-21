package com.cricket.info.services.impl;

import com.cricket.info.exceptions.PlayerNotFoundException;
import com.cricket.info.models.PlayerModel;
import com.cricket.info.repo.PlayerRepository;
import com.cricket.info.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public PlayerModel getPlayerById(Long id) throws PlayerNotFoundException{
       Optional<PlayerModel> opt =  playerRepository.findById(id);
       if(opt.isEmpty()){
           throw new PlayerNotFoundException("Player with id " +id+ " not found");
       }
       return opt.get();
    }

    @Override
    public List<PlayerModel> findAllPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public void savePlayer(PlayerModel player) throws Exception {
       if(player.getId() == null){
           player.setAverage((double) (player.getTotalRuns()/player.getTotalMatches()));
           playerRepository.save(player);
       }else{
           Optional<PlayerModel> opt = playerRepository.findById(player.getId());
           if(opt.isEmpty()){
               throw new PlayerNotFoundException("Player with id " + player.getId() + " not found");
           }else{
               PlayerModel p = opt.get();
               p.setTeam(player.getTeam());
               p.setGender(player.getGender());
               p.setTotalMatches(player.getTotalMatches());
               p.setCenturies(player.getCenturies());
               p.setAverage((double) (player.getTotalRuns()/player.getTotalMatches()));
               p.setHalfCenturies(player.getHalfCenturies());
               p.setTotalRuns(player.getTotalRuns());
               p.setAge(player.getAge());
               p.setCaptain(player.isCaptain());
               p.setPlayerName(player.getPlayerName());
               playerRepository.save(p);
           }
       }
    }


    @Override
    public void deletePlayer(Long id) throws PlayerNotFoundException {
        Optional<PlayerModel> opt = playerRepository.findById(id);
        if(opt.isEmpty()){
            throw new PlayerNotFoundException("Player not found for deletion with ID: " + id);
        }
        playerRepository.deleteById(id);
    }
}
