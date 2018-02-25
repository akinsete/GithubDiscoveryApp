package gda.com.githubdiscoveryapp.searchuser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import gda.com.githubdiscoveryapp.data.models.Search;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

/**
 * Localstorage for previous searched username
 */
public class LocalDatabaseHelper extends SQLiteOpenHelper implements LocaldatabaseRepository {


    private static final int DATABASE_VERSION = 4;
    // Database Name
    private static final String DATABASE_NAME = "previous_search";
    // previous search table name
    private static final String TABLE_SEARCH = "searches";

    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_DATE = "date";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_ADDRESS = "address";


    public LocalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SEARCH_TABLE = "CREATE TABLE " + TABLE_SEARCH + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_LATITUDE + " TEXT,"
                + KEY_LONGITUDE + " TEXT,"
                + KEY_DATE + " INTEGER" + ")";
        db.execSQL(CREATE_SEARCH_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEARCH);

        // Create tables again
        onCreate(db);
    }

    @Override // Getting All Searches
    public List<Search> getPreviousSearches() {
        List<Search> searches = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SEARCH + " ORDER BY id DESC";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do{
                if(cursor != null) {
                    Search search = new Search(
                            cursor.getString(cursor.getColumnIndex(KEY_USERNAME)),
                            cursor.getString(cursor.getColumnIndex(KEY_LATITUDE)),
                            cursor.getString(cursor.getColumnIndex(KEY_LONGITUDE)),
                            cursor.getString(cursor.getColumnIndex(KEY_ADDRESS))
                    );
                    search.setDate(Long.parseLong(cursor.getString(cursor.getColumnIndex(KEY_DATE))));
                    searches.add(search);
                }
            }while (cursor.moveToNext());
        }

        return searches;
    }

    @Override
    public void saveSearch(Search search) {
        SQLiteDatabase db = this.getWritableDatabase();

        if(!checkIsDataAlreadyInDBorNot(search.getUsername())){
            ContentValues values = new ContentValues();
            values.put(KEY_USERNAME, search.getUsername());
            values.put(KEY_ADDRESS, search.getAddress());
            values.put(KEY_LONGITUDE, search.getLongitude());
            values.put(KEY_LATITUDE, search.getLatitude());
            values.put(KEY_DATE, getCurrentTimeInMilli());

            db.insert(TABLE_SEARCH, null, values);
            db.close(); // Closing database connection
        }
    }



    private  boolean checkIsDataAlreadyInDBorNot(String fieldValue) {
        SQLiteDatabase db = this.getWritableDatabase();

        String Query = "Select * from " + TABLE_SEARCH + " where username = '" + fieldValue + "'";
        Cursor cursor = db.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }


    protected long getCurrentTimeInMilli(){
        return System.currentTimeMillis();
    }
}
