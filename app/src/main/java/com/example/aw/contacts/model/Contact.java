/*
 * contacts
 * com.example.aw.contacts.model
 *
 * Created by Ash Wu on 20/07/18 12:19 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.example.aw.contacts.model;

public class Contact {
    
    private String name;
    
    private String email;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Contact(String name,String email) {
        
        this.name = name;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

