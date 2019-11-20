package com.misa.myvideos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
static int count=0,seekpos=0;
float x1,x2,y1,y2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MediaController mediaController=new MediaController(this);
        //mediaController.setAnchorView(((View)findViewById(R.id.conslo)));
        ((VideoView)findViewById(R.id.vdo)).setMediaController(mediaController);
        list();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2=event.getX();
                y2=event.getY();
                if (y1 < y2 && abs(x1 - x2) < abs(y1 - y2) && abs(y1-y2)>200) {
                    count-=1;
                    if(count<=-1) count=3;
                    list();
                } else if ((y1 > y2) && (abs(x1 - x2) < abs(y1 - y2)&& abs(y1-y2)>200)) {
                    count+=1;
                    if(count>=4) count=0;
                    list();
                } else if (x1>x2 && abs(x1-x2)>200){
                    Toast.makeText(this,"Channel Subscribed",Toast.LENGTH_SHORT).show();
                } else if (x2>x1 && abs(x1-x2)>200){
                    VideoView videoView= findViewById(R.id.vdo);
                    seekpos=videoView.getCurrentPosition();
                    videoView.stopPlayback();
                    videoView.suspend();
                    Intent myIntent = new Intent(this,Profile.class);
                    startActivityForResult(myIntent,0);
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                }
                break;

        }
        return false;
    }

    void playvid(int song){
        final VideoView videoView = findViewById(R.id.vdo);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + song);
        videoView.start();
    }
    void list(){
        if(count==0){
            playvid(R.raw.buleya);
        }
        if(count==1){
            playvid(R.raw.kabir_singh);
        }
        if(count==2){
            playvid(R.raw.kalhonaho);
        }
        if(count==3){
            playvid(R.raw.pop);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        VideoView videoView = findViewById(R.id.vdo);
        if(videoView!=null) {
            videoView.seekTo(seekpos);
            videoView.start();
        }
    }

    @Override
    protected void onDestroy() {
        VideoView videoView = findViewById(R.id.vdo);
        if(videoView!=null)
        seekpos = videoView.getCurrentPosition();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        VideoView videoView=findViewById(R.id.vdo);
        videoView.seekTo(seekpos);
        videoView.start();
    }
}
