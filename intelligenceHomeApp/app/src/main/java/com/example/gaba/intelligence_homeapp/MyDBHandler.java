package com.example.gaba.intelligence_homeapp;


import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "int_elligence.db";

    //Table 1
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";

    //Table 2
    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String COLUMN_SERVICE_NAME = "service_name";
    public static final String COLUMN_SERVICE_RATE = "service_rate";

    /**
     * Constructor of class MyDBHandler
     * @param context
     */
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    /**
     * method create a data base table which is consist of 4 columns with information of user id, name, password and role
     * @param db SQLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_USERID + " INTEGER PRIMARY KEY,"+ COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_ROLE + " TEXT" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        String CREATE_SERVICES_TABLE = "CREATE TABLE " +
                TABLE_SERVICES + "("
                + COLUMN_SERVICE_ID + " INTEGER PRIMARY KEY,"+ COLUMN_SERVICE_NAME
                + " TEXT," + COLUMN_SERVICE_RATE + " FLOAT" + ")";
        db.execSQL(CREATE_SERVICES_TABLE);



    }
    /**
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES);
        onCreate(db);
    }

    //adds new service
    public void addService(Service service) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICE_NAME, service.getName());
        values.put(COLUMN_SERVICE_RATE, service.getRate());
        db.insert(TABLE_SERVICES, null, values);
        db.close();
    }


    public ArrayList<Service> getAllServices(){
        ArrayList<Service> serviceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_SERVICES;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Service service = new Service();
                service.setName(cursor.getString(cursor.getColumnIndex(COLUMN_SERVICE_NAME)));
                service.setRate((double) cursor.getFloat(cursor.getColumnIndex(COLUMN_SERVICE_RATE)));
                serviceList.add(service);
                cursor.moveToNext();
            }
        }

        db.close();
        return serviceList;

    }

    /**
     * method adds a new user to data base table
     * @param user User
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());
        values.put(COLUMN_ROLE, user.getRole());
        db.insert(TABLE_USERS, null, values);
        db.close();
    }
    /**
     * method search for String user name in data base, and it returns the user's information if user exist
     * @param username
     * @return user User / null
     */
    public User findUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();

        if(cursor.moveToFirst()) {
            user.setUser_id(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setRole(cursor.getString(3));
        } else  {
            user = null;
        }
        db.close();
        return user;
    }
    /**
     * method search for String admin name in data base, and it returns the admin's information if user exist
     * @return user User / null
     */
    public User findAdmin() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_ROLE + " = \"" + "Administrator" + "\"";
        Cursor cursor = db.rawQuery(query, null);
        User user = new User();

        if(cursor.moveToFirst()) {
            user.setUser_id(Integer.parseInt(cursor.getString(0)));
            user.setUsername(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            user.setRole(cursor.getString(3));
        } else  {
            user = null;
        }
        db.close();
        return user;
    }

    /**
     * method erases user information from data base (id, name, password, role)
     * @param username
     * @return result Boolean
     */
    public boolean deleteUser(String username) {
        boolean result = false;
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()) {
            String idStr = cursor.getString(0);
            db.delete(TABLE_USERS, COLUMN_USERID + " = " + idStr, null);
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
    /**
     * method checks if admin's account has been created already
     * @param admin
     * @return result Boolean
     */
    //Returns True if just a SINGLE Admin user is existing in the database
    public boolean checkAdmin(String admin){
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_ROLE + " = \"" + admin + "\"";
        Cursor cursor = db.rawQuery(query, null);
        User user = new User(); //Unnecessary

        boolean result;
        //cursor is at the row where "admin" value would be found
        //but how to assure its the ONLY one??? and is my logic above correct?
        if(cursor.moveToFirst()) {
            result = true;

        } else  {
            result = false;
        }
        db.close();
        return result;
    }
    /**
     * method erases database
     */
    public void clearAllTables(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS,null,null);
        db.delete(TABLE_SERVICES,null,null);
        db.close();
    }

    /**
     *
     * @return result String
     */
    public String getAllUsernames(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);
        String result = "\n\nUser Accounts: ";
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String role = cursor.getString(cursor.getColumnIndex(COLUMN_ROLE));
                result += "\n"+ name + ": "+ role;
                cursor.moveToNext();
            }
        }
        db.close();
        return result;
    }
}

