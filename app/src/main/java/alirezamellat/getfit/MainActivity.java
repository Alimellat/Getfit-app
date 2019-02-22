package alirezamellat.getfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.color.black;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs = null;
    boolean Gender;
    TextView bmitext,recommendedtext,reqcal,curcalo;
    double bmii,recwei1,recwei2,reqcalories,currcalonum;
    Fooddatabase db1=new Fooddatabase(this);
    informationdb db=new informationdb(this);
    Activedatabase db2=new Activedatabase(this);









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        final String PREFS_NAME = "MyPrefsFile";
        prefs = getSharedPreferences("com.alirezamellat.getfit", MODE_PRIVATE);
        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false







            //arranging the food database
            db1.insertvoid("Low fat milk,1cup",String.valueOf(102),0);
            db1.insertvoid("Feta cheese,28g",String.valueOf(75),0);
            db1.insertvoid("Apple juice,1/2cup",String.valueOf(60),0);
            db1.insertvoid("orange juice,1/2cup",String.valueOf(59),0);
            db1.insertvoid("Boiled egg,",String.valueOf(79),0);
            db1.insertvoid("Nuts,1/2cup",String.valueOf(380),0);
            db1.insertvoid("Medium Apple",String.valueOf(81),0);
            db1.insertvoid("Medium Banana",String.valueOf(105),0);
            db1.insertvoid("Medium Orange",String.valueOf(62),0);
            db1.insertvoid("Kebab,85g",String.valueOf(226),0);
            db1.insertvoid("Medium Carrot",String.valueOf(31),0);
            db1.insertvoid("bread,1/4 loaf",String.valueOf(79),0);
            db1.insertvoid("Grilled Fish,85g",String.valueOf(136),0);




            //arranging the activities database
            db2.insertvoid("Walking",String.valueOf(280),0);
            db2.insertvoid("Hiking",String.valueOf(370),0);
            db2.insertvoid("Swimming",String.valueOf(510),0);
            db2.insertvoid("Weight lifting",String.valueOf(440),0);






            //arranging the information database


            db.insertvoid("reqcalo",toString().valueOf(0));
            db.insertvoid("recomweight2",toString().valueOf(0));
            db.insertvoid("recomweight1",toString().valueOf(0));
            db.insertvoid("bmivalue",toString().valueOf(0));
            db.insertvoid("CURRENT_CALLO",toString().valueOf(0));
            Toast.makeText(this, "First time added!!!!!!!!!", Toast.LENGTH_SHORT).show();






            // using the following line to edit/commit prefs
            prefs.edit().putBoolean("firstrun", false).commit();
        }








        bmitext=(TextView)findViewById(R.id.bmitext);
        recommendedtext=(TextView)findViewById(R.id.rcweighttext);
        reqcal=(TextView)findViewById(R.id.req_caloritext);
        curcalo=(TextView)findViewById(R.id.current_calorie_text);





        Cursor c=db.selectOneVoid("bmivalue");
        c.moveToFirst();
       String BMI=c.getString(c.getColumnIndex("number"));
        bmii= Double.parseDouble(BMI);
        String shape=new String();

        c.close();
        if(bmii<18){
            shape="thin";
            bmitext.setBackgroundColor(getResources().getColor(R.color.low));

        }
        else{ if(bmii<25){


            shape="fit";
            bmitext.setBackgroundColor(getResources().getColor(R.color.fit));

        }
        else{



            shape="fat";
            bmitext.setBackgroundColor(getResources().getColor(R.color.extra));
        }




        }

        BMI="Your bmi:"+BMI+"\nyou seem to be "+shape;
        bmitext.setText(BMI);
        c=db.selectOneVoid("recomweight1");
        c.moveToFirst();
        BMI=c.getString(c.getColumnIndex("number"));
        recwei1= Double.parseDouble(BMI);

        c.close();

        c=db.selectOneVoid("recomweight2");
        c.moveToFirst();
        BMI=c.getString(c.getColumnIndex("number"));
        recwei2= Double.parseDouble(BMI);

        c.close();
        String RECOMMENDED1="recommended weight is from "+String.valueOf(recwei1)+" to "  +String.valueOf(recwei2)+" kg";
        recommendedtext.setText(RECOMMENDED1);



        c=db.selectOneVoid("reqcalo");
        c.moveToFirst();
        BMI=c.getString(c.getColumnIndex("number"));
        reqcalories= Double.parseDouble(BMI);
        BMI="required calories per day based on your information is :"+BMI;
        reqcal.setText(BMI);
        c.close();



        c=db.selectOneVoid("CURRENT_CALLO");
        c.moveToFirst();
        BMI=c.getString(c.getColumnIndex("number"));
        currcalonum= Double.parseDouble(BMI);



        curcalo.setText(BMI);










        double[]activityrate={1.2,1.375,1.55,1.725,1.9};




    }






    //when user clicks proceed within the first welcome screen
    public void secondwelcome(View view) {

        setContentView(R.layout.welcome1);
    }








    //when user clicks proceed within the seconds welcome screen
    public void gotomain(View view) {
        setContentView(R.layout.activity_main);
    }

    public void gotobmi(View view) {
        Intent i1=new Intent(this,Bmi_activity.class);
        startActivity(i1);
    }

    public void manageclories(View view) {
        Intent i2=new Intent(this,caloriemanager.class);


        startActivity(i2);
    }

    public void start_more_activity(View view) {
        Intent morei=new Intent(this,more_activity.class);
        startActivity(morei);
    }
}
