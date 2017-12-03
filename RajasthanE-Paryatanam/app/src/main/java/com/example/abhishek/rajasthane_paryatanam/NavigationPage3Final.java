package com.example.abhishek.rajasthane_paryatanam;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


//  Building 2 to Exit Gate2

public class NavigationPage3Final extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imgBtnGate3, imgBtnBuilding31, imgBtnBuilding32;
    private ImageView imgViewStart,imgViewEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page3_final);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        imgBtnGate3 = (ImageButton)findViewById(R.id.imgBtnGate);
        imgBtnBuilding31 = (ImageButton)findViewById(R.id.imgBtnBuilding1);
        imgBtnBuilding32 = (ImageButton)findViewById(R.id.imgBtnBuilding2);

        imgBtnGate3.setOnClickListener(this);
        imgBtnBuilding31.setOnClickListener(this);
        imgBtnBuilding32.setOnClickListener(this);



       /* Toast.makeText(this,"You are at Navigation Page 3",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"You are at Building 2",Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Go to Exit and Tap",Toast.LENGTH_SHORT).show();*/

        //##############     COMMON CODE        #############

        //For Animation setting resources
        imgViewStart = (ImageView)findViewById(R.id.imgViewStart);
        imgViewEnd = (ImageView)findViewById(R.id.imgViewEnd);

        //Animating the Icons
        //For animation
        Animation animationArrow = AnimationUtils.loadAnimation(this,R.anim.anim);
        imgViewStart.startAnimation(animationArrow);
        imgViewEnd.startAnimation(animationArrow);

    }




    @Override
    public void onClick(View v) {
        if(v == imgBtnGate3){

            // Toast.makeText(this,"You are at Exit Gate",Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Thank You for visiting the Museum App is closed", Toast.LENGTH_SHORT).show();
            // Intent intent= new Intent(this,MainActivity.class);
            // startActivity(intent);
            finish();

        }
        else if( v == imgBtnBuilding31 ){
            /*
            Toast.makeText(this,"You are at Building1",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this,NavigationPage1.class);
            startActivity(intent);
            finish();
            */

        }
        else if ( v == imgBtnBuilding32 ){

            Toast.makeText(this,"You are at Building2 ", Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"You are at Building2 Goto Exit Gate and Tap", Toast.LENGTH_SHORT).show();


            // Intent intent= new Intent(this,NavigationPage1.class);
            // startActivity(intent);
            // finish();

        }

    }
}
