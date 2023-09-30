package com.example.sumon.androidvolley;

import static com.example.sumon.androidvolley.api.ApiClientFactory.GetMessageApi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sumon.androidvolley.api.SlimCallback;
import com.example.sumon.androidvolley.app.AppController;
import com.example.sumon.androidvolley.model.Message;
import com.example.sumon.androidvolley.model.MessageUser;
import com.example.sumon.androidvolley.model.UserPersonality;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author kaiheng
 *
 * This is the group chat page for Twin
 */
public class GroupChatActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {
    private Button backButton;
    private TextView chatHistory, groupName;
    private ProgressDialog pDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = GroupChatActivity.class.getSimpleName();

    private ScrollView chatHistoryScrollView;
    private EditText chatBox;

    private MessageUser onSessionUser;
    private UserPersonality onSessionUserPersonality;

    private MessageUser existingUser;

    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        backButton = (Button) findViewById(R.id.GroupChatBackButton);
        chatBox = (EditText) findViewById(R.id.chatBox);
        chatHistory = (TextView) findViewById(R.id.chatHistory);
        groupName = (TextView) findViewById(R.id.groupNameTextView);
        chatHistoryScrollView = (ScrollView) findViewById(R.id.chatHistory_scrollView);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        backButton.setOnClickListener(this);
        chatBox.setOnKeyListener(this);


        onSessionUser = new MessageUser();
        onSessionUserPersonality = new UserPersonality();
//        makeJsonArryReq();
//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//        }

        RegenerateAllGroupChatOnScreen(chatHistory);

        Draft[] drafts = {
                new Draft_6455()
        };

           String serverUrl = "ws://coms-309-015.class.las.iastate.edu:8080/testwebsocket/" + Const.USER_NAME;

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
                    String s = chatHistory.getText().toString();
                    chatHistory.setText(s + message + "\n");
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

        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask chatList = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable(){
                    public void run(){
                        try{
                            //RegenerateAllGroupChatOnScreen(chatHistory);
                            //chatHistoryScrollView.fullScroll(View.FOCUS_DOWN);
                            //chatBox.requestFocus();
                        }
                        catch(Exception e){

                        }
                    }
                });
            }
        };



        timer.schedule(chatList, 0, 300);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.GroupChatBackButton:
                cc.close();
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_DOWN) &&(keyCode == KeyEvent.KEYCODE_ENTER))
        {
            try {
                cc.send(chatBox.getText().toString());
            }catch(Exception e){
                Log.d("ExceptionSendMessage:", e.getMessage().toString());
            }
            postChat();
            chatHistoryScrollView.fullScroll(View.FOCUS_DOWN);
            chatBox.getText().clear();
            chatBox.requestFocus();
            return true;
        }
        return false;
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
     * This function will be used when opening specific group's groupChat.
     * @param apiText1
     */
//    public void RegenerateAllGroupChatOnScreen(TextView apiText1){
//
//        GetGroupApi().GetAllMessage(Const.GROUP_ID).enqueue(new SlimCallback<List<Message>>(messages ->{
//            apiText1.setText("");
//
//            for (int i = 0; i < messages.size(); i++){
//                apiText1.append(messages.get(i).printable());
//            }
//        }, "GetAllChats"));
//    }

    public void RegenerateAllGroupChatOnScreen(TextView apiText1){

        GetMessageApi().GetAllMessages().enqueue(new SlimCallback<List<Message>>(messages ->{
            apiText1.setText("");
            for (int i = 0; i < messages.size(); i++){
//                if(messages.get(i).getSender() != null){
//                    existingUser = messages.get(i).getSender();
//                    makeJsonArryReq(existingUser.getId(), existingUser, onSessionUserPersonality);
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                    }
//                    apiText1.append(messages.get(i).printableUser());
//                }
//                else{
                    apiText1.append(messages.get(i).printable());
//                }


            }
        }, "GetAllChats"));
    }

    /**
     * this function will be use when posting chat in the specific groupChat
     */
//    public void postChat(){
//        Message newChat = new Message();
//        //EditProfileWindows editProfileWindows = new EditProfileWindows();
//        //editProfileWindows.makeJsonArryReq();
//        //String userName = String.valueOf(editProfileWindows.name.getText());
//        String currentDate = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
//        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
//        //newChat.setGroupName(groupName.getText().toString());
//        newChat.setSender(user);
//        newChat.setTime(currentTime);
//        newChat.setData(chatBox.getText().toString());
//        newChat.setDate(currentDate);
//        GetGroupApi().PostAMessage(Const.USER_ID,newChat).enqueue(new SlimCallback<Message>(message ->{
//            RegenerateAllGroupChatOnScreen(chatHistory);
//        }));
//    }

    public void postChat(){
        Message newChat = new Message();
        //EditProfileWindows editProfileWindows = new EditProfileWindows();
        //editProfileWindows.makeJsonArryReq();
        //String userName = String.valueOf(editProfileWindows.name.getText());
        String currentDate = new SimpleDateFormat("MM-dd-yyyy", Locale.getDefault()).format(new Date());
        String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

       // makeJsonArryReq(Const.USER_ID, onSessionUser, onSessionUserPersonality);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

        //newChat.setGroupName(groupName.getText().toString());

        /**
         * couldn't add user into the chat message due to backend configuration
         */
//        if(onSessionUser != null){
//            newChat.setSender(onSessionUser);
//        }
//        else{
        newChat.setSender(Const.USER_NAME);
//        }
        newChat.setTime(currentTime);
        newChat.setData(chatBox.getText().toString());
        newChat.setDate(currentDate);
        GetMessageApi().PostMessageByBody(newChat).enqueue(new SlimCallback<Message>(message ->{
//            RegenerateAllGroupChatOnScreen(chatHistory);
        }));
    }

    private void makeJsonArryReq(int userId, MessageUser user, UserPersonality userP) {
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        //msgResponse.setText(response.toString());
                        try {
                            getJsonArrayData(response, userId, user, userP);
                            getReponseData(user, userP);
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

    public void getReponseData(MessageUser richUser, UserPersonality userPersonality){
        onSessionUser = richUser;
        onSessionUserPersonality = userPersonality;
    }

    public void getJsonArrayData(JSONArray arr, int id, MessageUser user, UserPersonality userP) throws JSONException {
        int jsonUserId, jsonPersonalityId;
        String email_i = "", phoneNumber_i = "", name_i = "", aboutMe_i = "", gender_i = "",
                username_i = "", hobby_i = "";
        String personalityArr = null;
        for(int i = arr.length()-1; i>=0; i--){
            JSONObject obj = arr.getJSONObject(i);

            jsonUserId = obj.getInt("id");

            if(jsonUserId == id){
                personalityArr = obj.getString("personality");
                JSONObject personalityObj = new JSONObject(personalityArr);
                //userPersonality.setId(personalityObj.getInt("id"));
                JSONArray hobbyArr = personalityObj.getJSONArray("hobbies");
                for(int j = 0; j<hobbyArr.length(); j++){
                    JSONObject hobbyObj = hobbyArr.getJSONObject(j);
                    userP.setHobbies(hobbyObj.getString("hobbyN"));
                }

//                GetPersonalityApi().GetUserHobby(user.getId()).enqueue(new SlimCallback<List<Hobby>>(hobbies ->{
//
//                    for(int j = 0; j<hobbies.size(); j++) {
//                        if(hobbies.get(j).getHobbyN() != null) {
//                            groupName.append(hobbies.get(j).getHobbyN());
//                        }
//                    }
//                }, "GetUserHobby"));

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

//                GetPersonalityApi().GetUserInterest(user.getId()).enqueue(new SlimCallback<List<Interest>>(interests ->{

//                    for(int j = 0; j<interests.size(); j++){
//                        if(interests.get(j).getInterestN() != null) {
//                            userPersonality.setInterests(interests.get(j).getInterestN());
//                        }
//                    }
//
//
//                }, "GetUserInterest"));

                JSONArray interestArr = personalityObj.getJSONArray("interests");
                for(int j = 0; j<interestArr.length(); j++){
                    JSONObject interestObj = interestArr.getJSONObject(j);
                    userP.setInterests(interestObj.getString("interestN"));
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                user.setEmail(obj.getString("email"));
                user.setName(obj.getString("name"));
                user.setUserName(obj.getString("userName"));
                user.setPassword(obj.getString("password"));
                user.setGender(obj.getString("gender"));
                user.setPhoneNumber(obj.getString("phoneNumber"));
//                user.setPersonality(userPersonality);

                break;
            }
        }
    }
}