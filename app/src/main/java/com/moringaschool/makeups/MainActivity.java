package com.moringaschool.makeups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.findMakeupButton) Button mFindMakeupButton;
    @BindView(R.id.appNameTextView) TextView mAppNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mFindMakeupButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v == mFindMakeupButton){
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        }

    }
}
