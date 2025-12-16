package com.example.demo16._2._5;

public class User_with_repeat_password extends User{

    private String repeatPassword;


    public User_with_repeat_password(String name, String password, String repeatPassword) {
        super(name, password);
        this.repeatPassword = repeatPassword;
    }

    public User_with_repeat_password(int age, String password, String name, String repeatPassword) {
        super(age, password, name);
        this.repeatPassword = repeatPassword;
    }

}
