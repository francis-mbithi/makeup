package com.moringaschool.makeups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    @BindView(R.id.findMakeupButton) Button mFindMakeupButton;
//    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @BindView(R.id.locationTextView) TextView mLocationTextView;

    @BindView(R.id.listView) ListView mListView;

    private String[] makeup = new String[] {"Sweet Hereafter", "Cricket", "Hawthorne Fish House", "Viking Soul Food", "Red Square", "Horse Brass", "Dick's Kitchen", "Taco Bell", "Me Kha Noodle Bar", "La Bonita Taqueria", "Smokehouse Tavern", "Pembiche", "Kay's Bar", "Gnarly Grey", "Slappy Cakes", "Mi Mero Mole" };
    private String[] cosmetics= new String[] {"Vegan Food", "Breakfast", "Fishs Dishs", "Scandinavian", "Coffee", "English Food", "Burgers", "Fast Food", "Noodle Soups", "Mexican", "BBQ", "Cuban", "Bar Food", "Sports Bar", "Breakfast", "Mexican" };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLocationTextView = (TextView) findViewById(R.id.locationTextView);
        mListView = (ListView) findViewById(R.id.listView);

        MakeupArrayAdapter adapter = new MakeupArrayAdapter(this, android.R.layout.simple_list_item_1, makeup, cosmetics); // must match constructor!
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(MainActivity.this, "Makeup", Toast.LENGTH_LONG).show();

            }
        });

        Intent intent = getIntent();
        String location = intent.getStringExtra("Makeup");
        mLocationTextView.setText("Here are all the Makeup for you: " + location);

    }

    @Override
    public void onClick(View v) {

    }
}
