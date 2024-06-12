package com.example.databaseexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseexample.adapter.ContactAdapter;
import com.example.databaseexample.database.DataBaseDAO;
import com.example.databaseexample.model.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ContactAdapter contactAdapter;
    private ArrayList<Contact> contactArrayList;
    DataBaseDAO dataBaseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Open a connection to database
        dataBaseDAO = new DataBaseDAO(this);
        dataBaseDAO.open();

        contactArrayList = getContactFromDataBase();

        mRecyclerView = findViewById(R.id.contact_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);

        contactAdapter = new ContactAdapter(contactArrayList);
        mRecyclerView.setAdapter(contactAdapter);

        final Button button = findViewById(R.id.add_new_contact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent downloadIntent = new Intent(getApplicationContext(), AddNewContactActivity.class);
                startActivity(downloadIntent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactArrayList.clear();
        contactArrayList.addAll(getContactFromDataBase());
        contactAdapter.notifyDataSetChanged();


    }

    private ArrayList<Contact> getContactFromDataBase() {

        ArrayList contactList = dataBaseDAO.getAllContacts();
        return contactList;
    }
}