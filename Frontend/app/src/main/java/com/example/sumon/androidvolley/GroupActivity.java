package com.example.sumon.androidvolley;

import static com.example.sumon.androidvolley.api.ApiClientFactory.GetGroupApi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.sumon.androidvolley.api.SlimCallback;
import com.example.sumon.androidvolley.model.Group;
import com.example.sumon.androidvolley.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class GroupActivity extends AppCompatActivity implements View.OnClickListener {
    private Button finishBtn;
    private LinearLayout groupLinearLay;
    private ProgressDialog pDialog;
    private String TAG = GroupActivity.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";
    private ArrayList<Integer> groupIdList = new ArrayList<>();
    private int groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        finishBtn = (Button) findViewById(R.id.groupFinishBtn);
        groupLinearLay = (LinearLayout) findViewById(R.id.groupLinearLayout);

        finishBtn.setOnClickListener(this);

        getGroups();

//        for(int i = 0; i< 20; i++) {
//            Button groupButton = new Button(this);
//            groupButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            groupButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
//            groupButton.setText("Hello");
//            groupButton.setTextColor(Color.parseColor("#FFFFFFFF"));
//            groupButton.setBackgroundColor(Color.parseColor("#FF6200EE"));
//            groupButton.setOnClickListener(this);
//            groupButton.setId(1000+i);
//
//            groupLinearLay.addView(groupButton);
//            groupButton.setOnClickListener(assignGroupChat(groupButton));
//        }

    }

    View.OnClickListener assignGroupChat(final Button button){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i<groupIdList.size(); i++){
                    int checkPressedButton = button.getId();
                    checkPressedButton -= 1000;
                    if(button.isPressed() && (checkPressedButton == i)){
                        Const.GROUP_ID = groupIdList.get(i);
                        Toast toast = Toast.makeText(getApplicationContext(),Integer.toString(Const.GROUP_ID),Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
                startActivity(new Intent(GroupActivity.this, GroupChatActivity.class));
            }
        };
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.groupFinishBtn:
                finish();
                break;

            default:
                break;
        }
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())

            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

    void getGroups(){
        GetGroupApi().GetUserGroup(Const.USER_ID).enqueue(new SlimCallback <List<Group>>(groups ->{
            for(int i = 0; i< groups.size(); i++) {
                Button groupButton = new Button(this);
                groupButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                groupButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
                groupButton.setText(groups.get(i).getGroupName());
                groupButton.setTextColor(Color.parseColor("#FFFFFFFF"));
                groupButton.setBackgroundColor(Color.parseColor("#FF6200EE"));
                groupButton.setOnClickListener(this);
                groupButton.setId(1000+i);

                groupLinearLay.addView(groupButton);
                groupIdList.add(groups.get(i).getId());

                groupButton.setOnClickListener(assignGroupChat(groupButton));
            }

        }, "GetAllGroups"));

    }
}