package com.example.Spontaneity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by seanbroomfield on 10/19/15.
 */
public class SignUpActivity extends Activity {

    private EditText usernameEditText, passwordEditText, passwordAgainEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = (EditText) findViewById(R.id.username_edit_text);
        passwordEditText = (EditText) findViewById(R.id.password_edit_text);
        passwordAgainEditText = (EditText) findViewById(R.id.password_again_edit_text);

        Button actionButton = (Button) findViewById(R.id.action_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
    }

    private void signup() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String passwordAgain = passwordAgainEditText.getText().toString().trim();

        boolean error = false;
        StringBuilder errorMessage = new StringBuilder("");

        if(username.length() == 0) {
            error = true;
            errorMessage.append("Blank username!\n");
        }
        if(password.length() == 0) {
            if(error) {
                errorMessage.append("Blank password!\n");
            } else {
                error = true;
                errorMessage.append("Blank password!\n");
            }
        }
        if(!password.equals(passwordAgain)) {
            if(error) {
                errorMessage.append("Passwords don't match!\n");
            } else {
                error = true;
                errorMessage.append("Passwords don't match!\n");
            }
        }

        if(error) {
            Toast.makeText(SignUpActivity.this, errorMessage.toString(), Toast.LENGTH_LONG).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Signing Up!");
        dialog.show();

        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                dialog.dismiss();
                if(e != null) {
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(SignUpActivity.this, DispatchActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}
