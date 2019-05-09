package sg.edu.rp.dmsd.p04ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "Note.db";

    private static final String TABLE_NOTE = "Note";
    private static final String COLUMN_ID =  "id";
    private static final String COLUMN_NOTECONTENT = "noteContent";
    private static final String COLUMN_STARS = "stars";

    public DBHelper( Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_NOTE + "("
                + COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NOTECONTENT + "TEXT,"
                + COLUMN_STARS + " TEXT )";
        db.execSQL(createTableSql);
        Log.i("info","created tables");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
       onCreate(db);
    }

    public void  insertNote(String noteContent,String stars){

        // Get an instance of the database for writing
        SQLiteDatabase db = this.getWritableDatabase();

        // We use ContentValues object to store the values for
        //  the db operation
        ContentValues values = new ContentValues();

        values.put(COLUMN_NOTECONTENT,noteContent);
        values.put(COLUMN_STARS,stars);

        db.insert(TABLE_NOTE,null,values);
        db.close();
    }

    public ArrayList<String> getNoteContent(){
        ArrayList<String> notes = new ArrayList<String>();


        String selectQuery = "SELECT " + COLUMN_NOTECONTENT
                + " FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                notes.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();
        return notes;
    }

    public  ArrayList<Note> getTasks(){
        ArrayList<Note> tasks = new ArrayList<Note>();
        String query = "SELECT " + COLUMN_ID + ", "
                + COLUMN_NOTECONTENT + ", "
                + COLUMN_STARS
                + " FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String description = cursor.getString(1);
                String date = cursor.getString(2);
                Note obj = new Note(id, description, date);
                tasks.add(obj);
            }while(cursor.moveToFirst());
        }cursor.close();
        db.close();
        return  tasks;
    }

}
