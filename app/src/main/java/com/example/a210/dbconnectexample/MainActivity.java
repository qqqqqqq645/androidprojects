package com.example.a210.dbconnectexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button menu1;
    Button menu2;
    Button menu3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu1 = (Button)findViewById(R.id.menu1);
        menu1 = (Button)findViewById(R.id.menu2);

        menu1.setOnClickListener(this);
        menu1.setOnClickListener(this);

        setTitle("고객관리");

    }

    public void onClick(View v){
        switch(v.getId()){

            case R.id.menu1:
                Intent it = new Intent(this, RegisterForm.class);
                startActivity(it);
                return;

            case R.id.menu2:
                Intent it2 = new Intent(this, RegisterForm.class);
                startActivity(it);
                return;

            default:
                return;
        }
    }
}
