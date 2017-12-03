package com.example.abhishek.rajasthane_paryatanam;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton frontImage_butn;
    public static final String IPV4_URL = "https://csgeeks.co.in/wp-admin/Tikshnayodha/";     //ipv4 for local server connection 192.168.43.186
    //public static final String BASE_URL = "https://csgeeks.co.in/wp-admin/Tikshnayodha/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frontImage_butn = (ImageButton) findViewById(R.id.img_btn_museum_blink);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.anim);
        frontImage_butn.startAnimation(myFadeInAnimation);
        frontImage_butn.setOnClickListener(this);//set onclicklistener to image button

        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        MyBroadCastReciever mReceiver = new MyBroadCastReciever (this);
        registerReceiver(mReceiver, filter);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.img_btn_museum_blink) ;
        {
            //Transition  to LoginActivity Page
            Intent intent_login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent_login);
            //this.finish();
        }
    }
}
