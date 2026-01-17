package com.cricket.info.repo;

import com.cricket.info.models.PlayerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // connectivity with database
public interface PlayerRepository extends JpaRepository<PlayerModel, Long> { // playerModel -> table in database mapped to PlayerModel, Long -> type of primary Key
    PlayerModel findByPlayerName(String name);
    PlayerModel findByPlayerNameOrAge(String name, int age);
    PlayerModel findByPlayerNameAndAge(String name, int age);
    int countByCenturies(int num);
}


// Repository -> CrudRepository -> JpaRepository --> ModelRepository
// Repository -> CrudRepository --> ModelRepository
// Repository -> CrudRepository -->ListCrudRepository ->ModelRepository

// findAll --> SELECT * from players
//findById -> SELECT * from players where id = ?
//save -> INSERT INTO players (name, age) VALUES (?, ?)
//findByName -> SELECT * from players where name = ?
//findByAgeAndPlayerName SELECT * from players where age = ? AND name = ?

// CollegeTable --> CollegeID, collegeName, students
//   C1,FIEM,s1,s2,s3,s4 // One to Many
// students -> id, name, collegeId
// S1, Atul, C1
// S2, Abhi, C1
// S3, Ankit, C1
// S4, Ashish, C1
// playerID, teamID
// S1, C1
// S2, C1
// S3, C1
// S4, C1

