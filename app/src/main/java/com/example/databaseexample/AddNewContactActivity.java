package com.example.databaseexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.databaseexample.database.DataBaseDAO;
import com.example.databaseexample.model.Contact;

public class AddNewContactActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    DataBaseDAO dataBaseDAO;
    private EditText nameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText phoneNumberEditText;
    private Toolbar mToolbar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_new_contact);

        //Get EditText references
        nameEditText = findViewById(R.id.input_name);
        lastNameEditText = findViewById(R.id.input_lastName);
        emailEditText = findViewById(R.id.input_email);
        phoneNumberEditText = findViewById(R.id.input_phoneNumber);

         mToolbar = findViewById(R.id.toolbar);
         setSupportActionBar(mToolbar);

         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);



        //Open a connection to database
        dataBaseDAO = new DataBaseDAO(this);
        dataBaseDAO.open();



        Button button = findViewById(R.id.button_create_contact);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();

                createNewContact(name, lastName, email, phoneNumber);

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void createNewContact(String name, String lastName, String email, String phoneNumber) {

        Contact contact = new Contact(name, lastName, email, phoneNumber);
        dataBaseDAO.addContact(contact);

        Log.d(TAG, "New contact created: " + contact.toString());
        dataBaseDAO.close();
        clearEditText();


    }

    private void clearEditText() {

        nameEditText.setText("");
        lastNameEditText.setText("");
        emailEditText.setText("");
        phoneNumberEditText.setText("");
    }
}