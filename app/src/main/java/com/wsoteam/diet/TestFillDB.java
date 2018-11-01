package com.wsoteam.diet;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wsoteam.diet.POJOS.DescriptionOfDiet;
import com.wsoteam.diet.POJOS.GlobalObject;
import com.wsoteam.diet.POJOS.GroupOfFood;
import com.wsoteam.diet.POJOS.ItemOfGlobalBase;
import com.wsoteam.diet.POJOS.ListOfGroupsFood;
import com.wsoteam.diet.POJOS.ListOfPOJO;

import java.util.ArrayList;
import java.util.List;

public class TestFillDB {

    public static void fiilDB(ListOfPOJO listOfPOJO){
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

        GlobalObject globalObject = new GlobalObject("GB", groupOfFoodListOfGroupsFood,
                listOfPOJO, descriptionOfDiet);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(Config.NAME_OF_ENTITY_FOR_DB);

        myRef.setValue(globalObject);
    }
}
