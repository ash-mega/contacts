/*
 * contacts
 * com.example.aw.contacts.adapter
 *
 * Created by Ash Wu on 20/07/18 3:20 PM.
 * Copyright (c) 2018 mega.co.nz
 */
package com.example.aw.contacts.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aw.contacts.R;
import com.example.aw.contacts.model.Contact;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
    
    private List<Contact> data;
    
    private Context context;
    
    public ContactsAdapter(List<Contact> data) {
        this.data = data;
    }
    
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup,int i) {
        context = viewGroup.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_contact,viewGroup,false);
        return new ViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder,int i) {
        Contact contact = data.get(i);
        viewHolder.avatarView.setImageBitmap(createDefaultAvatar());
        viewHolder.nameView.setText(contact.getName());
        viewHolder.emailView.setText(contact.getEmail());
    }
    
    @Override
    public int getItemCount() {
        return data.size();
    }
    
    private Bitmap createDefaultAvatar() {
        Bitmap defaultAvatar = Bitmap.createBitmap(100,100,Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(defaultAvatar);
        Paint paintCircle = new Paint();
        
        paintCircle.setColor(ContextCompat.getColor(context,R.color.colorAccent));
        paintCircle.setAntiAlias(true);
        
        int radius;
        if (defaultAvatar.getWidth() < defaultAvatar.getHeight()) {
            radius = defaultAvatar.getWidth() / 2;
        } else {
            radius = defaultAvatar.getHeight() / 2;
        }
        c.drawCircle(defaultAvatar.getWidth() / 2,defaultAvatar.getHeight() / 2,radius,paintCircle);
        
        Paint paintText = new Paint();
        paintText.setColor(Color.WHITE);
        paintText.setTextSize(23);
        paintText.setAntiAlias(true);
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setSubpixelText(true);
        paintText.setStyle(Paint.Style.FILL);
        Typeface face = Typeface.SANS_SERIF;
        paintText.setTypeface(face);
        
        String firstLetter = randomLetter();
        Rect bounds = new Rect();
        paintText.getTextBounds(firstLetter,0,1,bounds);
        int xPos = c.getWidth() / 2;
        int yPos = (int)((c.getHeight() / 2) - ((paintText.descent() + paintText.ascent() / 2)) + 20);
        c.drawText(firstLetter.toUpperCase(Locale.getDefault()),xPos,yPos,paintText);
        
        return defaultAvatar;
    }
    
    private String randomLetter() {
        String [] characters = new String[] {
          "速度","的","三大","sd","阿斯大","全文","哦坑","每次","不错","哦我","万恶","体育","于"
        };
        return characters[new Random().nextInt(characters.length)];
    }
    
    static class ViewHolder extends RecyclerView.ViewHolder {
        
        TextView nameView;
        
        TextView emailView;
        
        ImageView avatarView;
        
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarView = itemView.findViewById(R.id.contact_avatar);
            nameView = itemView.findViewById(R.id.contact_name);
            emailView = itemView.findViewById(R.id.contact_email);
        }
    }
}

