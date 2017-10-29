package com.example.rohasoft.idus.others;

/**
 * Created by Edinbridge on 10/24/2017.
 */

public class User {
    String name,email,phone,password;

    public User(String name, String email, String phone, String password){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public User(String email, String password){

        this.email=email;
        this.password=password;
        this.name="";
        this.phone= "";

    }

}
