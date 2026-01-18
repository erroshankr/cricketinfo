package com.cricket.info.models;

import com.cricket.info.enums.MatchStatus;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "matches")
public class MatchModel extends BaseModel{

    @ManyToOne
    private TeamModel team1; // match me jo 1 ka value h wo team table ka ID h --> foreign key
    @ManyToOne
    private TeamModel team2; // match me jo team2 ka value h wo team table ka ID h --> foreign key
    private String venue;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private MatchStatus status;
    private String winner;
    @ManyToOne
    private TeamModel tossWinner; // IND vs SA -> IND won toss in one match but can win toss in other match also
    @OneToOne
    private PlayerModel manOfTheMatch;

    public TeamModel getTeam1() {
        return team1;
    }

    public void setTeam1(TeamModel team1) {
        this.team1 = team1;
    }

    public TeamModel getTeam2() {
        return team2;
    }

    public void setTeam2(TeamModel team2) {
        this.team2 = team2;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public TeamModel getTossWinner() {
        return tossWinner;
    }

    public void setTossWinner(TeamModel tossWinner) {
        this.tossWinner = tossWinner;
    }

    public PlayerModel getManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch(PlayerModel manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
    }
}
