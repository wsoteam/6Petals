package com.wsoteam.diet.POJOS;

import java.util.ArrayList;
import java.util.List;

public class ListOfRecipes {

private String name;
private List<ItemRecipes> listRecipes;

    public ListOfRecipes(){
        listRecipes = new ArrayList<>(); // ты лошара, не было этой строки, работал с нулевым объектом
        String url = "https://firebasestorage.googleapis.com/v0/b/dietnew-1ce77.appspot.com/o/0-4.jpg?alt=media&token=34ae4632-e7ff-4300-835b-0ca67e583544";
        String body = "Существуют две основные трактовки понятия «текст»: имманентная (расширенная, философски нагруженная) и репрезентативная (более частная). Имманентный подход подразумевает отношение к тексту как к автономной реальности, нацеленность на выявление его внутренней структуры. Репрезентативный — рассмотрение текста как особой формы представления знаний о внешней тексту действительности.";
        this.name = "Test";
        this.listRecipes = new ArrayList<>();
        for (int i =0; i < 100; i++) {
            listRecipes.add(new ItemRecipes("Recipe - " + i, url, body));
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemRecipes> getListRecipes() {
        return listRecipes;
    }

    public void setListRecipes(List<ItemRecipes> listRecipes) {
        this.listRecipes = listRecipes;
    }
}
