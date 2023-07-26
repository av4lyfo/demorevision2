package sg.edu.rp.c346.id22013834.demorevision2;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import kotlinx.coroutines.scheduling.Task;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "tasks.db";

    private static final String TABLE_POKEMON = "pokemon";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TYPE ="type";
    private static final String COLUMN_POWER = "power";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_POKEMON +  "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_POWER + " REAL,"
                + COLUMN_TYPE + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info" ,"created tables");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {

        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POKEMON);
        // Create table(s) again
        onCreate(db);
    }
    public void insertTask(String type, double power){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();
        // Store the column name as key and the description as value
        values.put(COLUMN_TYPE, type);
        // Store the column name as key and the date as value
        values.put(COLUMN_POWER, power);
        // Insert the row into the TABLE_TASK
        db.insert(TABLE_POKEMON, null, values);
        // Close the database connection
        db.close();
    }
    public ArrayList<pokemon> getpokemon() {
        // Create an ArrayList that holds String objects
        ArrayList<pokemon> tasks = new ArrayList<pokemon>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TYPE, COLUMN_POWER};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_POKEMON, columns, null, null, null, null, null, null);

        // moveToFirst() moves to first row, null if no records
        if (cursor.moveToFirst()) {
            // Loop while moveToNext() points to next row
            //  and returns true; moveToNext() returns false
            //  when no more next row to move to
            do {
                int id = cursor.getInt(0);
                String type = cursor.getString(1);
                double power = cursor.getDouble(2);
                pokemon obj = new pokemon(id, type, power);
                // Add the task content to the ArrayList object
                //  getString(0) retrieves first column data
                //  getString(1) return second column data
                //  getInt(0) if data is an integer value
                tasks.add(obj);
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return tasks;
    }
    public int updatepokemon(pokemon data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TYPE, data.getType());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};

        int result = db.update(TABLE_POKEMON, values, condition, args);
        db.close();
        return result;
    }
    public void updatePowerByType(String type,double power){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_POWER,power);

        String condition =
    }



}





