package com.productions.rk.tickitock;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TwoPlayerSameDevice extends Activity implements View.OnClickListener {

    ImageView img1,img2,img3,position0,position1,position2,position3,position4,position5,position6,position7,position8;
    TextView player1score,player2score;
    String winner="Game Drawn";
    AnimationDrawable anim,win1,win2,win3;
    Button player1moveindicator,player2moveindicator,ReplayButton,exitButton;
    int activeplayer;
    int filled;
    int playerOnewin=0,playerTwowin=0,draws;
    int state[] = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    boolean gameActive;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_player_same_device);
        player1moveindicator = (Button) findViewById(R.id.player1moveindicator);
        player2moveindicator = (Button) findViewById(R.id.player2moveindicator);
        player1score = (TextView) findViewById(R.id.player1score);
        player2score = (TextView) findViewById(R.id.player2score);
        position0 = (ImageView) findViewById(R.id.position0);
        position0.setOnClickListener(this);
        position1 = (ImageView) findViewById(R.id.position1);
        position1.setOnClickListener(this);
        position2 = (ImageView) findViewById(R.id.position2);
        position2.setOnClickListener(this);
        position3 = (ImageView) findViewById(R.id.position3);
        position3.setOnClickListener(this);
        position4 = (ImageView) findViewById(R.id.position4);
        position4.setOnClickListener(this);
        position5 = (ImageView) findViewById(R.id.position5);
        position5.setOnClickListener(this);
        position6 = (ImageView) findViewById(R.id.position6);
        position6.setOnClickListener(this);
        position7 = (ImageView) findViewById(R.id.position7);
        position7.setOnClickListener(this);
        position8 = (ImageView) findViewById(R.id.position8);
        position8.setOnClickListener(this);
        beginNewGame();
    }
    private void beginNewGame() {
        gameActive=true;
        for(int i=0;i<9;i++){
            state[i]=0;
        }
        activeplayer=1;
        filled=0;
        position0.setBackgroundResource(android.R.color.white);
        position1.setBackgroundResource(android.R.color.white);
        position2.setBackgroundResource(android.R.color.white);
        position3.setBackgroundResource(android.R.color.white);
        position4.setBackgroundResource(android.R.color.white);
        position5.setBackgroundResource(android.R.color.white);
        position6.setBackgroundResource(android.R.color.white);
        position7.setBackgroundResource(android.R.color.white);
        position8.setBackgroundResource(android.R.color.white);
        player1moveindicator.setBackgroundColor(Color.argb(255, 54, 145, 32));
        player2moveindicator.setBackgroundColor(Color.argb(255, 222, 237, 222));

    }



    public void onClick(View view) {
        ImageView counter = (ImageView) view;
        int check=Integer.parseInt(counter.getTag().toString());

        if(state[check]==0&& gameActive) {
            if (activeplayer == 1) {
                counter.setBackgroundResource(R.drawable.circle_anim);
                anim = (AnimationDrawable) counter.getBackground();
                anim.start();
                activeplayer = 2;
                state[check] = 1;
                filled++;
                player2moveindicator.setBackgroundColor(Color.argb(255,54,145,32));
                player1moveindicator.setBackgroundColor(Color.argb(255,222,237,222));

            } else {
                counter.setBackgroundResource(R.drawable.cross_anim);
                anim = (AnimationDrawable) counter.getBackground();
                anim.start();
                activeplayer = 1;
                state[check] = 2;
                filled++;
                player1moveindicator.setBackgroundColor(Color.argb(255,54,145,32));
                player2moveindicator.setBackgroundColor(Color.argb(255,222,237,222));
            }
        }
        if(state[0]==state[1] && state[1]==state[2] &&state[0]!=0){
            win(0,1,2);
        }
        else  if(state[3]==state[4] && state[4]==state[5]&&state[3]!=0){
            win(3,4,5);
        }
        else  if(state[6]==state[7] && state[7]==state[8]&&state[6]!=0){
            win(6,7,8);
        }
        else  if(state[0]==state[3] && state[3]==state[6]&&state[0]!=0){
            win(0,3,6);

        }
        else  if(state[1]==state[4] && state[4]==state[7]&&state[1]!=0){
            win(1,4,7);
        }
        else  if(state[2]==state[5] && state[5]==state[8]&&state[2]!=0){
            win(2,5,8);
        }
        else  if(state[0]==state[4] && state[4]==state[8]&&state[0]!=0){
            win(0,4,8);
        }
        else  if(state[2]==state[4] && state[4]==state[6]&&state[2]!=0){
            win(2,4,6);
        }

        if(gameActive&&filled==9){
            gameActive=false;
            draws++;
        }
        if(!gameActive){

            LayoutInflater inflater= getLayoutInflater();
            View ResultAlertBox = inflater.inflate(R.layout.layout_game_result_2p_same_device, null);

            final Dialog alertDialog = new Dialog(this);
            alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialog.setContentView(ResultAlertBox);
            alertDialog.setCancelable(false);
            alertDialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.alertbackground));
            alertDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

            ReplayButton=(Button)ResultAlertBox.findViewById(R.id.ReplayButton);
            exitButton=(Button)ResultAlertBox.findViewById(R.id.ExitButton);

            ReplayButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    beginNewGame();
                    alertDialog.cancel();

                }
            });
            exitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveStats(playerOnewin,playerTwowin,draws);
                    startActivity(new Intent(TwoPlayerSameDevice.this, OptionScreen.class));
                    TwoPlayerSameDevice.this.finish();
                }
            });

            //0.5s delay before showing dialog
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    alertDialog.show();
                }
            }, 500);

        }

    }

    private void win(int a,int b,int c){
        String ida="position"+a;
        String idb="position"+b;
        String idc="position"+c;
        int id1=getResources().getIdentifier(ida, "id", getPackageName());
        int id2=getResources().getIdentifier(idb, "id", getPackageName());
        int id3=getResources().getIdentifier(idc, "id", getPackageName());
        if(state[a]==1){
            img1=(ImageView) findViewById(id1);
            img2=(ImageView) findViewById(id2);
            img3=(ImageView) findViewById(id3);
            img1.setBackgroundResource(R.drawable.circlewin_anim);
            img2.setBackgroundResource(R.drawable.circlewin_anim);
            img3.setBackgroundResource(R.drawable.circlewin_anim);
            win1=(AnimationDrawable)img1.getBackground();
            win2=(AnimationDrawable)img2.getBackground();
            win3=(AnimationDrawable)img3.getBackground();
            win1.start();
            win2.start();
            win3.start();
            gameActive=false;
            winner="Player 1 wins!";
            playerOnewin++;
            player1score.setText(String.valueOf(playerOnewin));
        }
        else{
            img1=(ImageView) findViewById(id1);
            img2=(ImageView) findViewById(id2);
            img3=(ImageView) findViewById(id3);
            img1.setBackgroundResource(R.drawable.crosswin_anim);
            img2.setBackgroundResource(R.drawable.crosswin_anim);
            img3.setBackgroundResource(R.drawable.crosswin_anim);
            win1=(AnimationDrawable)img1.getBackground();
            win2=(AnimationDrawable)img2.getBackground();
            win3=(AnimationDrawable)img3.getBackground();
            win1.start();
            win2.start();
            win3.start();
            gameActive=false;
            winner="Player 2 wins!";
            playerTwowin++;
            player2score.setText(String.valueOf(playerTwowin));
        }

    }
    private void saveStats(int wins,int loses,int draws){
        SharedPreferences SEPrefs=getSharedPreferences("SAMEDEVICE", 2);
        SharedPreferences.Editor SEprefEditor=SEPrefs.edit();
        if(!SEPrefs.contains("WINS")) {
            SEprefEditor.putString("WINS", String.valueOf(wins));
            SEprefEditor.putString("LOSES", String.valueOf(loses));
            SEprefEditor.putString("DRAWS", String.valueOf(draws));
            SEprefEditor.apply();
        }
        else{
            wins+=Integer.parseInt(SEPrefs.getString("WINS","0"));
            loses+=Integer.parseInt(SEPrefs.getString("LOSES","0"));
            draws+=Integer.parseInt(SEPrefs.getString("DRAWS","0"));
            SEprefEditor.putString("WINS", String.valueOf(wins));
            SEprefEditor.putString("LOSES", String.valueOf(loses));
            SEprefEditor.putString("DRAWS", String.valueOf(draws));
            SEprefEditor.apply();
        }
    }
    @Override
    public void onBackPressed() {
        saveStats(playerOnewin,playerTwowin,draws);
        startActivity(new Intent(TwoPlayerSameDevice.this,OptionScreen.class));
        TwoPlayerSameDevice.this.finish();
    }

}
