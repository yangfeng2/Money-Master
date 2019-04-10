package com.example.moneymaster;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LockScreenActivity extends AppCompatActivity {

    EditText password;
    Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_screen);

        password = findViewById(R.id.confirmPassword);
        confirmButton = findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    matchHashPassword();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //retrieve the password via sharedpreferences
    public String getPassword()
    {
        SharedPreferences settings = getSharedPreferences("password",0);
        String password = settings.getString("hashPassword","error");
        return password;
    }

    //match the current password with the correct password
    public void matchHashPassword() throws NoSuchAlgorithmException {
        String correctPassword = getPassword();
        //hash the typed password so can match with the correct password
        String typedPassword = hashString(password.getText().toString());

        if(correctPassword.equals(typedPassword))
        {
            startActivity(new Intent(LockScreenActivity.this,MainActivity.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Incorrect Password ! Try Again.",Toast.LENGTH_LONG).show();
        }
    }

    //hash a string via SHA-256
    public String hashString(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance( "SHA-256" );
        String password = s;

        messageDigest.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = messageDigest.digest();

        String hex = String.format("%064x",new BigInteger(1,digest));
        return hex;
    }
}

