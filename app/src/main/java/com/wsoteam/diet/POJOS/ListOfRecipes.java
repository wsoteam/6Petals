package com.wsoteam.diet.POJOS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListOfRecipes implements Serializable {

private String name;
private String urlGroup;
private List<ItemRecipes> listRecipes;

    public ListOfRecipes(){
        listRecipes = new ArrayList<>(); // ты лошара, не было этой строки, работал с нулевым объектом
        urlGroup = "https://firebasestorage.googleapis.com/v0/b/dietnew-1ce77.appspot.com/o/4-5.jpg?alt=media&token=a55d6603-fb79-4ed5-9820-2cf35a4b0921";
        String url = "https://firebasestorage.googleapis.com/v0/b/dietnew-1ce77.appspot.com/o/0-4.jpg?alt=media&token=34ae4632-e7ff-4300-835b-0ca67e583544";
        String body = "<p></p> <h2>ИНГРЕДИЕНТЫ ДЛЯ РЕЦЕПТА &ldquo;РИС С АНАНАСОМ&rdquo;:</h2> <p>Ананас (средний) &mdash; 2 шт.<br />" +
                "Рис &mdash; 4 ст. (отварить)<br />Масло растительное &mdash; 4 ст.л.<br />Чеснок &mdash; 3 зуб.<br />Кешью &mdash; " +
                "0.5 ст.<br />Зеленый горошек &mdash; 0.5 ст.<br />Яйцо куриное &mdash; 1 шт.<br />Изюм &mdash; 1/4 ст.<br />Бульон &mdash; " +
                "3 ст.л.<br />Лук-шалот &mdash; 2 шт.<br />Перцы Чили &mdash; 1 шт.<br />Лук зеленый &mdash; 3 шт.<br />Кориандр &mdash; треть " +
                "чашки<br />Рыбный соус &mdash; 3 ст.л.<br />Приправа (Карри) &mdash; По вкусу<br />Сахар &mdash; 1 ч.л.</p> <h2>КАК ПРИГОТОВИТЬ \"РИС С АНАНАСОМ\"</h2>" +
                " <p>Сначала отварите рис. Варить рис нужно по обычной технологии. Чтобы зерна не слипались, добавьте ложку масла и тщательно перемешайте.</p> " +
                "<p>Далее вам необходимо мелко нарезать лук, чеснок и перцы чили. Теперь обжарьте овощи в масле на сильном огне (не более 1 минуты).</p> <p>После " +
                "этого мы будем добавлять остальные ингредиенты к овощной смеси. Первым делом разбиваем в овощи яйцо и быстро перемешиваем. Затем заливаем сковороду " +
                "бульоном и рыбным соусом, сверху посыпаем приправой карри и сахаром. Продолжаем перемешивать смесь.</p> <p>\"Остается добавить орехи кешью и жарить" +
                " овощи еще около минуты. Получившуюся смесь смешиваем с отварным рисом.</p>";
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

    public String getUrlGroup() {
        return urlGroup;
    }

    public void setUrlGroup(String urlGroup) {
        this.urlGroup = urlGroup;
    }

    public List<ItemRecipes> getListRecipes() {
        return listRecipes;
    }

    public void setListRecipes(List<ItemRecipes> listRecipes) {
        this.listRecipes = listRecipes;
    }
}
