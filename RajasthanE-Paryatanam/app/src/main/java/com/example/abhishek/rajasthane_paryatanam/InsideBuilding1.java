package com.example.abhishek.rajasthane_paryatanam;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class InsideBuilding1 extends AppCompatActivity implements View.OnTouchListener {

    private ImageView imgViewLeftDoor,imgViewCenterDoor,imgViewRightDoor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside_building1);
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
            try {
                openApp(this, "com.Tikshnayodha.interior");
                Toast.makeText(this, "You have selected Center Gallery", Toast.LENGTH_LONG).show();
            }catch(Exception e){}

        }

        else if( v == imgViewLeftDoor){
            Toast.makeText(this,"You have selected Left Gallery", Toast.LENGTH_LONG).show();

        }
        else if( v == imgViewRightDoor){
            Toast.makeText(this,"You have selected Right Door Gallery", Toast.LENGTH_LONG).show();

        }
        return false;

    }
    public static boolean openApp(Context context, String pkg_name) {
        PackageManager manager = context.getPackageManager();
        Intent intent = manager.getLaunchIntentForPackage(pkg_name);
        if (intent == null) {
            return false;
        } else {
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(intent);
            return true;
        }

    }
}
