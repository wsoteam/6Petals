package com.wsoteam.diet.POJOProfile;

import com.orm.SugarRecord;

public class Profile extends SugarRecord {
    private String firstName;
    private String lastName;
    private boolean isFemale;
    private int age;
    private int height;
    private int weight;
    private String exerciseStress;
    private int waterCount;
    private String firstEnter;
    private int loseWeight;
    private String photoUrl;

    private int maxKcal;
    private int maxProt;
    private int maxFat;
    private int maxCarbo;

    private String difficultyLevel;

    public Profile() {
    }

    public Profile(String firstName, String lastName, boolean isFemale, int age, int height, int weight, String exerciseStress, int waterCount, String firstEnter, int loseWeight, String photoUrl, int maxKcal, int maxProt, int maxFat, int maxCarbo, String difficultyLevel) {
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
        this.maxKcal = maxKcal;
        this.maxProt = maxProt;
        this.maxFat = maxFat;
        this.maxCarbo = maxCarbo;
        this.difficultyLevel = difficultyLevel;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getMaxKcal() {
        return maxKcal;
    }

    public void setMaxKcal(int maxKcal) {
        this.maxKcal = maxKcal;
    }

    public int getMaxProt() {
        return maxProt;
    }

    public void setMaxProt(int maxProt) {
        this.maxProt = maxProt;
    }

    public int getMaxFat() {
        return maxFat;
    }

    public void setMaxFat(int maxFat) {
        this.maxFat = maxFat;
    }

    public int getMaxCarbo() {
        return maxCarbo;
    }

    public void setMaxCarbo(int maxCarbo) {
        this.maxCarbo = maxCarbo;
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

    public String getExerciseStress() {
        return exerciseStress;
    }

    public void setExerciseStress(String exerciseStress) {
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
