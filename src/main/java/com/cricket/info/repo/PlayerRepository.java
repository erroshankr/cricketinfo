package com.cricket.info.repo;

import com.cricket.info.models.PlayerModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // connectivity with database
public interface PlayerRepository extends CrudRepository<PlayerModel, Long> { // playerModel -> table in database mapped to PlayerModel, Long -> type of primary Key
}
