package alirezamellat.getfit;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class edit extends AppCompatActivity {
    TextView nametext;
    TextView calotext;
    double current_callories,currnet_amount,currentamountof;
    int new_amount;
    EditText editText;
    String AMOUNT;
    String NAME;
    String curamount;

    Fooddatabase db1=new Fooddatabase(this);
    informationdb db=new informationdb(this);
    Activedatabase db2=new Activedatabase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent i2=getIntent();
        int ID=i2.getIntExtra("id",0);
        int ver=i2.getIntExtra("type",0);
        nametext=(TextView)findViewById(R.id.editnametext);
        calotext=(TextView)findViewById(R.id.edicalotext);
        Fooddatabase db=new Fooddatabase(this);
        Activedatabase dba=new Activedatabase(this);



        if (ver==2){




            Cursor c=db.selectidvoid(ID);
            c.moveToFirst();
             NAME=c.getString(c.getColumnIndex("name"));
            nametext.setText(NAME);
            AMOUNT=c.getString(c.getColumnIndex("number"));
             curamount=c.getString(c.getColumnIndex("amount"));

            calotext.setText("calories per amount :\n"+AMOUNT+"\ncurrent amount:\n"+curamount);
            currentamountof= Double.parseDouble(curamount);















        }
        else {

            Cursor c=dba.selectidvoid(ID);
            c.moveToFirst();
             NAME=c.getString(c.getColumnIndex("name"));
            nametext.setText(NAME);
            AMOUNT=c.getString(c.getColumnIndex("number"));
             curamount=c.getString(c.getColumnIndex("amount"));

            calotext.setText("calories per hour :\n"+AMOUNT+"\n current hours per day:\n"+curamount);
            currentamountof= Double.parseDouble(curamount);






        }








    }

    public void applychanges(View view) {

editText=(EditText)findViewById(R.id.editamounttext);
        if(editText.getText().toString().trim().length() == 0){


            Toast.makeText(this, "Empty!!!!!!!!!", Toast.LENGTH_SHORT).show();
        }





else{


            Cursor c=db.selectOneVoid("CURRENT_CALLO");
            c.moveToFirst();
            String BMI=c.getString(c.getColumnIndex("number"));
            current_callories= Double.parseDouble(BMI);


            c.close();
        //    editText=(EditText)findViewById(R.id.editamounttext);
            new_amount= Integer.parseInt(editText.getText().toString().trim());

            currnet_amount= Double.parseDouble(AMOUNT);
            Intent i2=getIntent();

            int ver=i2.getIntExtra("type",0);
            if(ver==2){

                db1.updateVoid(NAME,AMOUNT,new_amount);
                new_amount-=currentamountof;
                current_callories=current_callories+(currnet_amount*new_amount);
                db.updateVoid("CURRENT_CALLO",String.valueOf(current_callories));
            }
            else{
                db2.updateVoid(NAME,AMOUNT,new_amount);
                new_amount-=currentamountof;
                current_callories=current_callories-(currnet_amount*new_amount);
                db.updateVoid("CURRENT_CALLO",String.valueOf(current_callories));
            }
            Intent ii=new Intent(this,MainActivity.class);
            startActivity(ii);








        }





    }
}
