package code.admin.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost;
import android.app.TabActivity;
import java.util.ArrayList;
import java.util.List;
import android.widget.AdapterView;
public class MainActivity extends TabActivity {
    private restaurant r = new restaurant();
    private List<restaurant> reslist = new ArrayList<restaurant>();
    private RestaurantAdap adapter = null;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.save);
        button.setOnClickListener(onSave);
        ListView list = (ListView)findViewById(R.id.restaurants);
        adapter = new RestaurantAdap();
        list.setAdapter(adapter);
        // Phần bổ sung cho Tab
        TabHost.TabSpec spec  = getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List",getResources().getDrawable(R.drawable.u2));
        getTabHost().addTab(spec);

        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details",
                getResources().getDrawable(R.drawable.u1));
        getTabHost().addTab(spec);

        getTabHost().setCurrentTab(0);
        list.setOnItemClickListener(onClickList);

    }
    private AdapterView.OnItemClickListener onClickList = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            restaurant r = reslist.get(position);
            EditText name,address;
            RadioGroup types;
            name = (EditText)findViewById(R.id.name);
            address = (EditText)findViewById(R.id.addr);
            types = (RadioGroup)findViewById(R.id.types);

            name.setText(r.getName());
            address.setText(r.getAddress());
            if (r.getType().equals("Sit down"))
                types.check(R.id.sit_down);
            else if (r.getType().equals("Take out"))
                types.check(R.id.take_out);
            else
                types.check(R.id.delivery);
            getTabHost().setCurrentTab(1);

        }
    };

    class RestaurantAdap extends ArrayAdapter<restaurant>
    {
        public RestaurantAdap(Context context,int resource,int textViewResource){
            super(context,resource,textViewResource);
        }
        public RestaurantAdap(){
            super(MainActivity.this,android.R.layout.simple_list_item_1,reslist);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View row = convertView;
            if(row == null)
            {
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row,null);

            }
            restaurant r = reslist.get(position);

            ((TextView)row.findViewById(R.id.title)).setText(r.getName());
            ((TextView)row.findViewById(R.id.address)).setText(r.getAddress());
            ImageView icon = (ImageView)row.findViewById(R.id.icon);
            String type = r.getType();
            if (type.equals("Take out"))
                icon.setImageResource(R.drawable.u1);
            else if (type.equals("Sit down"))
                icon.setImageResource(R.drawable.u2);
            else
                icon.setImageResource(R.drawable.u3);
            return row;
        }
    }
    private View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
                restaurant r = new restaurant();
                EditText name = (EditText)findViewById(R.id.name);
                EditText address = (EditText)findViewById(R.id.addr);

                r.setName(name.getText().toString());
                r.setAddress(address.getText().toString());

                RadioGroup type = (RadioGroup)findViewById(R.id.types);
                switch (type.getCheckedRadioButtonId())
                {
                    case R.id.take_out:
                        r.setType("Take out");
                        break;
                    case R.id.sit_down:
                        r.setType("Sit down");
                        break;
                    case R.id.delivery:
                        r.setType("Delivery");
                        break;
                }
                reslist.add(r);
            Toast.makeText(getApplicationContext(), r.toString(),Toast.LENGTH_LONG).show();
            }
    };

}
