package alirezamellat.getfit;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class caloriemanager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caloriemanager);









    }

    public void managefoods(View view) {
        foodfragment Foods=new foodfragment();





        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();

        ft.replace(R.id.frame_layout,Foods,"feed");
        ft.commit();

    }


    public void activi_manage(View view) {
        activityfragment Acti=new activityfragment();
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frame_layout,Acti);
        ft.commit();

    }
}
