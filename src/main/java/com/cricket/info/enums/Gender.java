package com.cricket.info.enums;

public enum Gender {
    MALE("M"),
    FEMALE("F");

    private String name;

    Gender(String name){
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
