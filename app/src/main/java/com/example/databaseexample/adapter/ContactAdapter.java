package com.example.databaseexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseexample.R;
import com.example.databaseexample.model.Contact;

import java.util.ArrayList;

/**
 * Created by Sheeraz on 6/12/2024.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder>{

    private ArrayList<Contact> contactArrayList;

    public ContactAdapter(ArrayList<Contact> _contactArrayList) {

        this.contactArrayList = _contactArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_contact, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Contact contact = contactArrayList.get(position);

        holder.userName.setText(contact.getName());
        holder.userLastName.setText(contact.getLastName());
        holder.userEmail.setText(contact.getEmail());
        holder.userPhoneNumber.setText(contact.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView userName;
        TextView userLastName;
        TextView userEmail;
        TextView userPhoneNumber;

        public MyViewHolder(@NonNull View view) {
            super(view);

            userName = view.findViewById(R.id.userName);
            userLastName = view.findViewById(R.id.last_name);
            userEmail = view.findViewById(R.id.email);
            userPhoneNumber = view.findViewById(R.id.phone_number);

        }
    }

}
