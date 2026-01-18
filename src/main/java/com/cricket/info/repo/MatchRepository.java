package com.cricket.info.repo;

import com.cricket.info.models.MatchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<MatchModel, Long> {
}
