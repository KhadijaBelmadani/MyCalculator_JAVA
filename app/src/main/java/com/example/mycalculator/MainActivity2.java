package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    EditText user,psw;
    Button btnLogg;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        user=findViewById(R.id.UserName);
        psw=findViewById(R.id.Password);
        dbHelper=new DBHelper(this);
        btnLogg=findViewById(R.id.btnLoggin);
        btnLogg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isLoggedId=dbHelper.checkUser(user.getText().toString(),psw.getText().toString());
                if(isLoggedId){
                    Intent intent=new Intent(MainActivity2.this, MainActivity3.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity2.this,"Loggin failed",Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}