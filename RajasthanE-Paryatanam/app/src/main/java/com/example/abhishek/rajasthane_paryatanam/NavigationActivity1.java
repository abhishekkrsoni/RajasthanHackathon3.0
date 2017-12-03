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


public class NavigationActivity1 extends AppCompatActivity implements View.OnClickListener{

    private ImageButton imgBtnGate1, imgBtnBuilding1, imgBtnBuilding2;
    private ImageView imgViewStart,imgViewEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        imgBtnGate1 = (ImageButton)findViewById(R.id.imgBtnGate);
        imgBtnBuilding1 = (ImageButton)findViewById(R.id.imgBtnBuilding1);
        imgBtnBuilding2 = (ImageButton)findViewById(R.id.imgBtnBuilding2);

        imgBtnGate1.setOnClickListener(this);
        imgBtnBuilding1.setOnClickListener(this);
        imgBtnBuilding2.setOnClickListener(this);

        Toast.makeText(this,"You are at Navigation Page 1", Toast.LENGTH_SHORT).show();
        Toast.makeText(this,"Tap at Entrance and begin your Journey", Toast.LENGTH_SHORT).show();

        //For Animation setting resources
        imgViewStart = (ImageView)findViewById(R.id.imgViewStart);
        imgViewEnd = (ImageView)findViewById(R.id.imgViewEnd);

        //Animating the Icons For animation
        Animation animationArrow = AnimationUtils.loadAnimation(this,R.anim.anim);
        imgViewStart.startAnimation(animationArrow);
        imgViewEnd.startAnimation(animationArrow);


    }
    //When comes back from the Gallery / Building 1 consisting of 3 Gallery Category
    public void onRestart(){
        super.onRestart();
        //Toast.makeText(this,"You Have Visited Buildiding 1 of the Museum",Toast.LENGTH_LONG).show();
        Intent intent= new Intent(this,NavigationPage2.class);
        startActivity(intent);
        // Toast.makeText(this,"Go to Building2 an",Toast.LENGTH_SHORT).show();
        finish();
    }



    /*public void onResume(){
        super.onResume();


        Toast.makeText(this,"In onResume Function of navigation 1",Toast.LENGTH_LONG).show();

        Intent intent= new Intent(this,NavigationPage2.class);
        startActivity(intent);
       // Toast.makeText(this,"Go to Building2 an",Toast.LENGTH_SHORT).show();
        finish();

    }*/



    @Override
    public void onClick(View v) {
        if(v == imgBtnGate1){
            //Toast.makeText(this,"You are at Entrance",Toast.LENGTH_SHORT).show();
            //Toast.makeText(this,"Goto Building 1 and Tap",Toast.LENGTH_SHORT).show();

            //Intent intent= new Intent(this,NavigationPage1.class);
            //startActivity(intent);
            //finish();
        }
        else if( v == imgBtnBuilding1 ){
            //Toast.makeText(this,"You Reached Building1",Toast.LENGTH_LONG).show();
            Intent intent= new Intent(this,InsideBuilding1.class);
            startActivity(intent);
            // finish();
        }
        /*
        else if ( v == imgBtnBuilding2 ){
           /*
            Toast.makeText(this,"You are at Building2",Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(this,NavigationPage1.class);
            startActivity(intent);
            finish();


        }*/

    }
}
