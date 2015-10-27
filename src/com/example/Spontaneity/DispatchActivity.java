package com.example.Spontaneity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import com.parse.ParseUser;

/**
 * Created by seanbroomfield on 10/17/15.
 */
public class DispatchActivity extends Activity {
    public DispatchActivity() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ParseUser.getCurrentUser() != null) {
            //New intent for those logged in
            System.out.println("Parse user exists!");
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            //New intent for logged out
            System.out.println("Parse user doesn't exist!");
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        }
    }
}
