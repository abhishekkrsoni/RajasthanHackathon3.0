package com.example.abhishek.rajasthane_paryatanam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

// For Building1 to Building2 way

public class NavigationPage2 extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imgBtnGate2, imgBtnBuilding21, imgBtnBuilding22;
    private ImageView imgViewStart,imgViewEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this,"You are at Page2", Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        imgBtnGate2 = (ImageButton)findViewById(R.id.imgBtnGate);
        imgBtnBuilding21 = (ImageButton)findViewById(R.id.imgBtnBuilding1);
        imgBtnBuilding22 = (ImageButton)findViewById(R.id.imgBtnBuilding2);

        imgBtnGate2.setOnClickListener(this);
        imgBtnBuilding21.setOnClickListener(this);
        imgBtnBuilding22.setOnClickListener(this);

        /*Toast.makeText(this,"You are at Navigation Page 2",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"You are at Building 1",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Go to Building 2 and Tap",Toast.LENGTH_SHORT).show();*/

        //For Animation setting resources
        imgViewStart = (ImageView)findViewById(R.id.imgViewStart);
        imgViewEnd = (ImageView)findViewById(R.id.imgViewEnd);

        //Animating the Icons
        //For animation
        Animation animationArrow = AnimationUtils.loadAnimation(this,R.anim.anim);
        imgViewStart.startAnimation(animationArrow);
        imgViewEnd.startAnimation(animationArrow);


    }
    /*
        public void onResume(){
            super.onResume();


            Toast.makeText(this,"In onResume Function of Navigation 3",Toast.LENGTH_LONG).show();

            Intent intent= new Intent(this,NavigationPage3Final.class);
            startActivity(intent);
            //Toast.makeText(this,"Go to Building2 an",Toast.LENGTH_SHORT).show();
            finish();

        }

    */
    public void onRestart(){
        super.onRestart();
        //Toast.makeText(this,"You Have Visited Buildiding 2 of the Museum",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(this,NavigationPage3Final.class);
        startActivity(intent);
        // Toast.makeText(this,"Go to Building2 an",Toast.LENGTH_SHORT).show();
        finish();
    }


    @Override
    public void onClick(View v) {
        if(v == imgBtnGate2){
            /*
            Toast.makeText(this,"You are at Gate",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this,NavigationPage1.class);
            startActivity(intent);
            finish();
            */
        }

        else if( v == imgBtnBuilding21 ){
            /*Toast.makeText(this,"You are at Building 1",Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Go to Building 2 and Tap",Toast.LENGTH_SHORT).show();*/

            //Intent intent= new Intent(this,NavigationPage1.class);
            // startActivity(intent);
            //  finish();

        }
        else if ( v == imgBtnBuilding22 ){
            Toast.makeText(this,"You Reached Building 2", Toast.LENGTH_SHORT).show();
            // Toast.makeText(this,"Go to Building 2 and Tap the Button",Toast.LENGTH_SHORT).show();

            // Going into the Building 2 where different Gallery are present

            Intent intent= new Intent(this,InsideBuilding2.class);
            startActivity(intent);
            // finish();

        }

    }
}
