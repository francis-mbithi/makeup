package com.moringaschool.makeups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.findMakeupButton) Button mFindMakeupButton;
//    @BindView(R.id.appNameTextView) TextView mAppNameTextView;

    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;

    private String[] makeup = new String[] {"Face Primer", "BB Cream ", "Foundation", "Blush", "Glow Kit Highlighter", "Bronzer", "Setting Spray", "Setting Powder"};
    private String[] cosmetics= new String[] {"Sephora", "Ulta", " Clinique", "Scandinavian", " Anastasia Beverly Hills", " Milani", "Amazon", " Lord & Taylor"  };



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
                String makeup = ((TextView)view).getText().toString();
                Toast.makeText(MainActivity.this, "Makeup", Toast.LENGTH_LONG).show();

            }
        });

        Intent intent = getIntent();
        String makeup = intent.getStringExtra("makeup");
        mLocationTextView.setText("The Available Makeup for You " + makeup);

    }

}
