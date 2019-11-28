package com.example.a210.dbconnectexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegisterForm extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        setTitle("고객등록");
    }

    public void register(View v) {
        EditText et_name = (EditText) findViewById(R.id.name);
        EditText et_phone = (EditText) findViewById(R.id.i_phone);
        EditText et_location = (EditText) findViewById(R.id.i_location);

        String str_name = et_name.getText().toString();

        String str_phone = et_phone.getText().toString();
        String str_location = et_location.getText().toString();

        RadioGroup rg_gender = (RadioGroup) findViewById(R.id.radiogroup_gender);
        RadioButton rb_male = (RadioButton) findViewById(R.id.male);
        RadioButton rb_female = (RadioButton) findViewById(R.id.female);
        String str_gender = "";

        if (rg_gender.getCheckedRadioButtonId() == R.id.male) {
            str_gender = rb_male.getText().toString();
        } else if (rg_gender.getCheckedRadioButtonId() == R.id.female) {
            str_gender = rb_female.getText().toString();
        }

        Intent it = new Intent(this, Register.class);

        it.putExtra("it_name",str_name);
        it.putExtra("it_gener",str_gender);
        it.putExtra("it_phone",str_phone);
        it.putExtra("it_location",str_location);

        startActivity(it);

        finish();
    }
}
