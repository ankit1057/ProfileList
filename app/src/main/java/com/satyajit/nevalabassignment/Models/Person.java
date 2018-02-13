package com.satyajit.nevalabassignment.Models;

/**
 * Created by Satyajit on 10/02/18
 */


public class Person {

    private String name;
    private String skills;
    private String image;

    public Person(String name, String skills, String image) {

        this.name = name;
        this.skills = skills;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
    public String getImage() {

        return image;
    }

    public void setImage(String image) {

        this.image = image;
    }
}