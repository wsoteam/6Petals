package com.wsoteam.diet.POJOS;

public class ItemRecipes {

    private String name;
    private String url;
    private String body;

    public ItemRecipes(String name, String url, String body){
        this.name = name;
        this.url = url;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
