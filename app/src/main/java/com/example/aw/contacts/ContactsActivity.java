/*
 * contacts
 * com.example.aw.contacts
 *
 * Created by Ash Wu on 20/07/18 12:05 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.example.aw.contacts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.aw.contacts.adapter.ContactsAdapter;
import com.example.aw.contacts.loader.ContactsLoader;
import com.example.aw.contacts.model.Contact;

import java.util.List;

public class ContactsActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        RecyclerView listView = findViewById(R.id.contacts_list_view);
        listView.setLayoutManager(new LinearLayoutManager(this));
        
        if (hasPermission(Manifest.permission.READ_CONTACTS)) {
            ContactsLoader loader = new ContactsLoader(this);
            List<Contact> data = loader.getContacts();
            ContactsAdapter adapter = new ContactsAdapter(data);
            listView.setAdapter(adapter);
        }
    }
    
    private Boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean hasPermission = (ContextCompat.checkSelfPermission(this,permission) == PackageManager.PERMISSION_GRANTED);
            if (!hasPermission) {
                ActivityCompat.requestPermissions(this,new String[] {permission},1);
                return false;
            }
            return true;
        }
        return true;
    }
}
