package com.example.databaseexample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.databaseexample.model.Contact;

import java.util.ArrayList;

/**
 * Created by Sheeraz on 6/12/2024.
 */

public class DataBaseDAO {

    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase sqLiteDatabase;

    public DataBaseDAO(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {

        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
    }

    public void close() {

        dataBaseHelper.close();
    }

    //Insert a new contact on data base
    public void addContact(Contact contact) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COLUMN_NAME, contact.getName());
        contentValues.put(DataBaseHelper.COLUMN_LAST_NAME, contact.getLastName());
        contentValues.put(DataBaseHelper.COLUMN_EMAIL, contact.getEmail());
        contentValues.put(DataBaseHelper.COLUMN_PHONE_NUMBER, contact.getPhoneNumber());

        sqLiteDatabase.insert(DataBaseHelper.TABLE_CONTACTS, null, contentValues);
    }

    //Get all contacts from data base
    public ArrayList<Contact> getAllContacts() {

        ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_CONTACTS, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {

            Contact contact = new Contact();
            contact.setName(cursor.getString(1));
            contact.setLastName(cursor.getString(2));
            contact.setEmail(cursor.getString(3));
            contact.setPhoneNumber(cursor.getString(4));
            contactArrayList.add(contact);

            cursor.moveToNext();

        }

        cursor.close();
        return contactArrayList;
    }
}
