package com.majorproject.onlineshopingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Registration_page extends AppCompatActivity {
    TextInputLayout useremail,username,pass,cpass;
    Button registerbtn;
    TextView loginbtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);

        db=new DBHelper(this);

        username=(TextInputLayout) findViewById(R.id.userName);
        useremail=(TextInputLayout) findViewById(R.id.email);
        pass=(TextInputLayout) findViewById(R.id.password);
        cpass=(TextInputLayout) findViewById(R.id.cpassword);
        loginbtn= (TextView) findViewById(R.id.loginbtnView);
        registerbtn=(Button) findViewById(R.id.registerBtn);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=username.getEditText().getText().toString().trim();
                String email=useremail.getEditText().getText().toString().trim();
                String password=pass.getEditText().getText().toString().trim();
                String cpassword=cpass.getEditText().getText().toString().trim();

                if(TextUtils.isEmpty(name)){
                    username.setError("Email Required!");
                    return;
                }
                else{
                    username.setError(null);
                }
                if(TextUtils.isEmpty(email)){
                    useremail.setError("Email Required!");
                    return;
                }
                else{
                    useremail.setError(null);
                }
                if(TextUtils.isEmpty(password)){
                    pass.setError("Password Required!");
                    return;
                }
                else{
                    pass.setError(null);
                }
                if(TextUtils.isEmpty(cpassword)){
                    cpass.setError("Confirm Password Required!");
                    return;
                }
                else{
                    cpass.setError(null);
                }
                if(!password.equals(cpassword)){
                    cpass.setError("Password Not match");
                    return;
                }
                else{
                    cpass.setError(null);
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    useremail.setError("Invalid Email!");
                    return;
                }
                else{
                    useremail.setError(null);
                }

                Boolean checkuser= db.checkEmail(email);
                if(checkuser==true){
                    useremail.setError("Email already Exists!");
                }else{
                    useremail.setError(null);
                    Boolean insert=db.insertData(name,email,password);
                    if(insert==true){
                        Toast.makeText(Registration_page.this,"Registed Succfully...",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),Login_page.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),Login_page.class);
                startActivity(intent);
                finish();
            }
        });
    }
}