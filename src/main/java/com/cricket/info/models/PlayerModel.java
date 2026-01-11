package com.cricket.info.models;

import com.cricket.info.enums.Gender;

public class PlayerModel {

    private String playerName;
    private String teamName;
    private String jerseyNum;
    private int centuries;
    private int halfCenturies;
    private int age;
    private Gender gender;
    private int totalRuns;
    private double average;

    public PlayerModel() {
    }

    public PlayerModel(String playerName, String teamName, int age, Gender gender, String jerseyNum) {
        this.playerName = playerName;
        this.teamName = teamName;
        this.age = age;
        this.gender = gender;
        this.jerseyNum = jerseyNum;
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


    public int getCenturies() {
        return centuries;
    }

    public void setCenturies(int centuries) {
        this.centuries = centuries;
    }

    public int getHalfCenturies() {
        return halfCenturies;
    }

    public void setHalfCenturies(int halfCenturies) {
        this.halfCenturies = halfCenturies;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        return "PlayerModel{" +
                "playerName='" + playerName + '\'' +
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
}
