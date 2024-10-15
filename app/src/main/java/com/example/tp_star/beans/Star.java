package com.example.tp_star.beans;

public class Star {
    private int id;
    private String name;
    private String img;
    private float star;

    private static int comp = 0;

    public Star(String name, String img, float star){
        this.id = ++comp;
        this.name = name;
        this.star = star;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
    }
}
