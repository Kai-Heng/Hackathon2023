package com.example.sumon.androidvolley;

import static com.example.sumon.androidvolley.api.ApiClientFactory.GetEventApi;
import static com.example.sumon.androidvolley.api.ApiClientFactory.GetMatchApi;
import static com.example.sumon.androidvolley.api.ApiClientFactory.GetTrivaApi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
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
import com.example.sumon.androidvolley.model.Event;
import com.example.sumon.androidvolley.model.Match;
import com.example.sumon.androidvolley.model.Trivia;
import com.example.sumon.androidvolley.utils.Const;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
/**
 * @author kaiheng
 */
public class MainActivity extends Activity implements OnClickListener {
    private Button getButton, saveButton, eventTabButton, profileTabBtn, groupButton, messageButton, groupChatButton;
    private TextView matchView, eventView;

    private ScrollView eventScrollView;
    private ProgressDialog pDialog;
    private String TAG = MainActivity.class.getSimpleName();
    private TextView msgResponse;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private EditText email;
    private EditText phoneNumber;
    private EditText name;
    private EditText gender;
    private EditText aboutMe;
    private EditText personality;
    private TextView username;
    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //startActivity(new Intent(MainActivity.this, PersonalityBuilder.class));
        startActivity(new Intent(MainActivity.this, LoginPageActivity.class));

        profileTabBtn = (Button) findViewById(R.id.EditProfileButton);
        groupButton = (Button) findViewById(R.id.groupButton);
        eventTabButton = (Button) findViewById(R.id.eventTabButton);
        messageButton = (Button) findViewById(R.id.messageButton);
        groupChatButton = (Button) findViewById(R.id.openChatButton);

        matchView = (TextView) findViewById(R.id.newMatchView);
        eventView = (TextView) findViewById(R.id.eventListView);
        eventScrollView = (ScrollView) findViewById(R.id.eventScrollView);
        /*
        getButton = (Button) findViewById(R.id.getDataButton);
        saveButton = (Button) findViewById(R.id.saveButton);
        //msgResponse = (TextView) findViewById(R.id.ResponseText);
        email = (EditText) findViewById(R.id.editprofilewindows_email);
        phoneNumber = (EditText) findViewById(R.id.editprofilewindows_phoneNumber);
        name = (EditText) findViewById(R.id.editprofilewindows_name);
        gender = (EditText) findViewById(R.id.editprofilewindows_gender);
        aboutMe = (EditText) findViewById(R.id.editprofilewindows_aboutMe);
        personality = (EditText) findViewById(R.id.editprofilewindows_interest);
        username = (TextView) findViewById(R.id.usernameTextView);
        */

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);


        profileTabBtn.setOnClickListener(this);
        groupButton.setOnClickListener(this);
        eventTabButton.setOnClickListener(this);
        messageButton.setOnClickListener(this);
        groupChatButton.setOnClickListener(this);

        RegenerateAllEventsOnScreen(eventView);

        matchView.setMovementMethod(new ScrollingMovementMethod());

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask updateMatch = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable(){
                    public void run(){
                        try{
                            matchView.setText("");
                            //RegenerateAllEventsOnScreen(eventView);
                            makeJsonArrReq();

//                            eventScrollView.fullScroll(View.FOCUS_DOWN); ""
                        }
                        catch(Exception e){

                        }
                    }
                });
            }
        };

        timer.schedule(updateMatch, 0, 5000);

        Draft[] drafts = {
                new Draft_6455()
        };

        String serverUrl = "ws://coms-309-015.class.las.iastate.edu:8080/eventWebSocket/HOME";
        //String serverUrl = "ws://10.0.2.2:8080/websocket/Test";
        try{
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(serverUrl), (Draft) drafts[0]) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");

                }

                @Override
                public void onMessage(String message) {
                    Log.d("", "run() returned: " + message);
                    String s = eventView.getText().toString();
                    eventView.setText(message + s);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);

                }

                @Override
                public void onError(Exception e) {
                    Log.d("Exception: ", e.toString());

                }
            };
        }catch (URISyntaxException e){
            Log.d("Exception:", e.toString());
        }
        cc.connect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.EditProfileButton:
                startActivity(new Intent(MainActivity.this, EditProfileWindows.class));
                break;

            case R.id.groupButton:
                startActivity(new Intent(MainActivity.this, GroupActivity.class));
                break;

            case R.id.eventTabButton:
                startActivity(new Intent(MainActivity.this, EventActivity.class));
                break;

            case R.id.messageButton:
                startActivity(new Intent(MainActivity.this, MessageActivity.class));
                break;

            case R.id.openChatButton:
                startActivity(new Intent(MainActivity.this, GroupChatActivity.class));
                break;

            default:
                break;
        }
    }

    void RegenerateAllTriviasOnScreen( TextView apiText1){

        GetTrivaApi().GetAllTrivia().enqueue(new SlimCallback<List<Trivia>>(trivias ->{
            apiText1.setText("");

            for (int i = trivias.size()-1; i>= 0; i--){
                apiText1.append(trivias.get(i).printable());
            }
        }, "GetAllTrivia"));

    }

    void RegenerateAllEventsOnScreen( TextView apiText1){

        GetEventApi().GetAllEvent().enqueue(new SlimCallback<List<Event>>(events ->{
            apiText1.setText("");

            for (int i = events.size()-1; i>= 0; i--){
                apiText1.append(events.get(i).printable());
            }
        }, "GetAllEvents"));
    }

    void RegenerateNewMatchOnScreen(TextView newMatch){

        GetMatchApi().GetAllMatches().enqueue(new SlimCallback<List<Match>>(matches ->{
            newMatch.setText("");
                newMatch.append(matches.get(matches.size()-1).printable());
        }, "GetNewMatches"));
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
    private void makeJsonArrReq() {
        showProgressDialog();
        String newMatchURL = Const.URL_JSON_ARRAY + Const.USER_ID + "/friends";

        JsonArrayRequest req = new JsonArrayRequest(newMatchURL,
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
        String email_i = null, phoneNumber_i = null, name_i = null, aboutMe_i = null, gender_i = null,
                username_i = null, hobby_i = "";
        String personalityArr = null;
        for(int i = 0; i< arr.length(); i++){
            JSONObject obj = arr.getJSONObject(i);
            personalityArr = obj.getString("personality");
            JSONObject personalityObj = new JSONObject(personalityArr);
            JSONArray hobbyArr = personalityObj.getJSONArray("hobbies");
            for(int j = 0; j<hobbyArr.length(); j++){
                JSONObject hobbyObj = hobbyArr.getJSONObject(j);
                hobby_i += hobbyObj.getString("hobbyN") + ", ";
            }
            email_i = obj.getString("email");
            phoneNumber_i = obj.getString("phoneNumber");
            name_i = obj.getString("name");
            //aboutMe_i = obj.getString("aboutMe");
            username_i = obj.getString("userName");
            gender_i = obj.getString("gender");
        }
        matchView.append("Name: " + name_i);
        matchView.append("\nUsername: " + username_i);
        matchView.append("\nEmail: " + email_i);
        matchView.append("\nPhone Number: " + phoneNumber_i);
        matchView.append("\nHobby: " + hobby_i);
        matchView.append("\nGender: " + gender_i);
    }

}