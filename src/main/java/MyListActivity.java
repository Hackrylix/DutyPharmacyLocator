package be.itsworking.dpl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyListActivity extends Activity
{
    private ListView listView = null;
    private ArrayList<MyPharmacy> pharmacyList;

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_list);
        setUpListIfNeeded();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setUpListIfNeeded();
    }

    private void setUpListIfNeeded()
    {
        if(pharmacyList==null)
        {
            Intent sender = getIntent();
            Bundle extras = sender.getExtras();
            Object pl = extras.get("pl");
            pharmacyList = new ArrayList<MyPharmacy>();
            if (pl instanceof ArrayList)
            {
                pharmacyList = (ArrayList<MyPharmacy>) pl;
            }
            else
                new Error("not instance of arraylist").printStackTrace();
        }
        if (listView == null)
        {
            listView = (ListView) findViewById(R.id.lv_pharmacy);
            if (listView != null)
            {
                //Collections.sort(pharmacyList);
                String[] params = new String[pharmacyList.size()];
                int i = 0;
                for (MyPharmacy pharmacy : pharmacyList)
                {
                    params[i] = pharmacy.getName()+"\r\n"+pharmacy.getAdresse()+"\r\n"+pharmacy.getCp()+" "+pharmacy.getCity();
                    i++;
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, params);

                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new OnItemClickListener()
                {
                    public void onItemClick(@SuppressWarnings("rawtypes") AdapterView parent, View v, int position, long id)
                    {
                        MyPharmacy pharmacy = pharmacyList.get(position);
                        Intent myPharmacyIntent = new Intent(getApplication(), MyPharmacyActivity.class);
                        myPharmacyIntent.putExtra("pharmacy", pharmacy);
                        startActivity(myPharmacyIntent);

                    }
                });
            }
        }

    }




}
