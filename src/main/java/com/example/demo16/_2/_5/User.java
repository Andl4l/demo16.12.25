package com.example.demo16._2._5;

public class User {
    public String name;
    private String password;
    public int age;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(int age, String password, String name) {
        this.age = age;
        this.password = password;
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
