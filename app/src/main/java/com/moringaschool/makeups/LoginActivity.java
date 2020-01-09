package com.moringaschool.makeups;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.moringaschool.makeups.presentation.select_product.SelectProductActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.signinButton) Button mSigninButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        mSigninButton.setOnClickListener(this);



        TextView signUp_text = findViewById(R.id.signUp_text);
        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SelectProductActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v == mSigninButton){
            Intent intent = new Intent(LoginActivity.this, SelectProductActivity.class);
            startActivity(intent);
        }

    }
}