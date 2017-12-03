package com.example.abhishek.rajasthane_paryatanam;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class FeedBackActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{

    RadioGroup rg1,rg2,rg3,rg4,rg5;
    RadioButton ra1,ra2,ra3,ra4;
    RadioButton rb1,rb2,rb3,rb4;
    RadioButton rc1,rc2,rc3,rc4;
    RadioButton rd1,rd2,rd3,rd4;
    RadioButton re1,re2,re3,re4;


    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);


        //5 RadioGroups for 5 questions
        rg1 = (RadioGroup)findViewById(R.id.rg1);
        /*ra1 = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
        ra2 = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
        ra3 = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());
        ra4 = (RadioButton) rg1.findViewById(rg1.getCheckedRadioButtonId());*/

        btSubmit = (Button)findViewById(R.id.button);
        btSubmit.setOnClickListener(this);




    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

    }

    @Override
    public void onClick(View view) {
        if(view==btSubmit){
            Toast.makeText(this,"Thanks for your Feeedback...!", Toast.LENGTH_LONG).show();
        }
    }
}
