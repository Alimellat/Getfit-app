package alirezamellat.getfit;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Bmi_activity extends AppCompatActivity {
boolean Gender;
    double calorate,neededcalo;
    informationdb db=new informationdb(this);


    double bmi,bmr,weight,height,age,recom1,recom2;
    EditText weitext,heitext,agetext;
    double[]activityrate={1.2,1.375,1.55,1.725,1.9};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_activity);
        final Spinner genderspinner=(Spinner)findViewById(R.id.genderspinner);
        Spinner activespinner=(Spinner)findViewById(R.id.actspinner);
        String[] gendersarray=getResources().getStringArray(R.array.gender);
        String[] activearray=getResources().getStringArray(R.array.active);
        ArrayAdapter<String> genderadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,gendersarray);//gender spinner
        ArrayAdapter<String> activeadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,activearray);//activity rating spinner
        genderspinner.setAdapter(genderadapter);
        activespinner.setAdapter(activeadapter);
        genderspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                    Gender=true;
                else
                    Gender=false;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {



            }
        });
        activespinner=(Spinner)findViewById(R.id.actspinner);
        activespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                calorate=activityrate[position];


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





    }


    public void calculate_bmi_bmr(View view) {
        weitext=(EditText)findViewById(R.id.weighttext);
        heitext=(EditText)findViewById(R.id.heighttext);
        agetext=(EditText)findViewById(R.id.age_text);

        if((weitext.getText().toString().trim().length() == 0) || (agetext.getText().toString().trim().length() == 0) || (heitext.getText().toString().trim().length() == 0)){

            Toast.makeText(this, "There are empty fields,please fill them ", Toast.LENGTH_SHORT).show();
            age=0;
            height=0;
            weight=0;





        }
        else{
            weight= Double.parseDouble(weitext.getText().toString().trim());
            height=Double.parseDouble(heitext.getText().toString().trim());
            age=Double.parseDouble(agetext.getText().toString().trim());



            if((130>age) && (age>0) && (weight>0) && (weight<500 )  && (height>0) && (height<350)){
                height/=100;
                bmi=(weight/(height*height));//calculate bmi
                bmi=(double) Math.round(bmi*100)/100;

                //recommended weights
                recom1=18*height*height;
                recom1=(double) Math.round(recom1*100)/100;
                recom2=25*height*height;
                recom2=(double) Math.round(recom2*100)/100;






                height*=100;







                if(Gender){//calculate bmr
                    bmr=66 + (13.7 * weight ) + (5 * height) - (6.8*age);


                }
                else{



                    bmr=655 + (9.6 * weight) + (1.8*height) - (4.7* age);
                }
                neededcalo=bmr*calorate;
                neededcalo=(double) Math.round(neededcalo*100)/100;












                Intent i1=new Intent(this,MainActivity.class);
                i1.putExtra("bmivalue",bmi);
                i1.putExtra("caloneed",neededcalo);



                try{



                    db.updateVoid("recomweight1",toString().valueOf(recom1));
                    db.updateVoid("bmivalue",toString().valueOf(bmi));

                    db.updateVoid("recomweight2",toString().valueOf(recom2));
                    db.updateVoid("reqcalo",toString().valueOf(neededcalo));



                    //  db.insertvoid("bmi",0);//insert anjam shod
                    //int run;


                    Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();








                }
                catch(Exception a){

                    Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
                }


                startActivity(i1);
















            }
            else
                Toast.makeText(this, "Invalid inputs", Toast.LENGTH_SHORT).show();







        }


    }

}
