package com.example.gameroom;

public class Igrashki {
    private int id;
    private String name;
    private int age;
    private int price;

    public Igrashki(int id, String name, int price, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public Igrashki(String name, int price, int age) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPrice() {
        return price;
    }

    public void setprice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
