package samer.app.vtracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //General:
    private static DatabaseHelper instance;
    private static final String TAG = "DatabaseHelper.java";
    //Database info:
    private static final String DATABASE_NAME = "Countries.db";
    private static final int DATABASE_VERSION = 1;
    //Tables:
    private static final String TABLE_COUNTRIES = "countries";
    //Columns:
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_COUNTRY_NAME = "country_name";
    private static final String COLUMN_NUMBER_INFECTED = "number_infected";
    private static final String COLUMN_NUMBER_RECOVERED = "number_recovered";
    private static final String COLUMN_NUMBER_DECEASED = "number_deceased";

    //Create table:
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_COUNTRIES + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_COUNTRY_NAME + " TEXT, " + COLUMN_NUMBER_INFECTED + " TEXT, " + COLUMN_NUMBER_RECOVERED + " TEXT, " + COLUMN_NUMBER_DECEASED + " TEXT)";

    //The static getInstance() method ensures that only one DatabaseHelper will ever exist at any given time.
    //If the instance object has not been initialized, one will be created. If one has already been created then it will simply be returned.
    public static synchronized DatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i(TAG, "Table has been created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            String sql = "DROP TABLE IF EXISTS " + TABLE_COUNTRIES;
            db.execSQL(sql);
            onCreate(db);
        }
    }

    public long addItem(Country country) {
        SQLiteDatabase database = getWritableDatabase();
        long ID = -1;
        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_COUNTRY_NAME, country.getCountry_name());
            values.put(COLUMN_NUMBER_INFECTED, country.getNumber_infected());
            values.put(COLUMN_NUMBER_RECOVERED, country.getNumber_recovered());
            values.put(COLUMN_NUMBER_DECEASED, country.getNumber_deceased());

            //Add values to database w/ error handling:
            ID = database.insertOrThrow(TABLE_COUNTRIES, null, values);
            database.setTransactionSuccessful();
            Log.i(TAG, "COUNTRY ADDED");
        } catch (Exception e) {
            Log.e(TAG, "Unable to add item to database");
        } finally {
            Log.d(TAG, "New Item: " + country.toString());
            database.endTransaction();
        }
        return ID;
    }

    public void updateItem(Country country) {
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_COUNTRY_NAME, country.getCountry_name());
            values.put(COLUMN_NUMBER_INFECTED, country.getNumber_infected());
            values.put(COLUMN_NUMBER_RECOVERED, country.getNumber_recovered());
            values.put(COLUMN_NUMBER_DECEASED, country.getNumber_deceased());

            database.update(TABLE_COUNTRIES, values, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(country.getID())});
            database.setTransactionSuccessful();
            Log.i(TAG, "ITEM UPDATED");
        } catch (Exception e) {
            Log.e(TAG, "Unable to UPDATE item to database");
        } finally {
            Log.d(TAG, country.toString());
            database.endTransaction();
        }

    }

    public List<Country> getAllItemsFromDatabase() {
        List<Country> list_items = new ArrayList<>();
        String SELECT_QUERY = "SELECT * FROM " + TABLE_COUNTRIES;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Country country = new Country();
                    country.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                    country.setCountry_name(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY_NAME)));
                    country.setNumber_infected(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_INFECTED)));
                    country.setNumber_recovered(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_RECOVERED)));
                    country.setNumber_deceased(cursor.getString(cursor.getColumnIndex(COLUMN_NUMBER_DECEASED)));
                    list_items.add(country);
                    Log.i(TAG, country.toString());//print note to console
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Unable to get data from local database");
        } finally {
            Log.i(TAG, "Total rows (# of fields) = " + cursor.getCount());
            Log.d(TAG, list_items.toString());
            //check if cursor is closed, if not > close
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
                Log.i(TAG, "Cursor is closed");
            }
        }
        database.close();
        Log.d(TAG, "VIP_DB: " + list_items.size());
        Log.d(TAG, "VIP_DB_ListFromDB: " + list_items.toString());
        return list_items;
    }

    public void deleteItem(Country country) {
        long ID = country.getID();
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            database.delete(TABLE_COUNTRIES, COLUMN_ID + "=?", new String[]{String.valueOf(ID)});
            database.setTransactionSuccessful();
            Log.i(TAG, "COUNTRY DELETED");
            Log.d(TAG, country.toString());
        } catch (Exception e) {
            Log.d(TAG, "Unable to delete country");
        } finally {
            database.endTransaction();
        }
    }

    public void deleteAllItemsFromDatabase() {
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            database.delete(TABLE_COUNTRIES, null, null);
            database.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Unable to delete all countries from database");
        } finally {
            database.endTransaction();
        }
    }

    public String getDatabaseName() {
        return DATABASE_NAME;
    }
}
