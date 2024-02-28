package com.majorproject.onlineshopingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class Login_page extends AppCompatActivity {
    public static final String EMAIL="emailId_key";

    TextInputLayout username,pass;
    Button loginbtn;
    DBHelper db;
    TextView registerbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        SharedPreferences sharedPreferences=getSharedPreferences("MySharedpre", Context.MODE_PRIVATE);
        db=new DBHelper(getApplicationContext());

        username=(TextInputLayout) findViewById(R.id.email);
        pass=(TextInputLayout) findViewById(R.id.password);
        loginbtn= (Button) findViewById(R.id.loginBtn);
        registerbtn=(TextView) findViewById(R.id.registerBtnView);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=username.getEditText().getText().toString().trim();
                String password=pass.getEditText().getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    username.setError("Email Required!");
                    return;
                }
                else{
                    username.setError(null);
                }
                if(TextUtils.isEmpty(password)){
                    pass.setError("Password Required!");
                    return;
                }
                else{
                    pass.setError(null);
                }
                if(!db.checkEmail(email)){
                    username.setError("Email Not Exists! SingUp first");
                }else{
                    username.setError(null);
                    if(!db.checkEmailPassword(email,password)) {
                        pass.setError("Invalid Password!");
                    }else{
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putString("emailId_key",email);
                        editor.putBoolean("isLogedIn",true);
                        editor.apply();

//                        intent.putExtra(EMAIL,email);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),Registration_page.class);
                startActivity(intent);
                finish();
            }
        });

    }
}