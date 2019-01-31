package com.wsoteam.diet.POJOProfile;

import com.orm.SugarRecord;

public class Profile extends SugarRecord {
    private String firstName;
    private String lastName;
    private boolean isFemale;
    private int age;
    private int height;
    private int weight;
    private int exerciseStress;
    private int waterCount;
    private String firstEnter;
    private int loseWeight;
    private String photoUrl;

    public Profile() {
    }

    public Profile(String firstName, String lastName, boolean isFemale, int age, int height, int weight, int exerciseStress, int waterCount, String firstEnter, int loseWeight, String photoUrl) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isFemale = isFemale;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.exerciseStress = exerciseStress;
        this.waterCount = waterCount;
        this.firstEnter = firstEnter;
        this.loseWeight = loseWeight;
        this.photoUrl = photoUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public void setFemale(boolean female) {
        isFemale = female;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getExerciseStress() {
        return exerciseStress;
    }

    public void setExerciseStress(int exerciseStress) {
        this.exerciseStress = exerciseStress;
    }

    public int getWaterCount() {
        return waterCount;
    }

    public void setWaterCount(int waterCount) {
        this.waterCount = waterCount;
    }

    public String getFirstEnter() {
        return firstEnter;
    }

    public void setFirstEnter(String firstEnter) {
        this.firstEnter = firstEnter;
    }

    public int getLoseWeight() {
        return loseWeight;
    }

    public void setLoseWeight(int loseWeight) {
        this.loseWeight = loseWeight;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
