package com.example.francis.hyscoreapp.DataBaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.francis.hyscoreapp.Model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francis on 09-Jun-18.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    //my database version
    private static final int DataBase_Version = 1;
    //database name
    private static final String DataBase_Name = "UserManager.db";
    //user table name
    private static final String Table_User = "user";
    //user table columns names
    private static final String Column_User_id = "user_id";
    private static final String Column_User_Name = "user_name";
    private static final String Column_User_Email = "user_email";
    private static final String Column_User_Password = "user_password";

    //create sql query
    private String Create_user_table = "Create Table " + Table_User + "(" +
            Column_User_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Column_User_Name +
            " TEXT, " + Column_User_Email + " TEXT, " + Column_User_Password + " TEXT " + ")";

    //drop sql table
    private String Drop_User_Table = "Drop Table IF Exists " + Table_User;

    //Constructor @context
    public DataBaseHelper(Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
    }
    //methos that will execute create user function
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_user_table);
    }
    // creating a method onUpGrade that will drop table if it exists then if it isnt it will create a new database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Drop_User_Table);
        onCreate(db);
    }

    //This method is to create user record
    public void addUser(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(Column_User_Name, user.getName());
        values.put(Column_User_Email, user.getEmail());
        values.put(Column_User_Password, user.getPassword());

        //inserting row
        db.insert(Table_User, null, values);
        db.close();
    }
    //thsi method is fetch all user and return the list of all user records @return list

    public List<User> getAllUser() {
        //array Of columns to fetch
        String[] columns = {Column_User_id,
                Column_User_Name, Column_User_Email, Column_User_Password};

        //sorting orders

        String sortOrder =
                Column_User_Name + "ASC";
        List<User> userList = new ArrayList<User>();

        SQLiteDatabase db = this.getReadableDatabase();

        //query the user table
        /*
        * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */

        Cursor cursor = db.query(Table_User,//table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order

        //Traversing through all the rows and adding to the list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Column_User_id))));
                user.setName(cursor.getString(cursor.getColumnIndex(Column_User_Name)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(Column_User_Email)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(Column_User_Password)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }
    // this method deletes userrecord

    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        //delete user record by id
        db.delete(Table_User, Column_User_id + "=?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    //This method checks whether the user email exists or not
    public boolean check(String email) {
        //array of columns to fetch
        String[] columns = {Column_User_id};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = Column_User_Email + " = ?";
        //selection Argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'chimefk282@gmail.com';
         */
        Cursor cursor = db.query(Table_User, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;

    }

    /**
     * This method to check user email and password exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                Column_User_id
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = Column_User_Email + " = ?" + " AND " + Column_User_Password + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(Table_User, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;

    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                Column_User_id
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = Column_User_Email + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(Table_User, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
}
