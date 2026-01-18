package com.cricket.info.models;

import com.cricket.info.enums.Gender;
import jakarta.persistence.*;

import java.lang.reflect.Type;

@Entity  // This class is mapped to a table in database -> ORM -> Object Relational Mapping
@Table(name = "players")
public class PlayerModel extends BaseModel {

    // SELECT * from players INNER JOIN team on player.team = team.id where team.id = 1;

    @Column(name = "name")
    private String playerName;


    @ManyToOne
    private TeamModel team; // foreign key

    private Integer centuries;
    private Integer halfCenturies;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private Integer totalRuns;
    private Double average;
    private int totalMatches;
    private boolean captain;

    public PlayerModel() {
    }

    @Override
    public String toString() {
        return "PlayerModel{" +
                "playerId=" + getId() +
                ", playerName='" + playerName + '\'' +
                ", teamName='" + team + '\'' +
                ", totalMatches='" + totalMatches + '\'' +
                ", centuries=" + centuries +
                ", halfCenturies=" + halfCenturies +
                ", age=" + age +
                ", gender=" + gender +
                ", totalRuns=" + totalRuns +
                ", average=" + average +
                ", captain=" + captain +
                '}';
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public TeamModel getTeam() {
        return team;
    }

    public void setTeam(TeamModel team) {
        this.team = team;
    }

    public Integer getCenturies() {
        return centuries;
    }

    public void setCenturies(Integer centuries) {
        this.centuries = centuries;
    }

    public Integer getHalfCenturies() {
        return halfCenturies;
    }

    public void setHalfCenturies(Integer halfCenturies) {
        this.halfCenturies = halfCenturies;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(Integer totalRuns) {
        this.totalRuns = totalRuns;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches = totalMatches;
    }

    public boolean isCaptain() {
        return captain;
    }

    public void setCaptain(boolean captain) {
        this.captain = captain;
    }
}
