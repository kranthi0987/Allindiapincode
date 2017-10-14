package com.sanjay.allindiapincode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText editText;
    String theText;
    Switch simpleSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=(EditText) findViewById(R.id.editext);
        Switch simpleSwitch = (Switch) findViewById(R.id.switch1);
        Button btn = (Button) findViewById(R.id.button)

//        Boolean switchState = simpleSwitch.isChecked();
        simpleSwitch.setTextOn("On");

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //create the toast object, set display duration,
                Toast.makeText(getApplicationContext(), theText, Toast.LENGTH_SHORT).show();
            }
        });

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // we assign "theText" a value here
                 theText = s.toString();
                // we test it here (assuming your main Activity is called "MainActivity"
//                Toast.makeText(MainActivity.this, theText, Toast.LENGTH_LONG).show();
                // you can do whatever with the value here directly (like call "do_search"),
                // or launch a background Thread to do it from here
            }
        });
    }
}
