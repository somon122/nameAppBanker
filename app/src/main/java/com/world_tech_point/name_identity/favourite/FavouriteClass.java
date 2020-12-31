package com.world_tech_point.name_identity.favourite;

public class FavouriteClass {

    private String id , category,name, meaning;

    public FavouriteClass() {}

    public FavouriteClass(String id, String category, String name, String meaning) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.meaning = meaning;
    }

    public FavouriteClass(String category, String name, String meaning) {
        this.category = category;
        this.name = name;
        this.meaning = meaning;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
