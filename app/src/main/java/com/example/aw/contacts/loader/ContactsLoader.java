/*
 * contacts
 * com.example.aw.contacts.loader
 *
 * Created by Ash Wu on 20/07/18 11:56 AM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.example.aw.contacts.loader;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.aw.contacts.model.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactsLoader {
    
    private Activity context;
    
    private List<Contact> contacts = new ArrayList<>();
    
    private final static Uri CONTACTS_URI = ContactsContract.Data.CONTENT_URI;
    
    public ContactsLoader(Activity context) {
        this.context = context;
        Cursor cursor = context.getContentResolver().query(CONTACTS_URI,null,null,null,ContactsContract.Contacts.DISPLAY_NAME);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
                    String email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
                    contacts.add(new Contact(name,email));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }
    
    public List<Contact> getContacts() {
        return contacts;
    }
}
