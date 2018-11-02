package com.wsoteam.diet.POJOS;

import java.util.List;

public class ListOfRecipes {

private String name;
private List<ItemRecipes> listRecipes;

    public ListOfRecipes(){
        String url = "https://i2.wp.com/yaturistka.ru/uploads/images/7/7/a/d/announce_big_7.png";
        String body = "Существуют две основные трактовки понятия «текст»: имманентная (расширенная, философски нагруженная) и репрезентативная (более частная). Имманентный подход подразумевает отношение к тексту как к автономной реальности, нацеленность на выявление его внутренней структуры. Репрезентативный — рассмотрение текста как особой формы представления знаний о внешней тексту действительности.";
        this.name = "Test";
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
