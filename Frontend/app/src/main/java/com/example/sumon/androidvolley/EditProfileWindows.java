package com.example.sumon.androidvolley;

import static com.example.sumon.androidvolley.api.ApiClientFactory.GetPersonalityApi;
import static com.example.sumon.androidvolley.api.ApiClientFactory.GetTrivaApi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sumon.androidvolley.api.SlimCallback;
import com.example.sumon.androidvolley.app.AppController;
import com.example.sumon.androidvolley.model.Hobby;
import com.example.sumon.androidvolley.model.Interest;
import com.example.sumon.androidvolley.model.Trivia;
import com.example.sumon.androidvolley.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @author kaiheng
 */
public class EditProfileWindows extends Activity implements View.OnClickListener {
    private Button getButton, saveButton, backButton;
    private ProgressDialog pDialog;
    private String TAG = EditProfileWindows.class.getSimpleName();
    private TextView msgResponse;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private EditText email;
    private EditText phoneNumber;
    protected EditText name;
    private EditText gender;
    private EditText aboutMe;
    private EditText personality;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_windows);

        getButton = (Button) findViewById(R.id.getDataButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        backButton = (Button) findViewById(R.id.editProfileBackButton);
        //msgResponse = (TextView) findViewById(R.id.ResponseText);
        email = (EditText) findViewById(R.id.editprofilewindows_email);
        phoneNumber = (EditText) findViewById(R.id.editprofilewindows_phoneNumber);
        name = (EditText) findViewById(R.id.editprofilewindows_name);
        gender = (EditText) findViewById(R.id.editprofilewindows_gender);
        aboutMe = (EditText) findViewById(R.id.editprofilewindows_aboutMe);
        personality = (EditText) findViewById(R.id.editprofilewindows_interest);
        username = (TextView) findViewById(R.id.usernameTextView);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        // button click listeners
        getButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

//        email.setText(Integer.toString(Const.USER_ID));

        GetUserInformation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                postObj();
                break;

            case R.id.getDataButton:
//                makeJsonArryReq();
                personality.setText("");
                GetUserInformation();
                break;

            case R.id.editProfileBackButton:
                finish();
                break;

            default:
                break;
        }
    }
    public void postObj(){
        Trivia newTrivia = new Trivia();
        //newTrivia.setId(4);
        newTrivia.setEmail(email.getText().toString());
        newTrivia.setPhoneNumber(phoneNumber.getText().toString());
        newTrivia.setName(name.getText().toString());
        //newTrivia.setAboutMe(aboutMe.getText().toString());
        //newTrivia.setPersonality(personality.getText().toString());
        newTrivia.setGender(gender.getText().toString());
//        newTrivia.setPassword("Demo 3");
//        newTrivia.setUsername("Profile Demo 3");
        //newTrivia.setPersonalityId(3);
        //newTrivia.setEventId(0);
        GetTrivaApi().PostTriviaByPath(Const.USER_ID,newTrivia).enqueue(new SlimCallback<Trivia>(trivia->{
            GetUserInformation();
        }));

//        GetTrivaApi().PostHobbytoUser(123,1).enqueue(new SlimCallback<Trivia>(hobby->{
//        }));
        /*GetPostApi().getFirstPost().enqueue(new SlimCallback<Post>(response -> {
            String result = "email:  "+ response.getEmail()
                    +"\n  phone:  "+ response.getPhoneNumber()
                    +"\n  name:  "+ response.getName()
                    +"\n  aboutMe:  "+ response.getAboutMe()
                    +"\n  personality:  "+ response.getPersonality()
                    +"\n  gender:  "+ response.getGender()
                    +"\n  Body:    "+ response.getBigText();
            System.out.println(result);
        }, "CustomTagForFirstApi"));

         */
    }
    void GetUserInformation(){
        GetTrivaApi().GetAUser(Const.USER_ID).enqueue(new SlimCallback<Trivia>(trivias ->{
            email.setText(trivias.getEmail());
            phoneNumber.setText(trivias.getPhoneNumber());
            name.setText(trivias.getName());
            gender.setText(trivias.getGender());
            username.setText(trivias.getUsername());
        }, "GetAUser"));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        GetPersonalityApi().GetUserHobby(Const.USER_ID).enqueue(new SlimCallback<List<Hobby>>(hobbies ->{
            for(int i = 0; i<hobbies.size(); i++){
                personality.append(hobbies.get(i).getHobbyN() + ",");
            }
        }, "GetUserHobby"));

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        GetPersonalityApi().GetUserInterest(Const.USER_ID).enqueue(new SlimCallback<List<Interest>>(interests ->{
            for(int i = 0; i<interests.size(); i++){
                personality.append(interests.get(i).getInterestN()+",");
            }
        }, "GetUserInterest"));
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())

            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    /**
     * Making json object request
     * */
    private void makeJsonObjReq() {
        showProgressDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                Const.URL_JSON_OBJECT, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        //msgResponse.setText(response.toString());
                        try {
                            getJsonObjData(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        }) {

            /**
             * Passing some request headers
             * */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
//                params.put("name", "Androidhive");
//                params.put("email", "abc@androidhive.info");
//                params.put("pass", "password123");

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq,
                tag_json_obj);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_obj);
    }

    /**
     * Making json array request
     * */
    public void makeJsonArryReq() {
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        //msgResponse.setText(response.toString());
                        try {
                            getJsonArrayData(response);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        hideProgressDialog();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req,
                tag_json_arry);

        // Cancelling request
        // ApplicationController.getInstance().getRequestQueue().cancelAll(tag_json_arry);
    }

    public void getJsonObjData(JSONObject obj) throws JSONException {

        email.setText((String) obj.get("email"));
        phoneNumber.setText((String)obj.get("phone"));
        name.setText((String) obj.get("name"));
        aboutMe.setText((String) obj.get("aboutMe"));
        personality.setText((String) obj.get("personality"));
        gender.setText((String) obj.get("gender"));

    }

    public void getJsonArrayData(JSONArray arr) throws JSONException {
        int id_i;
        String email_i = null, phoneNumber_i = null, name_i = null, aboutMe_i = null, gender_i = null,
                username_i = null, hobby_i = null;
        String personalityArr = null;
        for(int i = 0; i< arr.length(); i++){
            JSONObject obj = arr.getJSONObject(i);
            id_i = obj.getInt("id");
            personalityArr = obj.getString("personality");
            JSONObject personalityObj = new JSONObject(personalityArr);
            JSONArray hobbyArr = personalityObj.getJSONArray("hobbies");
            for(int j = 0; j<hobbyArr.length(); j++){
                JSONObject hobbyObj = hobbyArr.getJSONObject(j);
                hobby_i = hobby_i + hobbyObj.getString("hobbyN") + ",";
            }
            email_i = obj.getString("email");
            phoneNumber_i = obj.getString("phoneNumber");
            name_i = obj.getString("name");
            //aboutMe_i = obj.getString("aboutMe");
            username_i = obj.getString("userName");
            gender_i = obj.getString("gender");
            if(id_i == Const.USER_ID){
                break;
            }
        }
        email.setText((String) email_i);
        phoneNumber.setText(phoneNumber_i);
        name.setText(name_i);
        aboutMe.setText(aboutMe_i);
        username.setText(username_i);
        personality.setText(hobby_i.substring(0,hobby_i.length()-1));
        gender.setText(gender_i);
    }
}