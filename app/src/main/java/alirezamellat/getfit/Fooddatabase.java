package alirezamellat.getfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by DearUser on 22/02/2017.
 */

public class Fooddatabase extends SQLiteOpenHelper {
    static String databaseName="foodsdata";
    static int databaseVersion=1;
    static String tableName="foods10";
    static String fieldId="_id";
    static String fieldName="name";
    static Context context1;
    static String fieldnum="number";
    static String fieldamount="amount";
    public Fooddatabase(Context context) {

        super(context, databaseName, null, databaseVersion);;
        context1=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableQuery="CREATE TABLE "+tableName + " ( "
                + fieldId + " integer primary key autoincrement , "
                + fieldName + " text , "
                + fieldnum +" text , "
                + fieldamount +" text )";
        db.execSQL(CreateTableQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tableName );
        onCreate(db);
    }







    //to insert sth into the database
    public void insertvoid(String name,String num,int amount){
        try{
            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues cv=new ContentValues();
            cv.put(fieldName,name);
            cv.put(fieldnum,num);
            cv.put(fieldamount,String.valueOf(amount));
            db.insert(tableName,null,cv);
            db.close();}
        catch(Exception a){
            Toast.makeText(context1, "oops! an error has just occurred,please try again", Toast.LENGTH_LONG).show();


        }
    }



    public Cursor selectAllvoid(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from "+tableName ,null);
        return c;
    }
    public  Cursor selectOneVoid(String NAME){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("select * from foods10 where name= '"+ NAME+"'",null);
        return c;
    }



    public  Cursor selectidvoid(int id){
        SQLiteDatabase db=this.getReadableDatabase();
       Cursor c=db.rawQuery("select * from foods10 where _id= "+ id,null);
        return c;
    }














    public void updateVoid (String name,String num,int amount ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(fieldName,name);
        cv.put(fieldnum,num);
        cv.put(fieldamount,String.valueOf(amount));
        db.update(tableName,cv,fieldName + " = ? ",new String[]{name});

        db.close();
    }







    //set the amount of each food to zero
    public void resetvoid(){
        String query="update "+tableName + " set "
                + fieldamount +" = 0 ";
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL(query);
    }
}
