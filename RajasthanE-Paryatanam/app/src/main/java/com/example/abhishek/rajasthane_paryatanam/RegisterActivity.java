package com.example.abhishek.rajasthane_paryatanam;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    //-----global variable declaration
    private EditText etvName,etvGender,etvCountry,etvAge,etvState,etveEmail,etvMobile,etvPass;
    private TextView ttvLogin;
    boolean flag=false;
    private ImageButton btnReg;
    private static final String REGISTER_URL = "http://"+MainActivity.IPV4_URL+"/visitor/visitorreg.php";

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        etvName = (EditText) findViewById(R.id.etname);
        etvGender = (EditText) findViewById(R.id.etgender);
        etvCountry = (EditText) findViewById(R.id.etcountry);
        etvAge = (EditText) findViewById(R.id.etage);
        etvState = (EditText) findViewById(R.id.etstate);
        etveEmail = (EditText) findViewById(R.id.etemail);
        etvMobile = (EditText) findViewById(R.id.etmobile);
        etvPass = (EditText) findViewById(R.id.etpass);
        btnReg = (ImageButton) findViewById(R.id.btreg);
        ttvLogin = (TextView)findViewById(R.id.ttlogin);

        btnReg.setOnClickListener(this);
        ttvLogin.setOnClickListener(this);

    }

    //To validate the Registration Form
    private boolean regFormValidation(String name, String gender, String country, String age, String state, String email, String mobile, String pass ){
        int ageint;
        long mobno;
        if(name.equals("")){
            Toast.makeText(this,"Please Enter Email", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!gender.toLowerCase().equalsIgnoreCase("f") && !gender.toLowerCase().equalsIgnoreCase("m")){
            Toast.makeText(this,"Gender should M/F", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(country.equals("")){
            Toast.makeText(this,"Please enter your country", Toast.LENGTH_LONG).show();
            return false;
        }
        else if(age.equals("")){
            Toast.makeText(this,"Please enter Age", Toast.LENGTH_LONG).show();
            ageint= Integer.parseInt(age);
            return false;
        }
        else if(state.equals("")){
            Toast.makeText(this,"Please enter State", Toast.LENGTH_LONG).show();
            // ageint= Integer.parseInt(age);
            return false;
        }
        else if(state.equals("")){
            Toast.makeText(this,"Please enter State", Toast.LENGTH_LONG).show();
        }
        //For Email
        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            Toast.makeText(this,"Invalid Email "+email, Toast.LENGTH_LONG).show();
            return false;
        }
        /*else if(email.equals("")){
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_LONG).show();
            return false;
        }
        else if( email.indexOf('@') == -1 ){
            Toast.makeText(this,"Invalid Email @ "+email,Toast.LENGTH_LONG).show();
            Toast.makeText(this,"Invalid Email @",Toast.LENGTH_LONG).show();
            return false;
        }
        else if( email.indexOf('.') == -1 ){
            Toast.makeText(this,"Invalid Email  . ",Toast.LENGTH_LONG).show();
            return false;
        }*/
        else if( mobile.length() != 10 ){
            Toast.makeText(this,"Please enter a 10 digit mobile no", Toast.LENGTH_LONG).show();
            return false;
        }
        else if( pass.length()<8 ){
            Toast.makeText(this,"Password should be of minimum 8 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        //for age numeric check
        try {
            ageint = Integer.parseInt(age);
        }catch (NumberFormatException e){
            Toast.makeText(this,"Please enter numeric Age", Toast.LENGTH_LONG).show();
            return false;
        }

        //for mobile no numeric check
        try {
            long mobileInt = Long.parseLong(mobile);
        }catch (NumberFormatException e){
            Toast.makeText(this,"Please Enter a valid numeric Mobile NO", Toast.LENGTH_LONG).show();
            return false;
        }
        return  true;
    }


    @Override
    public void onClick(View v) {
        if(v == btnReg ){
            //Registration Form Validatdation
            // invoking the registerUser method which will invoke
            registerUser();
        }
    }

    private void registerUser(){
        String name = etvName.getText().toString().trim().toLowerCase();
        String gender = etvGender.getText().toString().trim().toLowerCase();
        String country = etvCountry.getText().toString().trim().toLowerCase();
        String age = etvAge.getText().toString().trim().toLowerCase();
        String state = etvState.getText().toString().trim().toLowerCase();
        String email = etveEmail.getText().toString().trim().toLowerCase();
        String mobile = etvMobile.getText().toString().trim().toLowerCase();
        String pass = etvPass.getText().toString().trim().toLowerCase();

        String S = "Name: "+name+"\n Gender: "+gender+"\n country: "+country+"\nAge"+age+ "\nState"+state+"\nEmail: "+email+"Mobile: "+mobile+"\nPassword: "+pass;

        //Invoking the regFormValidation
        flag = regFormValidation(name, gender, country, age, state, email, mobile,pass );

        if( flag == true){
            register(name,gender,country,age,state,email,mobile,pass);
            ttvLogin.setText("You'r Successfully Registered ");
            clear();
            Intent intent_Login = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent_Login);
        }

    }

    //Post the data entered in the form to the Server
    private void register(String name, String gender, String country, String age, String state, String email, String mobile, String pass){

        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RegisterUserClass ruc = new RegisterUserClass();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show( RegisterActivity.this, "Please Wait",null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                //Insertion of Data into SQL database
                HashMap<String, String> data = new HashMap<String,String>();
                data.put("vname",params[0]);
                data.put("vgender",params[1]);
                data.put("vcountry",params[2]);
                data.put("vage",params[3]);
                data.put("vstate",params[4]);
                data.put("vemail",params[5]);
                data.put("vmobileno",params[6]);
                data.put("vpassword",params[7]);
                String result = ruc.sendPostRequest(REGISTER_URL,data);
                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(name,gender,country,age,state,email,mobile,pass);
    }

    public void clear(){
        etvName.setText("");
        etvGender.setText("");
        etvCountry.setText("");
        etvAge.setText("");
        etvState.setText("");
        etveEmail.setText("");
        etvMobile.setText("");
        etvPass.setText("");
    }
}
