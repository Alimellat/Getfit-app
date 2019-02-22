package alirezamellat.getfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class helpactivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpactivity);
        Intent intent=this.getIntent();
        int type=intent.getIntExtra("type",0);
        textView=(TextView)findViewById(R.id.helptext);


        //check type to determine which string it must show
        if(type==1)
            textView.setText(getResources().getString(R.string.help_string));
        else
            textView.setText(getResources().getString(R.string.about));
    }

}
