package com.cricket.info.models;

import com.cricket.info.enums.Gender;
import jakarta.persistence.*;

@Entity  // This class is mapped to a table in database -> ORM -> Object Relational Mapping
@Table(name = "players")
public class PlayerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerId;
    @Column(name = "name")
    private String playerName;
    @Column(name = "team")
    private String teamName;
    private String jerseyNum;
    private Integer centuries;
    private Integer halfCenturies;
    private Integer age;
    private Gender gender;
    private Integer totalRuns;
    private Double average;

    public PlayerModel() {
    }

    @Override
    public String toString() {
        return "PlayerModel{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", teamName='" + teamName + '\'' +
                ", jerseyNum='" + jerseyNum + '\'' +
                ", centuries=" + centuries +
                ", halfCenturies=" + halfCenturies +
                ", age=" + age +
                ", gender=" + gender +
                ", totalRuns=" + totalRuns +
                ", average=" + average +
                '}';
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getJerseyNum() {
        return jerseyNum;
    }

    public void setJerseyNum(String jerseyNum) {
        this.jerseyNum = jerseyNum;
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
}
