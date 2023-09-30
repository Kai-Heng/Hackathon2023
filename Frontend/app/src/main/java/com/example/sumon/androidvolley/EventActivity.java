package com.example.sumon.androidvolley;

import static com.example.sumon.androidvolley.api.ApiClientFactory.GetEventApi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sumon.androidvolley.api.SlimCallback;
import com.example.sumon.androidvolley.model.Event;
import com.example.sumon.androidvolley.utils.Const;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
/**
 * @author kaiheng
 */
public class EventActivity extends AppCompatActivity implements View.OnClickListener {
    private Button getEventBtn, saveEventBtn, backButton;
    private EditText eventName, eventTime, eventLocation;
    private TextView viewEvent;
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private ProgressDialog pDialog;
    private String TAG = MainActivity.class.getSimpleName();
    private WebSocketClient cc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        getEventBtn = (Button) findViewById(R.id.getEventBtn);
        saveEventBtn = (Button) findViewById(R.id.createEventBtn);
        backButton = (Button) findViewById(R.id.eventBackButton);
        eventName = (EditText) findViewById(R.id.eventNameTextField);
        eventTime = (EditText) findViewById(R.id.eventTimeTextField);
        eventLocation = (EditText) findViewById(R.id.eventLocationTextField);
        viewEvent = (TextView) findViewById(R.id.eventList);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        getEventBtn.setOnClickListener(this);
        saveEventBtn.setOnClickListener(this);
        backButton.setOnClickListener(this);
        viewEvent.setMovementMethod(new ScrollingMovementMethod());

        RegenerateAllEventsOnScreen(viewEvent);

        Draft[] drafts = {
                new Draft_6455()
        };

        String serverUrl = "ws://coms-309-015.class.las.iastate.edu:8080/eventWebSocket/" + Const.USER_ID;
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
                    String s = viewEvent.getText().toString();
                    viewEvent.setText(message + s);
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
        switch (v.getId()){
            case R.id.createEventBtn:
                postEvent();
                try {
                    String message = "Event:  " + eventName.getText().toString() + "\nTime:  " + eventTime.getText().toString()
                            + "\nLocation:  " + eventLocation.getText().toString() + "\n\n";
                    cc.send(message);
                }catch(Exception e){
                    Log.d("ExceptionSendMessage:", e.getMessage().toString());
                }
                break;
            case R.id.getEventBtn:
                //RegenerateAllEventsOnScreen(viewEvent);
                break;

            case R.id.eventBackButton:
                cc.close();
                finish();
                break;

            default:
                break;
        }
    }

    public void postEvent(){
        Event newEvent = new Event();
        //newTrivia.setId(4);
        newEvent.setEventName(eventName.getText().toString());
        newEvent.setTime(eventTime.getText().toString());
        newEvent.setLocation(eventLocation.getText().toString());
        GetEventApi().PostEventByBody(newEvent).enqueue(new SlimCallback<Event>(event->{
            //RegenerateAllEventsOnScreen(viewEvent);
        }));
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
    void RegenerateAllEventsOnScreen( TextView apiText1){

        GetEventApi().GetAllEvent().enqueue(new SlimCallback<List<Event>>(events ->{
            apiText1.setText("");

            for (int i = events.size()-1; i>=0; i--){
                apiText1.append(events.get(i).printable());
            }
        }, "GetAllEvents"));

    }
}