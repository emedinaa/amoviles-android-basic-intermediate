package com.academiamoviles.myapp.model;

/**
 * @author : Eduardo Medina
 * @see : https://developer.android.com/index.html
 * @since : 5/16/18
 */
public class User {

    private int id;
    private String name;
    private String lastName;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

    //metodos de acceso set get

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
