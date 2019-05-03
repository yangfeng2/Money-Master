package com.example.moneymaster;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LockActivity extends AppCompatActivity {

    //------------------------- variables ----------------------------
    EditText editPassword;
    EditText confirmPassword;
    Button cancelButton;
    Button addPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        editPassword = findViewById(R.id.editPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        cancelButton = findViewById(R.id.clcButton);
        addPassword = findViewById(R.id.addPassword);

        //go back to the setting screen
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LockActivity.this, SettingActivity.class));
            }
        });

        //try to add password and catch the exception
        addPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    savePassword();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });

        //check the current theme
        checkTheme();

    }

    //check the theme is dark or light
    public void checkTheme()
    {
        View view = findViewById(R.id.lockActivity);
        TextView password1 = findViewById(R.id.passwordView);
        TextView password2 = findViewById(R.id.password2View);
        EditText passText = findViewById(R.id.editPassword);
        EditText confirmPassText = findViewById(R.id.confirmPassword);

        String currentTheme = currentTheme();
        //dark theme found
        if (currentTheme.equals("darkTheme")){
            view.setBackgroundColor(Color.parseColor("#000000"));
            password1.setTextColor(Color.parseColor("#ffffff"));
            password2.setTextColor(Color.parseColor("#ffffff"));
            passText.setTextColor(Color.parseColor("#ffffff"));
            confirmPassText.setTextColor(Color.parseColor("#ffffff"));
        }
        //light theme found
        else {
            view.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    //retrieve the data from typeTheme file
    public String currentTheme()
    {
        SharedPreferences settings = getSharedPreferences("themes",0);
        String currentTheme = settings.getString("typeTheme","error");
        return  currentTheme;
    }

    //make sure the user have enter same password
    public boolean matchPassword()
    {
        if(editPassword.getText().toString().equals(confirmPassword.getText().toString())){
            return true;
        }
        else {
            Toast.makeText(getApplicationContext(),"Password not matched ! Try Again !",Toast.LENGTH_LONG).show();
            return  false;
        }
    }

    //save the password via sharedpreferences
    public void savePassword() throws NoSuchAlgorithmException {
        if(matchPassword())
        {
            String password = hashString(confirmPassword.getText().toString());

            SharedPreferences settings = getSharedPreferences("password", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("hashPassword",password);

            editor.apply();

            Toast.makeText(getApplicationContext(),"App Has Been Locked !",Toast.LENGTH_LONG).show();
            startActivity(new Intent(LockActivity.this,HomeActivity.class));
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

