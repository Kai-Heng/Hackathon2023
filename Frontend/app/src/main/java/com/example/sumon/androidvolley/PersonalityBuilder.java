package com.example.sumon.androidvolley;

import static com.example.sumon.androidvolley.api.ApiClientFactory.GetPersonalityApi;
import static com.example.sumon.androidvolley.api.ApiClientFactory.GetTrivaApi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.sumon.androidvolley.api.PersonalityApi;
import com.example.sumon.androidvolley.api.SlimCallback;
import com.example.sumon.androidvolley.app.AppController;
import com.example.sumon.androidvolley.model.Trivia;
import com.example.sumon.androidvolley.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * @author kaiheng
 */
public class PersonalityBuilder extends AppCompatActivity implements View.OnClickListener {
    //Hobby Buttons
    private Button sportsBtn, bikingBtn, gardeningBtn, bakingBtn, archeryBtn, singingBtn, bloggingBtn, bowlingBtn,
            photographyBtn, cookingBtn, readingBtn, travelBtn, artBtn, potteryBtn, hikingBtn, fishingBtn,
            paintingBtn, danceBtn, musicBtn, woodworkingBtn, yogaBtn, calligraphyBtn, chessBtn, knittingBtn, preSubBtn, doneBtn;

    private Button activeBtn, healthyBtn, soloBtn, ruralBtn, urbanBtn, nomadicBtn, bohemianBtn, digitalBtn;
    private Button accountabilityBtn, achievementBtn, adaptabilityBtn, adventureBtn, ambitionBtn, careerBtn, caringBtn,
            communityBtn, friendshipBtn, confidenceBtn, harmonyBtn, graceBtn, honestyBtn, freedomBtn, faithBtn, ethicsBtn,
            patriotismBtn, knowledgeBtn, leadershipBtn, natureBtn, sportsmanshipBtn, respectBtn, teamworkBtn, wealthBtn;
    private TextView selectedView;

    private EditText age, name, sexuality, location, phoneNumber,goal1, goal2;
    private String hobbies = "";
    private String lifestyle = "";
    private String values = "";
    private String hobbiesId = "";
    private String lifestyleId = "";
    private String valuesId = "";
    private String userName = "";
    private String password = "";
    private String email = "";
    private int id = 0;

    private String[] hobbiesArr, lifestyleArr, valuesArr;
    private String[] tempHobbiesId, tempLifestyleId, tempValuesId;
    private int[] hobbiesIdArr, lifestyleIdArr, valuesIdArr;
    private ArrayList<String> hobbiesArrList = new ArrayList<>();
    private ProgressDialog pDialog;
    private String TAG = PersonalityBuilder.class.getSimpleName();
    private String tag_json_obj = "jobj_req", tag_json_arry = "jarray_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personality_builder);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        // Hobby Buttons
        sportsBtn = (Button) findViewById(R.id.sportsHobbiesButton);
        bikingBtn = (Button) findViewById(R.id.bikingHobbiesButton);
        gardeningBtn = (Button) findViewById(R.id.gardeningHobbiesButton);
        bakingBtn = (Button) findViewById(R.id.bakingHobbiesButton);
        archeryBtn = (Button) findViewById(R.id.archeryHobbiesButton);
        singingBtn = (Button) findViewById(R.id.singingHobbiesButton);
        bloggingBtn = (Button) findViewById(R.id.bloggingHobbiesButton);
        bowlingBtn = (Button) findViewById(R.id.bowlingHobbiesButton);
        photographyBtn = (Button) findViewById(R.id.photographyHobbiesButton);
        cookingBtn = (Button) findViewById(R.id.cookingHobbiesButton);
        readingBtn = (Button) findViewById(R.id.readingHobbiesButton);
        travelBtn = (Button) findViewById(R.id.travelHobbiesButton);
        artBtn = (Button) findViewById(R.id.artHobbiesButton);
        potteryBtn = (Button) findViewById(R.id.potteryHobbiesButton);
        hikingBtn = (Button) findViewById(R.id.hikingHobbiesButton);
        fishingBtn = (Button) findViewById(R.id.fishingHobbiesButton);
        paintingBtn = (Button) findViewById(R.id.paintingHobbiesButton);
        danceBtn = (Button) findViewById(R.id.danceHobbiesButton);
        musicBtn = (Button) findViewById(R.id.musicHobbiesButton);
        woodworkingBtn = (Button) findViewById(R.id.woodworkingHobbiesButton);
        yogaBtn = (Button) findViewById(R.id.yogaHobbiesButton);
        calligraphyBtn = (Button) findViewById(R.id.calligraphyHobbiesButton);
        chessBtn = (Button) findViewById(R.id.chessHobbiesButton);
        knittingBtn = (Button) findViewById(R.id.knittingHobbiesButton);

        //Lifestyle Buttons
        activeBtn = (Button) findViewById(R.id.activeLifestyleButton);
        healthyBtn = (Button) findViewById(R.id.healthyLifestyleButton);
        soloBtn = (Button) findViewById(R.id.soloLifestyleButton);
        ruralBtn = (Button) findViewById(R.id.ruralLifestyleButton);
        urbanBtn = (Button) findViewById(R.id.urbanLifestyleButton);
        nomadicBtn = (Button) findViewById(R.id.nomadicLifestyleButton);
        bohemianBtn = (Button) findViewById(R.id.bohemianLifestyleButton);
        digitalBtn = (Button) findViewById(R.id.digitalLifestyleButton);

        //Values Buttons
        accountabilityBtn = (Button) findViewById(R.id.accountabilityBtn);
        achievementBtn = (Button) findViewById(R.id.achievementBtn);
        adaptabilityBtn = (Button) findViewById(R.id.adaptabilityBtn);
        adventureBtn = (Button) findViewById(R.id.adventureBtn);
        ambitionBtn = (Button) findViewById(R.id.ambitionBtn);
        careerBtn = (Button) findViewById(R.id.careerBtn);
        caringBtn = (Button) findViewById(R.id.caringBtn);
        communityBtn = (Button) findViewById(R.id.communityBtn);
        friendshipBtn = (Button) findViewById(R.id.friendshipBtn);
        confidenceBtn = (Button) findViewById(R.id.confidenceBtn);
        harmonyBtn = (Button) findViewById(R.id.harmonyBtn);
        graceBtn = (Button) findViewById(R.id.graceBtn);
        honestyBtn = (Button) findViewById(R.id.honestyBtn);
        freedomBtn = (Button) findViewById(R.id.freedomBtn);
        faithBtn = (Button) findViewById(R.id.faithBtn);
        ethicsBtn = (Button) findViewById(R.id.ethicsBtn);
        patriotismBtn = (Button) findViewById(R.id.patriotismBtn);
        knowledgeBtn = (Button) findViewById(R.id.knowledgeBtn);
        leadershipBtn = (Button) findViewById(R.id.leadershipBtn);
        natureBtn = (Button) findViewById(R.id.natureBtn);
        sportsmanshipBtn = (Button) findViewById(R.id.sportsmanshipBtn);
        respectBtn = (Button) findViewById(R.id.respectBtn);
        teamworkBtn = (Button) findViewById(R.id.teamworkBtn);
        wealthBtn = (Button) findViewById(R.id.wealthBtn);


        doneBtn = (Button) findViewById(R.id.personalityDoneButton);
        preSubBtn = (Button) findViewById(R.id.personalityCheckButton);

        selectedView = (TextView) findViewById(R.id.viewSelected);

        age = (EditText) findViewById(R.id.ageEditText);
        name = (EditText) findViewById(R.id.nameEditText);
        sexuality = (EditText) findViewById(R.id.sexualityEditText);
        location = (EditText) findViewById(R.id.locationEditText);
        phoneNumber = (EditText) findViewById(R.id.mobileEditText);
        goal1 = (EditText) findViewById(R.id.goalEditText1);
        goal2 = (EditText) findViewById(R.id.goalEditText2);


        //Hobbies
        sportsBtn.setOnClickListener(this);
        bikingBtn.setOnClickListener(this);
        gardeningBtn.setOnClickListener(this);
        bakingBtn.setOnClickListener(this);
        archeryBtn.setOnClickListener(this);
        singingBtn.setOnClickListener(this);
        bloggingBtn.setOnClickListener(this);
        bowlingBtn.setOnClickListener(this);
        photographyBtn.setOnClickListener(this);
        cookingBtn.setOnClickListener(this);
        readingBtn.setOnClickListener(this);
        travelBtn.setOnClickListener(this);
        artBtn.setOnClickListener(this);
        potteryBtn.setOnClickListener(this);
        hikingBtn.setOnClickListener(this);
        fishingBtn.setOnClickListener(this);
        paintingBtn.setOnClickListener(this);
        danceBtn.setOnClickListener(this);
        musicBtn.setOnClickListener(this);
        woodworkingBtn.setOnClickListener(this);
        yogaBtn.setOnClickListener(this);
        calligraphyBtn.setOnClickListener(this);
        chessBtn.setOnClickListener(this);
        knittingBtn.setOnClickListener(this);

        //Lifestyle
        activeBtn.setOnClickListener(this);
        healthyBtn.setOnClickListener(this);
        soloBtn.setOnClickListener(this);
        ruralBtn.setOnClickListener(this);
        urbanBtn.setOnClickListener(this);
        nomadicBtn.setOnClickListener(this);
        bohemianBtn.setOnClickListener(this);
        digitalBtn.setOnClickListener(this);

        //Values
        accountabilityBtn.setOnClickListener(this);
        achievementBtn.setOnClickListener(this);
        adaptabilityBtn.setOnClickListener(this);
        adventureBtn.setOnClickListener(this);
        ambitionBtn.setOnClickListener(this);
        careerBtn.setOnClickListener(this);
        caringBtn.setOnClickListener(this);
        communityBtn.setOnClickListener(this);
        friendshipBtn.setOnClickListener(this);
        confidenceBtn.setOnClickListener(this);
        harmonyBtn.setOnClickListener(this);
        graceBtn.setOnClickListener(this);
        honestyBtn.setOnClickListener(this);
        freedomBtn.setOnClickListener(this);
        faithBtn.setOnClickListener(this);
        ethicsBtn.setOnClickListener(this);
        patriotismBtn.setOnClickListener(this);
        knowledgeBtn.setOnClickListener(this);
        leadershipBtn.setOnClickListener(this);
        natureBtn.setOnClickListener(this);
        sportsmanshipBtn.setOnClickListener(this);
        respectBtn.setOnClickListener(this);
        teamworkBtn.setOnClickListener(this);
        wealthBtn.setOnClickListener(this);


        doneBtn.setOnClickListener(this);
        preSubBtn.setOnClickListener(this);

//        final Handler handler = new Handler();
//        Timer timer = new Timer();
//        TimerTask updateList = new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(new Runnable(){
//                    public void run(){
//                        try{
//
//                        }
//                        catch(Exception e){
//
//                        }
//                    }
//                });
//            }
//        };
//
//        timer.schedule(updateList, 0, 150);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.personalityCheckButton:
                checkSelected();
                convertStringtoArr(hobbies, lifestyle, values);
                convertIdtoArrId(hobbiesId, lifestyleId, valuesId);
//                for(int i = 0; i< hobbiesIdArr.length; i++){
//                    selectedView.append(Integer.toString(hobbiesIdArr[i]));
//                }
                postPersonality();

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }

                makeJsonArryReq(selectedView);
                break;

            case R.id.personalityDoneButton:

                makeJsonArryReq(selectedView);



                Const.USER_ID = Integer.parseInt(selectedView.getText().toString());

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                postInterest();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }

                //updateAvailableSelection(hobbiesArr, lifestyleArr, valuesArr);

                finish();
                break;

            case R.id.sportsHobbiesButton:
                if(sportsBtn.isSelected() == false){
                    sportsBtn.setSelected(true);
                    sportsBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(sportsBtn.isSelected() == true){
                    sportsBtn.setSelected(false);
                    sportsBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }

                break;

            case R.id.bikingHobbiesButton:
                if(bikingBtn.isSelected() == false){
                    bikingBtn.setSelected(true);
                    bikingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(bikingBtn.isSelected() == true) {
                    bikingBtn.setSelected(false);
                    bikingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.gardeningHobbiesButton:
                if(gardeningBtn.isSelected() == false){
                    gardeningBtn.setSelected(true);
                    gardeningBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(gardeningBtn.isSelected() == true) {
                    gardeningBtn.setSelected(false);
                    gardeningBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.bakingHobbiesButton:
                if(bakingBtn.isSelected() == false){
                    bakingBtn.setSelected(true);
                    bakingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(bakingBtn.isSelected() == true) {
                    bakingBtn.setSelected(false);
                    bakingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.archeryHobbiesButton:
                if(archeryBtn.isSelected() == false){
                    archeryBtn.setSelected(true);
                    archeryBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(archeryBtn.isSelected() == true) {
                    archeryBtn.setSelected(false);
                    archeryBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.singingHobbiesButton:
                if(singingBtn.isSelected() == false){
                    singingBtn.setSelected(true);
                    singingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(singingBtn.isSelected() == true) {
                    singingBtn.setSelected(false);
                    singingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.bloggingHobbiesButton:
                if(bloggingBtn.isSelected() == false){
                    bloggingBtn.setSelected(true);
                    bloggingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(bloggingBtn.isSelected() == true) {
                    bloggingBtn.setSelected(false);
                    bloggingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.bowlingHobbiesButton:
                if(bowlingBtn.isSelected() == false){
                    bowlingBtn.setSelected(true);
                    bowlingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(bowlingBtn.isSelected() == true) {
                    bowlingBtn.setSelected(false);
                    bowlingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.photographyHobbiesButton:
                if(photographyBtn.isSelected() == false){
                    photographyBtn.setSelected(true);
                    photographyBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(photographyBtn.isSelected() == true) {
                    photographyBtn.setSelected(false);
                    photographyBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.cookingHobbiesButton:
                if(cookingBtn.isSelected() == false){
                    cookingBtn.setSelected(true);
                    cookingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(cookingBtn.isSelected() == true) {
                    cookingBtn.setSelected(false);
                    cookingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.readingHobbiesButton:
                if(readingBtn.isSelected() == false){
                    readingBtn.setSelected(true);
                    readingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(readingBtn.isSelected() == true) {
                    readingBtn.setSelected(false);
                    readingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.travelHobbiesButton:
                if(travelBtn.isSelected() == false){
                    travelBtn.setSelected(true);
                    travelBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(travelBtn.isSelected() == true) {
                    travelBtn.setSelected(false);
                    travelBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.artHobbiesButton:
                if(artBtn.isSelected() == false){
                    artBtn.setSelected(true);
                    artBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(artBtn.isSelected() == true) {
                    artBtn.setSelected(false);
                    artBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.potteryHobbiesButton:
                if(potteryBtn.isSelected() == false){
                    potteryBtn.setSelected(true);
                    potteryBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(potteryBtn.isSelected() == true) {
                    potteryBtn.setSelected(false);
                    potteryBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.hikingHobbiesButton:
                if(hikingBtn.isSelected() == false){
                    hikingBtn.setSelected(true);
                    hikingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(hikingBtn.isSelected() == true) {
                    hikingBtn.setSelected(false);
                    hikingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.fishingHobbiesButton:
                if(fishingBtn.isSelected() == false){
                    fishingBtn.setSelected(true);
                    fishingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(fishingBtn.isSelected() == true) {
                    fishingBtn.setSelected(false);
                    fishingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.paintingHobbiesButton:
                if(paintingBtn.isSelected() == false){
                    paintingBtn.setSelected(true);
                    paintingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(paintingBtn.isSelected() == true) {
                    paintingBtn.setSelected(false);
                    paintingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.danceHobbiesButton:
                if(danceBtn.isSelected() == false){
                    danceBtn.setSelected(true);
                    danceBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(danceBtn.isSelected() == true) {
                    danceBtn.setSelected(false);
                    danceBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.musicHobbiesButton:
                if(musicBtn.isSelected() == false){
                    musicBtn.setSelected(true);
                    musicBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(musicBtn.isSelected() == true) {
                    musicBtn.setSelected(false);
                    musicBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.woodworkingHobbiesButton:
                if(woodworkingBtn.isSelected() == false){
                    woodworkingBtn.setSelected(true);
                    woodworkingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(woodworkingBtn.isSelected() == true) {
                    woodworkingBtn.setSelected(false);
                    woodworkingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.yogaHobbiesButton:
                if(yogaBtn.isSelected() == false){
                    yogaBtn.setSelected(true);
                    yogaBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(yogaBtn.isSelected() == true) {
                    yogaBtn.setSelected(false);
                    yogaBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.calligraphyHobbiesButton:
                if(calligraphyBtn.isSelected() == false){
                    calligraphyBtn.setSelected(true);
                    calligraphyBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(calligraphyBtn.isSelected() == true) {
                    calligraphyBtn.setSelected(false);
                    calligraphyBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.chessHobbiesButton:
                if(chessBtn.isSelected() == false){
                    chessBtn.setSelected(true);
                    chessBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(chessBtn.isSelected() == true) {
                    chessBtn.setSelected(false);
                    chessBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.knittingHobbiesButton:
                if(knittingBtn.isSelected() == false){
                    knittingBtn.setSelected(true);
                    knittingBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(knittingBtn.isSelected() == true) {
                    knittingBtn.setSelected(false);
                    knittingBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.activeLifestyleButton:
                if(activeBtn.isSelected() == false){
                    activeBtn.setSelected(true);
                    activeBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(activeBtn.isSelected() == true) {
                    activeBtn.setSelected(false);
                    activeBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.healthyLifestyleButton:
                if(healthyBtn.isSelected() == false){
                    healthyBtn.setSelected(true);
                    healthyBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(healthyBtn.isSelected() == true) {
                    healthyBtn.setSelected(false);
                    healthyBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.soloLifestyleButton:
                if(soloBtn.isSelected() == false){
                    soloBtn.setSelected(true);
                    soloBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(soloBtn.isSelected() == true) {
                    soloBtn.setSelected(false);
                    soloBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.ruralLifestyleButton:
                if(ruralBtn.isSelected() == false){
                    ruralBtn.setSelected(true);
                    ruralBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(ruralBtn.isSelected() == true) {
                    ruralBtn.setSelected(false);
                    ruralBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.urbanLifestyleButton:
                if(urbanBtn.isSelected() == false){
                    urbanBtn.setSelected(true);
                    urbanBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(urbanBtn.isSelected() == true) {
                    urbanBtn.setSelected(false);
                    urbanBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.nomadicLifestyleButton:
                if(nomadicBtn.isSelected() == false){
                    nomadicBtn.setSelected(true);
                    nomadicBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(nomadicBtn.isSelected() == true) {
                    nomadicBtn.setSelected(false);
                    nomadicBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.bohemianLifestyleButton:
                if(bohemianBtn.isSelected() == false){
                    bohemianBtn.setSelected(true);
                    bohemianBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(bohemianBtn.isSelected() == true) {
                    bohemianBtn.setSelected(false);
                    bohemianBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.digitalLifestyleButton:
                if(digitalBtn.isSelected() == false){
                    digitalBtn.setSelected(true);
                    digitalBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(digitalBtn.isSelected() == true) {
                    digitalBtn.setSelected(false);
                    digitalBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;


            case R.id.accountabilityBtn:
                if(accountabilityBtn.isSelected() == false){
                    accountabilityBtn.setSelected(true);
                    accountabilityBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(accountabilityBtn.isSelected() == true) {
                    accountabilityBtn.setSelected(false);
                    accountabilityBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.achievementBtn:
                if(achievementBtn.isSelected() == false){
                    achievementBtn.setSelected(true);
                    achievementBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(achievementBtn.isSelected() == true) {
                    achievementBtn.setSelected(false);
                    achievementBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.adaptabilityBtn:
                if(adaptabilityBtn.isSelected() == false){
                    adaptabilityBtn.setSelected(true);
                    adaptabilityBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(adaptabilityBtn.isSelected() == true) {
                    adaptabilityBtn.setSelected(false);
                    adaptabilityBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.adventureBtn:
                if(adventureBtn.isSelected() == false){
                    adventureBtn.setSelected(true);
                    adventureBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(adventureBtn.isSelected() == true) {
                    adventureBtn.setSelected(false);
                    adventureBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.ambitionBtn:
                if(ambitionBtn.isSelected() == false){
                    ambitionBtn.setSelected(true);
                    ambitionBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(ambitionBtn.isSelected() == true) {
                    ambitionBtn.setSelected(false);
                    ambitionBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.careerBtn:
                if(careerBtn.isSelected() == false){
                    careerBtn.setSelected(true);
                    careerBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(careerBtn.isSelected() == true) {
                    careerBtn.setSelected(false);
                    careerBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.caringBtn:
                if(caringBtn.isSelected() == false){
                    caringBtn.setSelected(true);
                    caringBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(caringBtn.isSelected() == true) {
                    caringBtn.setSelected(false);
                    caringBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.communityBtn:
                if(communityBtn.isSelected() == false){
                    communityBtn.setSelected(true);
                    communityBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(communityBtn.isSelected() == true) {
                    communityBtn.setSelected(false);
                    communityBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.friendshipBtn:
                if(friendshipBtn.isSelected() == false){
                    friendshipBtn.setSelected(true);
                    friendshipBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(friendshipBtn.isSelected() == true) {
                    friendshipBtn.setSelected(false);
                    friendshipBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.confidenceBtn:
                if(confidenceBtn.isSelected() == false){
                    confidenceBtn.setSelected(true);
                    confidenceBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(confidenceBtn.isSelected() == true) {
                    confidenceBtn.setSelected(false);
                    confidenceBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.harmonyBtn:
                if(harmonyBtn.isSelected() == false){
                    harmonyBtn.setSelected(true);
                    harmonyBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(harmonyBtn.isSelected() == true) {
                    harmonyBtn.setSelected(false);
                    harmonyBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.graceBtn:
                if(graceBtn.isSelected() == false){
                    graceBtn.setSelected(true);
                    graceBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(graceBtn.isSelected() == true) {
                    graceBtn.setSelected(false);
                    graceBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.honestyBtn:
                if(honestyBtn.isSelected() == false){
                    honestyBtn.setSelected(true);
                    honestyBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(honestyBtn.isSelected() == true) {
                    honestyBtn.setSelected(false);
                    honestyBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.freedomBtn:
                if(freedomBtn.isSelected() == false){
                    freedomBtn.setSelected(true);
                    freedomBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(freedomBtn.isSelected() == true) {
                    freedomBtn.setSelected(false);
                    freedomBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.faithBtn:
                if(faithBtn.isSelected() == false){
                    faithBtn.setSelected(true);
                    faithBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(faithBtn.isSelected() == true) {
                    faithBtn.setSelected(false);
                    faithBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.ethicsBtn:
                if(ethicsBtn.isSelected() == false){
                    ethicsBtn.setSelected(true);
                    ethicsBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(ethicsBtn.isSelected() == true) {
                    ethicsBtn.setSelected(false);
                    ethicsBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.patriotismBtn:
                if(patriotismBtn.isSelected() == false){
                    patriotismBtn.setSelected(true);
                    patriotismBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(patriotismBtn.isSelected() == true) {
                    patriotismBtn.setSelected(false);
                    patriotismBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.knowledgeBtn:
                if(knowledgeBtn.isSelected() == false){
                    knowledgeBtn.setSelected(true);
                    knowledgeBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(knowledgeBtn.isSelected() == true) {
                    knowledgeBtn.setSelected(false);
                    knowledgeBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.leadershipBtn:
                if(leadershipBtn.isSelected() == false){
                    leadershipBtn.setSelected(true);
                    leadershipBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(leadershipBtn.isSelected() == true) {
                    leadershipBtn.setSelected(false);
                    leadershipBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.natureBtn:
                if(natureBtn.isSelected() == false){
                    natureBtn.setSelected(true);
                    natureBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(natureBtn.isSelected() == true) {
                    natureBtn.setSelected(false);
                    natureBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.sportsmanshipBtn:
                if(sportsmanshipBtn.isSelected() == false){
                    sportsmanshipBtn.setSelected(true);
                    sportsmanshipBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(sportsmanshipBtn.isSelected() == true) {
                    sportsmanshipBtn.setSelected(false);
                    sportsmanshipBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.respectBtn:
                if(respectBtn.isSelected() == false){
                    respectBtn.setSelected(true);
                    respectBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(respectBtn.isSelected() == true) {
                    respectBtn.setSelected(false);
                    respectBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.teamworkBtn:
                if(teamworkBtn.isSelected() == false){
                    teamworkBtn.setSelected(true);
                    teamworkBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(teamworkBtn.isSelected() == true) {
                    teamworkBtn.setSelected(false);
                    teamworkBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            case R.id.wealthBtn:
                if(wealthBtn.isSelected() == false){
                    wealthBtn.setSelected(true);
                    wealthBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                }
                else if(wealthBtn.isSelected() == true) {
                    wealthBtn.setSelected(false);
                    wealthBtn.setBackgroundColor(getResources().getColor(R.color.red_grey));
                }
                break;

            default:
                break;

        }
    }

    public void checkSelected(){
        if(bikingBtn.isSelected() == true){
            hobbies += bikingBtn.getText() + ",";
            hobbiesId += "198,";
        }

        if(sportsBtn.isSelected() == true){
            hobbies += sportsBtn.getText() + ",";
            hobbiesId += "199,";
        }


        if(gardeningBtn.isSelected() == true){
            hobbies += gardeningBtn.getText() + ",";
            hobbiesId += "200,";
        }

        if(bakingBtn.isSelected() == true){
            hobbies += bakingBtn.getText() + ",";
            hobbiesId += "201,";
        }

        if(archeryBtn.isSelected() == true){
            hobbies += archeryBtn.getText() + ",";
            hobbiesId += "202,";
        }

        if(singingBtn.isSelected() == true){
            hobbies += singingBtn.getText() + ",";
            hobbiesId += "203,";
        }

        if(bloggingBtn.isSelected() == true){
            hobbies += bloggingBtn.getText() + ",";
            hobbiesId += "204,";
        }

        if(bowlingBtn.isSelected() == true){
            hobbies += bowlingBtn.getText() + ",";
            hobbiesId += "205,";
        }

        if(photographyBtn.isSelected() == true){
            hobbies += photographyBtn.getText() + ",";
            hobbiesId += "206,";
        }

        if(cookingBtn.isSelected() == true){
            hobbies += cookingBtn.getText() + ",";
            hobbiesId += "207,";
        }

        if(readingBtn.isSelected() == true){
            hobbies += readingBtn.getText() + ",";
            hobbiesId += "208,";
        }

        if(travelBtn.isSelected() == true){
            hobbies += travelBtn.getText() + ",";
            hobbiesId += "209,";
        }

        if(artBtn.isSelected() == true){
            hobbies += artBtn.getText() + ",";
            hobbiesId += "210,";
        }

        if(potteryBtn.isSelected() == true){
            hobbies += potteryBtn.getText() + ",";
            hobbiesId += "211,";
        }

        if(hikingBtn.isSelected() == true){
            hobbies += hikingBtn.getText() + ",";
            hobbiesId += "212,";
        }

        if(fishingBtn.isSelected() == true){
            hobbies += fishingBtn.getText() + ",";
            hobbiesId += "213,";
        }

        if(paintingBtn.isSelected() == true){
            hobbies += paintingBtn.getText() + ",";
            hobbiesId += "214,";
        }

        if(danceBtn.isSelected() == true){
            hobbies += danceBtn.getText() + ",";
            hobbiesId += "215,";
        }

        if(musicBtn.isSelected() == true){
            hobbies += musicBtn.getText() + ",";
            hobbiesId += "216,";
        }

        if(woodworkingBtn.isSelected() == true){
            hobbies += woodworkingBtn.getText() + ",";
            hobbiesId += "217,";
        }

        if(yogaBtn.isSelected() == true){
            hobbies += yogaBtn.getText() + ",";
            hobbiesId += "218,";
        }
        if(calligraphyBtn.isSelected() == true){
            hobbies += calligraphyBtn.getText() + ",";
            hobbiesId += "219,";
        }

        if(chessBtn.isSelected() == true){
            hobbies += chessBtn.getText() + ",";
            hobbiesId += "220,";
        }
        if(knittingBtn.isSelected() == true){
            hobbies += knittingBtn.getText() + ",";
            hobbiesId += "221,";
        }

        //Lifestyle
        if(activeBtn.isSelected() == true){
            lifestyle += activeBtn.getText() +  ",";
            lifestyleId += "222,";
        }

        if(healthyBtn.isSelected() == true){
            lifestyle += healthyBtn.getText() +  ",";
            lifestyleId += "223,";
        }

        if(soloBtn.isSelected() == true){
            lifestyle += soloBtn.getText() +  ",";
            lifestyleId += "224,";
        }

        if(ruralBtn.isSelected() == true){
            lifestyle += ruralBtn.getText() +  ",";
            lifestyleId += "225,";
        }

        if(urbanBtn.isSelected() == true){
            lifestyle += urbanBtn.getText() +  ",";
            lifestyleId += "226,";
        }

        if(nomadicBtn.isSelected() == true){
            lifestyle += nomadicBtn.getText() + ",";
            lifestyleId += "227,";
        }

        if(bohemianBtn.isSelected() == true){
            lifestyle += bohemianBtn.getText() + ",";
            lifestyleId += "228,";
        }

        if(digitalBtn.isSelected() == true){
            lifestyle += digitalBtn.getText() + ",";
            lifestyleId += "229,";
        }


        // Values
        if(accountabilityBtn.isSelected() == true){
            values += accountabilityBtn.getText() + ",";
            valuesId += "230,";
        }

        if(achievementBtn.isSelected() == true){
            values += achievementBtn.getText() + ",";
            valuesId += "231,";
        }

        if(adaptabilityBtn.isSelected() == true){
            values += adaptabilityBtn.getText() + ",";
            valuesId += "232,";
        }

        if(adventureBtn.isSelected() == true){
            values += adventureBtn.getText() + ",";
            valuesId += "233,";
        }

        if(ambitionBtn.isSelected() == true){
            values += ambitionBtn.getText() + ",";
            valuesId += "234,";
        }

        if(careerBtn.isSelected() == true){
            values += careerBtn.getText() + ",";
            valuesId += "235,";
        }

        if(caringBtn.isSelected() == true){
            values += caringBtn.getText() + ",";
            valuesId += "236,";
        }

        if(communityBtn.isSelected() == true){
            values += communityBtn.getText() + ",";
            valuesId += "237,";
        }

        if(friendshipBtn.isSelected() == true){
            values += friendshipBtn.getText() + ",";
            valuesId += "238,";
        }

        if(confidenceBtn.isSelected() == true){
            values += confidenceBtn.getText() + ",";
            valuesId += "239,";
        }

        if(harmonyBtn.isSelected() == true){
            values += harmonyBtn.getText() + ",";
            valuesId += "240,";
        }

        if(graceBtn.isSelected() == true){
            values += graceBtn.getText() + ",";
            valuesId += "241,";
        }

        if(honestyBtn.isSelected() == true){
            values += honestyBtn.getText() + ",";
            valuesId += "242,";
        }

        if(freedomBtn.isSelected() == true){
            values += freedomBtn.getText() + ",";
            valuesId += "243,";
        }

        if(faithBtn.isSelected() == true){
            values += faithBtn.getText() + ",";
            valuesId += "244,";
        }

        if(ethicsBtn.isSelected() == true){
            values += ethicsBtn.getText() + ",";
            valuesId += "245,";
        }

        if(patriotismBtn.isSelected() == true){
            values += patriotismBtn.getText() + ",";
            valuesId += "246,";
        }

        if(knowledgeBtn.isSelected() == true){
            values += knowledgeBtn.getText() + ",";
            valuesId += "247,";
        }

        if(leadershipBtn.isSelected() == true){
            values += leadershipBtn.getText() + ",";
            valuesId += "248,";
        }

        if(natureBtn.isSelected() == true){
            values += natureBtn.getText() + ",";
            valuesId += "249,";
        }

        if(sportsmanshipBtn.isSelected() == true){
            values += sportsmanshipBtn.getText() + ",";
            valuesId += "250,";
        }

        if(respectBtn.isSelected() == true){
            values += respectBtn.getText() + ",";
            valuesId += "251,";
        }

        if(teamworkBtn.isSelected() == true){
            values += teamworkBtn.getText() + ",";
            valuesId += "252,";
        }

        if(wealthBtn.isSelected() == true){
            values += wealthBtn.getText() + ",";
            valuesId += "253,";
        }
    }

    public void convertStringtoArr(String hobbies, String lifestyle, String values){
        if(hobbies != null)
            hobbiesArr = hobbies.split(",");

        if(lifestyle != null)
            lifestyleArr = lifestyle.split(",");

        if(values !=null)
            valuesArr = values.split(",");

//        for(int i = 0; i< hobbiesArr.length; i++){
//                selectedView.append(hobbiesArr[i]);
//        }
//
//        for(int i =0; i< lifestyleArr.length; i++){
//            selectedView.append(lifestyleArr[i]);
//        }
//
//        for(int i =0; i< valuesArr.length; i++){
//            selectedView.append(valuesArr[i]);
//        }
    }

    public void convertIdtoArrId(String hobbiesId, String lifestyleId, String valuesId){
        if(hobbiesId != null) {
            tempHobbiesId = hobbiesId.split(",");
            hobbiesIdArr = new int[tempHobbiesId.length];
        }

        if(lifestyleId != null) {
            tempLifestyleId = lifestyleId.split(",");
            lifestyleIdArr = new int[tempLifestyleId.length];
        }
        if(valuesId !=null) {
            tempValuesId = valuesId.split(",");
            valuesIdArr = new int[tempValuesId.length];
        }

        for(int i = 0; i< tempHobbiesId.length; i++){
            if(tempHobbiesId[i] != "")
                hobbiesIdArr[i] = Integer.parseInt(tempHobbiesId[i]);
            //selectedView.append(tempHobbiesId[i]);
        }

        for(int i = 0; i< tempLifestyleId.length; i++){
            //selectedView.append(tempLifestyleId[i]);
            if(tempLifestyleId[i] != "")
                lifestyleIdArr[i] = Integer.parseInt(tempLifestyleId[i]);
        }

        for(int i = 0; i< tempValuesId.length; i++){
            //selectedView.append(tempValuesId[i]);
            if(tempValuesId[i] != "")
                valuesIdArr[i] = Integer.parseInt(tempValuesId[i]);
        }

    }

    public void updateAvailableSelection(String[] hobbiesArr, String[] lifestyleArr, String[] valuesArr){
        for(int i = 0; i<hobbiesArr.length; i++){
            GetPersonalityApi().AddHobby(hobbiesArr[i]).enqueue(new SlimCallback<PersonalityApi>(personalityHobby->{
            }));
        }

        for(int i = 0; i<lifestyleArr.length; i++){
            GetPersonalityApi().AddInterest(lifestyleArr[i]).enqueue(new SlimCallback<PersonalityApi>(personalityInterest->{
            }));
        }

        for(int i = 0; i<valuesArr.length; i++){
            GetPersonalityApi().AddValue(valuesArr[i]).enqueue(new SlimCallback<PersonalityApi>(personalityValue->{
            }));
        }
    }

    public void postPersonality() {
        Trivia userPersonality = new Trivia();
        userPersonality.setGender(sexuality.getText().toString());
        userPersonality.setName(name.getText().toString());
        userPersonality.setPhoneNumber(phoneNumber.getText().toString());
        userPersonality.setEmail(Const.EMAIL);
        userPersonality.setUsername(Const.USER_NAME);
        userPersonality.setPassword(Const.PASSWORD);
//        userPersonality.setEmail("postman@gmail.com");
//        userPersonality.setUsername("postman");
//        userPersonality.setPassword("asdf1234");

        GetTrivaApi().PostTriviaByBody(userPersonality).enqueue(new SlimCallback<Trivia>(demographics->{
        }));
    }

    public void postInterest(){
        for(int i = 0; i<hobbiesIdArr.length; i++){
            GetPersonalityApi().PostHobbyToUser(Const.USER_ID,hobbiesIdArr[i]).enqueue(new SlimCallback<PersonalityApi>(hobby->{
            }));
        }


        for(int i = 0; i< lifestyleIdArr.length; i++) {
            GetPersonalityApi().PostInterestToUser(Const.USER_ID, lifestyleIdArr[i]).enqueue(new SlimCallback<PersonalityApi>(interest -> {
            }));
        }

//        for(int i = 0; i<valuesIdArr.length; i++) {
//            GetPersonalityApi().PostValueToUser(Const.USER_ID, valuesIdArr[i]).enqueue(new SlimCallback<PersonalityApi>(value -> {
//            }));
//        }
//
//        GetPersonalityApi().PostValueToUser(379, 249).enqueue(new SlimCallback<PersonalityApi>(value -> {
//        }));
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
     * Making json array request
     * */
    public void makeJsonArryReq(TextView thisView) {
        showProgressDialog();
        JsonArrayRequest req = new JsonArrayRequest(Const.URL_JSON_ARRAY,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        //msgResponse.setText(response.toString());
                        try {
                            getJsonArrayData(response, thisView);
                            getUserId(thisView);
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

//    public void getJsonArrayData(JSONArray arr) throws JSONException {
//        int id_i = 0;
//        String email_i = null, phoneNumber_i = null, name_i = null, aboutMe_i = null, gender_i = null,
//                username_i = null, hobby_i = null, password_i = null;
//        String personalityArr = null;
//        for(int i = 0; i< arr.length(); i++){
//            JSONObject obj = arr.getJSONObject(i);
//            id_i = obj.getInt("id");
//            personalityArr = obj.getString("personality");
//            JSONObject personalityObj = new JSONObject(personalityArr);
//            JSONArray hobbyArr = personalityObj.getJSONArray("hobbies");
//            for(int j = 0; j<hobbyArr.length(); j++){
//                JSONObject hobbyObj = hobbyArr.getJSONObject(j);
//                hobby_i = hobby_i + hobbyObj.getString("hobbyN") + ",";
//            }
//            email_i = obj.getString("email");
//            phoneNumber_i = obj.getString("phoneNumber");
//            name_i = obj.getString("name");
//            username_i = obj.getString("userName");
//            password_i = obj.getString("password");
//            gender_i = obj.getString("gender");
//            if(id_i == Const.USER_ID){
//                id = id_i;
//                break;
//            }
//        }
//        userName = username_i;
//        password = password_i;
//        email = email_i;
//
//    }
    public void getJsonArrayData(JSONArray arr, TextView v) throws JSONException {
        int id_i;
        String email_i = "", phoneNumber_i = "", name_i = "", aboutMe_i = "", gender_i = "",
                username_i = "", hobby_i = "";
        String personalityArr = null;
        for(int i = arr.length()-1; i>=0; i--){
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

    //            testView.append(username_i + " | " + usernameEditText.getText().toString()
    //                    + " | "+Integer.toString(username_i.compareTo(username)) + "\n");

            gender_i = obj.getString("gender");
            if(username_i.compareTo(Const.USER_NAME) == 0){
    //                testView.append(username_i + " | " + usernameEditText.getText().toString()
    //                        + " | "+Integer.toString(username_i.compareTo(username_i)) + "\n");

                v.setText(Integer.toString(id_i));
            }
        }
    }

    public void getUserId(TextView view){
        selectedView = view;
    }







}