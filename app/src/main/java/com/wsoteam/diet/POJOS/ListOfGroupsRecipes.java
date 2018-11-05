package com.wsoteam.diet.POJOS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListOfGroupsRecipes implements Serializable {

    private String name;
    private List<ListOfRecipes> listOfGroupsRecipes;

    public ListOfGroupsRecipes() {
        this.listOfGroupsRecipes = new ArrayList<>();
        this.name = "test";
        for (int i = 0; i < 10; i++){
            this.listOfGroupsRecipes.add(new ListOfRecipes());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListOfRecipes> getListOfGroupsRecipes() {
        return listOfGroupsRecipes;
    }

    public void setListOfGroupsRecipes(List<ListOfRecipes> listOfGroupsRecipes) {
        this.listOfGroupsRecipes = listOfGroupsRecipes;
    }

}
