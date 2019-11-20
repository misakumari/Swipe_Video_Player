package com.misa.myvideos;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import static java.lang.Math.abs;

public class Profile extends MainActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        TextView uploadname=findViewById(R.id.uploadname);
        TextView movie=findViewById(R.id.movie);
        TextView actor=findViewById(R.id.actor);
        TextView actress=findViewById(R.id.actress) ;
        if(count==0){
            uploadname.setText("Uploader name: Aman Singh");
            movie.setText("Movie:Ae dil hai mushkil");
            actor.setText("actor name:Ranbir kapoor");
            actress.setText("actress name:Aishwarya Rai");
        }
        if(count==1){
            uploadname.setText("Uploader name: Aman Singh");
            movie.setText("Movie:Kabir Singh");
            actor.setText("actor name:Shahid Kapoor");
            actress.setText("actress name:Kiara Advani");
        }
        if(count==2){
            uploadname.setText("Uploader name: Aman Singh");
            movie.setText("Movie:Kal ho na ho");
            actor.setText("actor name:Shahrukh Khan");
            actress.setText("actress name:Preity Zinta");
        }
        if(count==3){
            uploadname.setText("Uploader name: Aman Singh");
            movie.setText("Movie:Tere naal love ho gaya");
            actor.setText("actor name:Ritesh Deshmukh");
            actress.setText("actress name:Kumari Misa");
        }
        Button back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

                } else if ((y1 > y2) && (abs(x1 - x2) < abs(y1 - y2)&& abs(y1-y2)>200)) {

                } else if (x1>x2 && abs(x1-x2)>200){
                    finish();
                } else if (x2>x1 && abs(x1-x2)>200){

                }
                break;

        }
        return false;
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}
