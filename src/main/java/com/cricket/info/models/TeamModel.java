package com.cricket.info.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "teams")
public class TeamModel extends BaseModel{

    // fields for a cricket team
    private String name;
    private String country;

    @OneToMany
    private List<PlayerModel> players;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
