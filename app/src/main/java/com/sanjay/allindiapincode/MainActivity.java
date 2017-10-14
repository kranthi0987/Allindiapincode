package com.sanjay.allindiapincode;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    Button btn;
    EditText editText;
    String theText;
    Switch simpleSwitch;
    String statusSwitch;
    private ProgressDialog pDialog;
    private TextView txtResponse;

    // temporary string to show the parsed response
    private String jsonResponse;
    private String urlJsonpin = "http://postalpincode.in/api/pincode/110001";
    private String urlJsonpla = "http://postalpincode.in/api/postoffice/New Delhi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText editText=(EditText) findViewById(R.id.editext);
        final Switch simpleSwitch = (Switch) findViewById(R.id.switch1);
        Button btn = (Button) findViewById(R.id.button);
        Log.d("sasasa", "sadsadsa: ");

//        Boolean switchState = simpleSwitch.isChecked();
        simpleSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    Toast.makeText(getApplicationContext(), "on", Toast.LENGTH_SHORT).show();
                    simpleSwitch.setText("pincode");

                } else {
                    Toast.makeText(getApplicationContext(), "off", Toast.LENGTH_SHORT).show();
                    simpleSwitch.setText("place");
                    // The toggle is disabled
                }
            }
        });

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //create the toast object, set display duration,
                Toast.makeText(getApplicationContext(), statusSwitch, Toast.LENGTH_SHORT).show();
                makeJsonObjectRequest();
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

    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonpin, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d("mm", response.toString());

                try {
//                    "Name": "Bhavani Nagar",
//                            "Description": "",
//                            "BranchType": "Sub Post Office",
//                            "DeliveryStatus": "Non-Delivery",
//                            "Taluk": "Tirupati (Urban)",
//                            "Circle": "Tirupati (Urban)",
//                            "District": "Chittoor",
//                            "Division": "Tirupati",
//                            "Region": "Kurnool",
//                            "State": "Andhra Pradesh",
//                            "Country": "India"
                    // Parsing json object response
                    // response will be a json object
                    String name = response.getString("name");
                    String Description = response.getString("Description");
                    String BranchType = response.getString("BranchType");
                    String DeliveryStatus = response.getString("DeliveryStatus");
                    String Circle = response.getString("Circle");
                    String District = response.getString("District");
                    String Division = response.getString("Division");
                    String Region = response.getString("Region");
                    String State = response.getString("State");
                    String Country = response.getString("Country");


                    jsonResponse = "";
                    jsonResponse += "Name: " + name + "\n\n";

                    txtResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });


    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
