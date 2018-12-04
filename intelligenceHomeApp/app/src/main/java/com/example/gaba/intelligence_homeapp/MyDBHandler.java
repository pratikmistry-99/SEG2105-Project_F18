package com.example.gaba.intelligence_homeapp;


import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

//class used to create the SQLite database
public class MyDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "int_eligence_homeApp.db";

    //Table 1
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_PROVIDER_PROFILE_ID = "sp_id";

    //Table 2
    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String COLUMN_SERVICE_NAME = "service_name";
    public static final String COLUMN_SERVICE_RATE = "service_rate";

    //Table 3
    public static final String TABLE_SERVICE_PROVIDERS = "serviceProviders";
    public static final String COLUMN_SERVICEID = "serviceid";
    public static final String COLUMN_USER_ID = "userid";

    //Table 4
    public static final String TABLE_SERVICE_PROVIDER_PROFILES = "serviceProvidersProfiles";
    public static final String COLUMN_PROFILE_ID = "p_id";
    public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE_NUMBER = "phone";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_LICENSE = "license";
    public static final String COLUMN_AVAILABILITY = "availability";
    public static final String COLUMN_AVG_RATING = "avg_rating";

    //Table 5
    public static final String TABLE_SERVICE_PROVIDER_REVIEWS = "serviceProvidersReviews";
    public static final String COLUMN_USER_KEY = "user_key";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_COMMENT = "num_of_ratings";


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
        // TABLE 1
        String CREATE_USERS_TABLE = "CREATE TABLE " +
                TABLE_USERS + "("
                + COLUMN_USERID + " INTEGER PRIMARY KEY,"+ COLUMN_USERNAME
                + " TEXT," + COLUMN_PASSWORD + " TEXT," + COLUMN_ROLE + " TEXT,"
                + COLUMN_PROVIDER_PROFILE_ID + " INTEGER,"
                + " FOREIGN KEY(" + COLUMN_PROVIDER_PROFILE_ID + ") REFERENCES " + TABLE_SERVICE_PROVIDER_PROFILES + "( " + COLUMN_PROFILE_ID + ")" +")";
        db.execSQL(CREATE_USERS_TABLE);

        // TABLE 2
        String CREATE_SERVICES_TABLE = "CREATE TABLE " +
                TABLE_SERVICES + "("
                + COLUMN_SERVICE_ID + " INTEGER PRIMARY KEY,"+ COLUMN_SERVICE_NAME
                + " TEXT," + COLUMN_SERVICE_RATE + " FLOAT" + ")";
        db.execSQL(CREATE_SERVICES_TABLE);


        //TABLE 3 - used to store the many to many associations between users and services
        String CREATE_SERVICES_PROVIDERS_TABLE = "CREATE TABLE " +
                TABLE_SERVICE_PROVIDERS + "("
                + COLUMN_USER_ID + " INTEGER,"+ COLUMN_SERVICEID
                + " INTEGER, FOREIGN KEY(" + COLUMN_USER_ID + ") REFERENCES " + TABLE_USERS + "( " + COLUMN_USERID + ")"
                + ", FOREIGN KEY(" + COLUMN_SERVICEID + ") REFERENCES " + TABLE_SERVICES + "( " + COLUMN_SERVICE_ID + ")" +")";
        db.execSQL(CREATE_SERVICES_PROVIDERS_TABLE);

        //TABLE 4 - stores the service providers' profile
        String CREATE_PROFILES_TABLE = "CREATE TABLE " +
                TABLE_SERVICE_PROVIDER_PROFILES + "("
                + COLUMN_PROFILE_ID + " INTEGER PRIMARY KEY,"+ COLUMN_COMPANY+" TEXT,"+COLUMN_ADDRESS + " TEXT,"
                + COLUMN_PHONE_NUMBER + " INTEGER," + COLUMN_DESCRIPTION + " TEXT," + COLUMN_LICENSE + " TEXT,"
                + COLUMN_AVAILABILITY + " TEXT,"+COLUMN_AVG_RATING + " FLOAT"+")";
        db.execSQL(CREATE_PROFILES_TABLE);

        //TABLE 5 - stores the service providers ratings
        String CREATE_REVIEWS_TABLE = "CREATE TABLE " +
                TABLE_SERVICE_PROVIDER_REVIEWS + "("
                + COLUMN_USER_KEY + " INTEGER,"+ COLUMN_RATING+" INTEGER,"+COLUMN_COMMENT + " TEXT" +")";
        db.execSQL(CREATE_REVIEWS_TABLE);

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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER_PROFILES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICE_PROVIDER_REVIEWS);
        onCreate(db);
    }


    public void addProfile(String username, String company, String address, long phoneNumber, String description, boolean isLicensed, String availability) throws Exception {
        String license = "";

        if(isLicensed)
            license = "YES";
        else
            license = "NO";


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COMPANY, company);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_PHONE_NUMBER, phoneNumber);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_LICENSE, license);
        if(!availability.equals("")){
            values.put(COLUMN_AVAILABILITY, availability);
        }
        values.put(COLUMN_AVG_RATING, 0.0);

        long id = db.insert(TABLE_SERVICE_PROVIDER_PROFILES, null, values);

        if(id ==-1)
            throw new Exception();

        ContentValues values2 = new ContentValues();
        values2.put(COLUMN_PROVIDER_PROFILE_ID, id);
        db.update(TABLE_USERS, values2, COLUMN_USERNAME + " = \"" + username + "\"", null);
        db.close();
    }
    /*
        public void updateAvailability(String availability){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_AVAILABILITY, availability);
            db.update(TABLE_SERVICE_PROVIDER_PROFILES,values,COLUMN_SERVICE_NAME + " = \"" + name + "\"", null);
            db.close();
        }
    */
    //adds new service-provider association
    public boolean addProviderToService(String username, String service) {
        ArrayList<User> serviceProviders = getServiceProviders(service);
        boolean alreadyAdded = false;
        for(int i =0 ; i < serviceProviders.size(); i++)
        {
            if(serviceProviders.get(i).getUsername().equals(username))
                alreadyAdded = true;
        }
        if(!alreadyAdded){

            SQLiteDatabase db = this.getWritableDatabase();
            int uId;
            int sId;

            String query1 = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
            Cursor cursor1 = db.rawQuery(query1, null);

            String query2 = "Select * FROM " + TABLE_SERVICES + " WHERE " + COLUMN_SERVICE_NAME + " = \"" + service + "\"";
            Cursor cursor2 = db.rawQuery(query2, null);

            if (cursor1.moveToFirst() && cursor2.moveToFirst()) {
                uId = Integer.parseInt(cursor1.getString(0));
                sId = Integer.parseInt(cursor2.getString(0));

                ContentValues values = new ContentValues();
                values.put(COLUMN_USER_ID, uId);
                values.put(COLUMN_SERVICEID, sId);
                db.insert(TABLE_SERVICE_PROVIDERS, null, values);
                db.close();
            }

            return true;
        }
        return false;
    }



    public void deleteProviderToService(String username, String service) {
        SQLiteDatabase db = this.getWritableDatabase();
        int uId;
        int sId;

        String query1 = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor cursor1 = db.rawQuery(query1, null);

        String query2 = "Select * FROM " + TABLE_SERVICES + " WHERE " + COLUMN_SERVICE_NAME + " = \"" + service + "\"";
        Cursor cursor2 = db.rawQuery(query2, null);

        if(cursor1.moveToFirst() && cursor2.moveToFirst()) {
            uId = Integer.parseInt(cursor1.getString(0));
            sId = Integer.parseInt(cursor2.getString(0));
            db.delete(TABLE_SERVICE_PROVIDERS, COLUMN_USER_ID + " = \"" + uId + "\"" + ""
                    + " AND " + COLUMN_SERVICEID + " = \"" + sId + "\"", null);
        }
        else
            throw new NullPointerException();
        db.close();
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

    // returns an ArrayList of all services
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
        values.put(COLUMN_PROVIDER_PROFILE_ID, -1);

        db.insert(TABLE_USERS, null, values);
        db.close();
    }

    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
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
     * method erases all services from database
     */
    public void clearServiceListTables(){
        SQLiteDatabase db = this.getWritableDatabase();
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

    // deletes the service from the database
    public void deleteService(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SERVICES, COLUMN_SERVICE_NAME + " = \"" + name + "\"", null);
        db.close();
    }

    // updates the rate of the service from the database
    public void updateService(String name, double rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SERVICE_NAME, name);
        values.put(COLUMN_SERVICE_RATE, rate);

        db.update(TABLE_SERVICES,values,COLUMN_SERVICE_NAME + " = \"" + name + "\"", null);
        db.close();
    }


    public boolean hasProfile(String username){
        boolean b = false;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_USERS  +" WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int a = cursor.getInt(cursor.getColumnIndex(COLUMN_PROVIDER_PROFILE_ID));
            System.out.println(username);
            if(a >=0)
                b = true;
            System.out.println(b);
        }
        db.close();
        return b;
    }

    public boolean updateAvailability(String username, String availability){
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_AVAILABILITY, availability);

            int a =-1;
            String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                a = cursor.getInt(cursor.getColumnIndex(COLUMN_PROVIDER_PROFILE_ID));

            }
            db = this.getWritableDatabase();
            db.update(TABLE_SERVICE_PROVIDER_PROFILES,values,COLUMN_PROFILE_ID + " = \"" + a + "\"", null);
            db.close();
        }
        catch (Exception e){
            return false;
        }
        return true;
    }


    public String getAvailabilities(String username){
        String a = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int i = cursor.getInt(cursor.getColumnIndex(COLUMN_PROVIDER_PROFILE_ID));
            String query2 = "Select * FROM " + TABLE_SERVICE_PROVIDER_PROFILES + " WHERE " + COLUMN_PROFILE_ID + " = \"" + i + "\"";
            Cursor cursor2 = db.rawQuery(query2, null);
            if (cursor2.moveToFirst())
                a = cursor2.getString(cursor2.getColumnIndex(COLUMN_AVAILABILITY));

        }
        db.close();
        return a;
    }

    public String getServiceProviderInfo(String username){
        String a = "";
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            int i = cursor.getInt(cursor.getColumnIndex(COLUMN_PROVIDER_PROFILE_ID));
            String query2 = "Select * FROM " + TABLE_SERVICE_PROVIDER_PROFILES + " WHERE " + COLUMN_PROFILE_ID + " = \"" + i + "\"";
            Cursor cursor2 = db.rawQuery(query2, null);
            if (cursor2.moveToFirst())
                a += "\n----------------------------------------\n Service Provider Profile\n----------------------------------------\n   Company: "+cursor2.getString(cursor2.getColumnIndex(COLUMN_COMPANY));
                a += "\n  Address: "+cursor2.getString(cursor2.getColumnIndex(COLUMN_ADDRESS));
                a += "\n  Phone Number:  "+cursor2.getString(cursor2.getColumnIndex(COLUMN_PHONE_NUMBER));
                a += "\n  Description:  "+cursor2.getString(cursor2.getColumnIndex(COLUMN_DESCRIPTION));
                a += "\n  License: "+cursor2.getString(cursor2.getColumnIndex(COLUMN_LICENSE));
                a += "\n  Rating: "+cursor2.getString(cursor2.getColumnIndex(COLUMN_AVG_RATING));

        }
        db.close();
        return a;
    }

    public void addReview(String username, int rating, String comment){
        SQLiteDatabase db = this.getReadableDatabase();

        int id;
        double currRating = 0;
        String query = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = \"" + username + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            id = cursor.getInt(cursor.getColumnIndex(COLUMN_USERID));
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_KEY, id);
            values.put(COLUMN_RATING, rating);
            values.put(COLUMN_COMMENT, comment);

            int i = cursor.getInt(cursor.getColumnIndex(COLUMN_PROVIDER_PROFILE_ID));
            String query2 = "Select * FROM " + TABLE_SERVICE_PROVIDER_REVIEWS + " WHERE " + COLUMN_USER_KEY + " = \"" + id + "\"";
            Cursor cursor2 = db.rawQuery(query2, null);
            int countRatings = cursor2.getCount();

            query2 = "Select * FROM " + TABLE_SERVICE_PROVIDER_PROFILES + " WHERE " + COLUMN_PROFILE_ID + " = \"" + i + "\"";
            cursor2 = db.rawQuery(query2, null);
            if(cursor2.moveToFirst()) {
                currRating = cursor2.getDouble(cursor2.getColumnIndex(COLUMN_AVG_RATING));
            }

            ContentValues values2 = new ContentValues();
            double avgRating = (currRating*countRatings+rating)/(countRatings+1);
            values2.put(COLUMN_AVG_RATING, avgRating);
            db = this.getWritableDatabase();
            db.insert(TABLE_SERVICE_PROVIDER_REVIEWS, null, values);
            db.update(TABLE_SERVICE_PROVIDER_PROFILES,values2,COLUMN_PROFILE_ID + " = \"" + i + "\"", null);


        }

        db.close();
    }


    public ArrayList<User> getServiceProviders(String serviceName) {
        ArrayList<User> providers = new ArrayList<User>();
        SQLiteDatabase db = this.getReadableDatabase();
        int s_id = -1;
        ArrayList<Integer> u_id = new ArrayList<>();

        // table services
        String query = "Select * FROM " + TABLE_SERVICES + " WHERE " + COLUMN_SERVICE_NAME + " = \"" + serviceName + "\"";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            s_id = cursor.getInt(cursor.getColumnIndex(COLUMN_SERVICE_ID));
            System.out.println("s_id = "+s_id);
            /*while(!cursor.isAfterLast()){
                u_id.add(cursor.getInt(cursor.getColumnIndex(COLUMN_US)));
            }*/

        }

        //table serviceProviders
        String query2 = "Select * FROM " + TABLE_SERVICE_PROVIDERS + " WHERE " + COLUMN_SERVICEID + " = \"" + s_id + "\"";
        Cursor cursor2 = db.rawQuery(query2, null);
        if(cursor2.moveToFirst()){
            while(!cursor2.isAfterLast()){
                u_id.add(cursor2.getInt(cursor2.getColumnIndex(COLUMN_USER_ID)));
                System.out.println("u_id = "+cursor2.getInt(cursor2.getColumnIndex(COLUMN_USER_ID)));
                cursor2.moveToNext();
            }
        }

        //table users
        for(int i = 0; i < u_id.size(); i++){
            String query3 = "Select * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERID + " = \"" + u_id.get(i) + "\"";
            Cursor cursor3 = db.rawQuery(query3, null);
            if (cursor3.moveToFirst()){
                String username, password, role;
                username = cursor3.getString(cursor3.getColumnIndex(COLUMN_USERNAME));
                password = cursor3.getString(cursor3.getColumnIndex(COLUMN_PASSWORD));
                role = cursor3.getString(cursor3.getColumnIndex(COLUMN_ROLE));
                User user = new User(username, password, role);
                System.out.println(user);
                providers.add(user);
            }
        }

        return providers;
    }
}
/*
    //Table 1
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERID = "user_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ROLE = "role";
    public static final String COLUMN_PROVIDER_PROFILE_ID = "sp_id";

    //Table 2
    public static final String TABLE_SERVICES = "services";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String COLUMN_SERVICE_NAME = "service_name";
    public static final String COLUMN_SERVICE_RATE = "service_rate";

    //Table 3
    public static final String TABLE_SERVICE_PROVIDERS = "serviceProviders";
    public static final String COLUMN_SERVICEID = "serviceid";
    public static final String COLUMN_USER_ID = "userid";
    */