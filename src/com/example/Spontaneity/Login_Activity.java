package com.example.Spontaneity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.*;

public class Login_Activity extends Activity {

    private EditText usernameField;
    private EditText passwordField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_Holo_Light_NoActionBar);
        setContentView(R.layout.activity_login);

        usernameField = (EditText) findViewById(R.id.username);
        passwordField = (EditText) findViewById(R.id.password);

        Button loginButton = (Button) findViewById(R.id.lgn_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login () {
        String username = usernameField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        //validate login info
        if(username.length() == 0) {
            Toast.makeText(this, "Please enter a username.", Toast.LENGTH_LONG).show();
            return;
        }
        if(password.length() == 0) {
            Toast.makeText(this, "Please enter a password.", Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Logging In!");
        dialog.show();

        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser parseUser, ParseException e) {
                dialog.dismiss();
                if(e != null) {
                    //Error
                    Toast.makeText(Login_Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Login_Activity.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }


}
