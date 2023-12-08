package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText user,psw,repsw;
Button btnReg,btnLogg;
DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user=findViewById(R.id.UserName);
        psw=findViewById(R.id.Password);
        repsw=findViewById(R.id.RePassword);
        btnReg=findViewById(R.id.btnRegister);
        btnLogg=findViewById(R.id.btnLoggin);
        dbHelper=new DBHelper(this);
        btnLogg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
      btnReg.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              String u,pass,rePass;
              u=user.getText().toString();
              pass=psw.getText().toString();
              rePass=repsw.getText().toString();
              if(u.equals("")|| pass.equals("")){
                  Toast.makeText(MainActivity.this,"please fill all the fields",Toast.LENGTH_LONG).show();
              }else{
                  if(pass.equals(rePass)){
                      if(dbHelper.checkUserName(u)){
                          Toast.makeText(MainActivity.this,"User already exists",Toast.LENGTH_LONG).show();
                            return;
                      }

                      boolean registredSuccess= dbHelper.insertData(u,pass);
                      if(registredSuccess){
                          Toast.makeText(MainActivity.this,"User Registered successfully",Toast.LENGTH_LONG).show();
                      }
                      else{
                          Toast.makeText(MainActivity.this,"User Registration failed",Toast.LENGTH_LONG).show();

                      }
                  }
                  else{
                      Toast.makeText(MainActivity.this,"Password does not match",Toast.LENGTH_LONG).show();
                  }
              }

          }
      });

    }
}