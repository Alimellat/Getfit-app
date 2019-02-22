package alirezamellat.getfit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by DearUser on 17/02/2017.
 */





//this class is for the database which tells whether it is the first program is running and also saves the values as needed
public class Infodb extends SQLiteOpenHelper {
    static String databaseName="Info";
    static int databaseVersion=1;
    static String tableName="infonum";
    static String fieldId="_id";
    static String fieldName="name";
    static Context context1;
    static String fieldnum="number";
    public Infodb(Context context) {

        super(context, databaseName, null, databaseVersion);;
        context1=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTableQuery="CREATE TABLE "+tableName + " ( "
                + fieldId + " integer primary key autoincrement , "
                + fieldName + " text , "
                + fieldnum +" text )";
        db.execSQL(CreateTableQuery);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+tableName );
        onCreate(db);
    }







    //to insert sth into the database
    public void insertvoid(String name,String num){
        try{
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(fieldName,name);
        cv.put(fieldnum,num);
        db.insert(tableName,null,cv);
        db.close();}
        catch(Exception a){
            Toast.makeText(context1, "oops! an error has just occurred,please try again", Toast.LENGTH_LONG).show();


        }
    }
    public void updateVoid (String name,String num ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(fieldName,name);
        cv.put(fieldnum,num);
        db.update(tableName,cv,fieldName + " = ? ",new String[]{name});

        db.close();
    }

}



