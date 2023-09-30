package com.example.sumon.androidvolley;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.sumon.androidvolley.utils.Const;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class MessageActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    private Button backButton;
    private TextView chatHistory, groupName;
    private ProgressDialog pDialog;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private String TAG = GroupChatActivity.class.getSimpleName();

    private ScrollView chatHistoryScrollView;
    private EditText chatBox;

    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        backButton = (Button) findViewById(R.id.messageBackButton);
        chatBox = (EditText) findViewById(R.id.messageBox);
        chatHistory = (TextView) findViewById(R.id.messageHistory);
        groupName = (TextView) findViewById(R.id.messageNameTextView);
        chatHistoryScrollView = (ScrollView) findViewById(R.id.messageHistory_scrollView);

        backButton.setOnClickListener(this);
        chatBox.setOnKeyListener(this);

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

//        pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.setCancelable(false);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.messageBackButton:
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
//            chatHistory.append(chatBox.getText() + "\n");
//            postChat();
            try {
                cc.send(chatBox.getText().toString());
            }catch(Exception e){
                Log.d("ExceptionSendMessage:", e.getMessage().toString());
            }
            chatHistoryScrollView.fullScroll(View.FOCUS_DOWN);
            chatBox.getText().clear();
            chatBox.requestFocus();
            return true;
        }
        return false;
    }
}