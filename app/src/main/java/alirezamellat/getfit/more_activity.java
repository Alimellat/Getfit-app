package alirezamellat.getfit;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class more_activity extends AppCompatActivity {




    Fooddatabase db1=new Fooddatabase(this);
    informationdb db=new informationdb(this);
    Activedatabase db2=new Activedatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_activity);
    }

    public void resetdata(View view) {


//dset data on each database to zero
        db.resetvoid();
        db1.resetvoid();
        db2.resetvoid();
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void go_to_help(View view) {
        Intent intent=new Intent(this,helpactivity.class);
        intent.putExtra("type",1);
        startActivity(intent);
    }

    public void go_to_about(View view) {
        Intent intent=new Intent(this,helpactivity.class);
        intent.putExtra("type",2);
        startActivity(intent);
    }
}
