package com.example.androidlesson;

class Contact {
    private final String name;
    private final String phoneNumber;

    static final Contact[] contacts = {
            new Contact("Бабочка", "89512065656"),
            new Contact("Мотылёк", "89124591131"),
            new Contact("Займы", "89005553535")
    };

    private Contact(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    String getName(){
        return name;
    }

    String getPhoneNumber(){
        return phoneNumber;
    }
}