package com.wsoteam.diet;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wsoteam.diet.POJOS.DescriptionOfDiet;
import com.wsoteam.diet.POJOS.GlobalObject;
import com.wsoteam.diet.POJOS.GroupOfFood;
import com.wsoteam.diet.POJOS.ItemOfGlobalBase;
import com.wsoteam.diet.POJOS.ItemRecipes;
import com.wsoteam.diet.POJOS.ListOfGroupsFood;
import com.wsoteam.diet.POJOS.ListOfGroupsRecipes;
import com.wsoteam.diet.POJOS.ListOfPOJO;
import com.wsoteam.diet.POJOS.ListOfRecipes;

import java.util.ArrayList;
import java.util.List;

public class TestFillDB {

    public static void fiilDB(ListOfPOJO listOfPOJO) {
        DescriptionOfDiet descriptionOfDiet = new DescriptionOfDiet("name", "main", "faq",
                "exit", "contradictions");


        ItemOfGlobalBase itemOfGlobalBase = new ItemOfGlobalBase("name", "des", "com",
                "prop", "cal", "prot",
                "fat", "car", "url");

        List<ItemOfGlobalBase> itemOfGlobalBases = new ArrayList<>();
        itemOfGlobalBases.add(itemOfGlobalBase);
        itemOfGlobalBases.add(itemOfGlobalBase);
        itemOfGlobalBases.add(itemOfGlobalBase);
        itemOfGlobalBases.add(itemOfGlobalBase);
        itemOfGlobalBases.add(itemOfGlobalBase);

        GroupOfFood groupOfFood = new GroupOfFood("name", itemOfGlobalBases, "url");

        List<GroupOfFood> groupOfFoods = new ArrayList<>();
        groupOfFoods.add(groupOfFood);
        groupOfFoods.add(groupOfFood);
        groupOfFoods.add(groupOfFood);
        groupOfFoods.add(groupOfFood);

        ListOfGroupsFood groupOfFoodListOfGroupsFood = new ListOfGroupsFood("name", groupOfFoods);

        ItemRecipes itemRecipes = new ItemRecipes("name", "url", "body");

        List<ItemRecipes> itemsRecipes = new ArrayList<>();
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        ListOfRecipes listOfRecipes1 = new ListOfRecipes("name0", "url", itemsRecipes);

        List<ItemRecipes> itemsRecipes2 = new ArrayList<>();
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        ListOfRecipes listOfRecipes2 = new ListOfRecipes("name1", "url", itemsRecipes2);

        List<ItemRecipes> itemsRecipes3 = new ArrayList<>();
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        ListOfRecipes listOfRecipes3 = new ListOfRecipes("name2", "url", itemsRecipes3);

        List<ItemRecipes> itemsRecipes4 = new ArrayList<>();
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        ListOfRecipes listOfRecipes4 = new ListOfRecipes("name3", "url", itemsRecipes4);

        List<ItemRecipes> itemsRecipes5 = new ArrayList<>();
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        ListOfRecipes listOfRecipes5 = new ListOfRecipes("name4", "url", itemsRecipes5);

        List<ItemRecipes> itemsRecipes6 = new ArrayList<>();
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        itemsRecipes.add(itemRecipes);
        ListOfRecipes listOfRecipes6 = new ListOfRecipes("name5", "url", itemsRecipes6);



        List<ListOfRecipes> listOfRecipes = new ArrayList<>();
        listOfRecipes.add(listOfRecipes1);
        listOfRecipes.add(listOfRecipes2);
        listOfRecipes.add(listOfRecipes3);
        listOfRecipes.add(listOfRecipes4);
        listOfRecipes.add(listOfRecipes5);
        listOfRecipes.add(listOfRecipes6);

        ListOfGroupsRecipes listOfGroupsRecipes = new ListOfGroupsRecipes("name", listOfRecipes);




        GlobalObject globalObject = new GlobalObject("GB", groupOfFoodListOfGroupsFood,
                listOfPOJO, descriptionOfDiet, listOfGroupsRecipes);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Config.NAME_OF_ENTITY_FOR_DB);

        myRef.setValue(globalObject);
    }
}
