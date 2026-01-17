package com.cricket.info.enums;

public enum MatchStatus {
    SCHEDULED("Scheduled"),
    IN_PROGRESS("In-Progress"),
    COMPLETED("Completed");

    private String name;

    MatchStatus(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
