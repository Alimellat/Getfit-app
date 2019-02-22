package alirezamellat.getfit;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by DearUser on 22/02/2017.
 */

public class activityfragment extends ListFragment {



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Context cont;
        cont=getActivity();
        Activedatabase db=new Activedatabase(cont);
        Cursor c=db.selectAllvoid();
        String[] fields={Fooddatabase.fieldId,Fooddatabase.fieldName,Fooddatabase.fieldnum,Fooddatabase.fieldamount};
        int[] ids={R.id.textViewidlistView_layout,R.id.textViewnamelistView_layout,R.id.textViewcalorielistView_layout,R.id.textViewcurrentlistView_layout};
        SimpleCursorAdapter sca=new SimpleCursorAdapter(cont,R.layout.list_view_layput,c,fields,ids,0);
        setListAdapter(sca);

    }





    public activityfragment(){};


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);


        Intent i2=new Intent(getActivity(),edit.class);
        position++;
        i2.putExtra("id",position);
        i2.putExtra("type",1);


        startActivity(i2);
    }
}




