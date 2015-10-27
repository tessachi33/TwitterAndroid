/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {

    EditText usernameEditText, passwordEditText;

    public void loginOrSignup(View view){
      ParseUser.logInInBackground(String.valueOf(usernameEditText.getText()), String.valueOf(passwordEditText.getText()), new LogInCallback() {
        public void done(ParseUser user, ParseException e) {
          if (user != null) {
            Log.i("AppInfo", "Logged In");
          } else {
            ParseUser newUser = new ParseUser();
            newUser.setUsername(String.valueOf(usernameEditText.getText()));
            newUser.setPassword(String.valueOf(passwordEditText.getText()));

            newUser.signUpInBackground(new SignUpCallback() {
              public void done(ParseException e) {
                if (e == null) {
                  Log.i("AppInfo", "Signed Up");
                } else {

                  Toast.makeText(getApplication(), "Could not login or signup- please, try again.", Toast.LENGTH_LONG);

                }
              }
            });
          }
        }
      });


    }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ParseAnalytics.trackAppOpenedInBackground(getIntent());
      usernameEditText = (EditText) findViewById(R.id.username);
      passwordEditText = (EditText) findViewById(R.id.password);


  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
