package com.example.abhishek.rajasthane_paryatanam;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class InsideBuilding2 extends AppCompatActivity implements View.OnTouchListener {

    private ImageView imgViewLeftDoor,imgViewCenterDoor,imgViewRightDoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_building2);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        imgViewLeftDoor = (ImageView)findViewById(R.id.imgViewLeftDoor);
        imgViewRightDoor = (ImageView)findViewById(R.id.imgViewRightDoor);
        imgViewCenterDoor = (ImageView)findViewById(R.id.imgViewCenterDoor);

        imgViewLeftDoor.setOnTouchListener(this);
        imgViewRightDoor.setOnTouchListener(this);
        imgViewCenterDoor.setOnTouchListener(this);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if( v == imgViewCenterDoor){
            /*Intent intent= new Intent(this,DetailActivity.class);
            startActivity(intent);*/
            Toast.makeText(this,"You have selected Center Gallery of Building 2", Toast.LENGTH_LONG).show();
        }

        else if( v == imgViewLeftDoor){
            Toast.makeText(this,"You have selected Left Gallery of Building 2", Toast.LENGTH_LONG).show();

        }
        else if( v == imgViewRightDoor){
            Toast.makeText(this,"You have selected Right Door Gallery of Building 2", Toast.LENGTH_LONG).show();

        }
        return false;

    }


}
