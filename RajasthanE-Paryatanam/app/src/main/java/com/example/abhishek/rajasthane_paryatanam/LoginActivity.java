package com.example.abhishek.rajasthane_paryatanam;

import android.content.ContentValues;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Global variables declaration
    EditText etname, etpass;
    ImageButton btlogin;
    TextView ttsignup;
    Spinner spinnerLanguage;
    MySQLiteQuery mySQLiteQuery;
    public static String language="English";
    ArrayAdapter<CharSequence> adapterLanguage;
    int m_id;
    String m_name,m_desc,imageurl,m_idd;
    public static boolean PARSE_FLAG=false,ENGLISH_FLAG=false,HINDI_FLAG=false, JAPANESE_FLAG=false;

    String[] column = {"monument_id", "imageurl","name","description"};

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        MyBroadCastReciever mReceiver = new MyBroadCastReciever (this);
        registerReceiver(mReceiver, filter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        etname = (EditText) findViewById(R.id.etname);
        etpass = (EditText) findViewById(R.id.etpass);
        btlogin = (ImageButton) findViewById(R.id.btlogin);
        ttsignup = (TextView) findViewById(R.id.ttsignup);
        btlogin.setOnClickListener(this);
        mySQLiteQuery=new MySQLiteQuery(this);
        spinnerLanguage = (Spinner) findViewById(R.id.spinner_lang);

        adapterLanguage = ArrayAdapter.createFromResource(this, R.array.array_language_name, android.R.layout.simple_spinner_item);//category_names is array define strings.xml
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        try {
            spinnerLanguage.setAdapter(adapterLanguage);  //setting the array adapter to sppiner
            //spinnerLanguage.setBackground(R.drawable.regimg);
            spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    // Toast.makeText(getBaseContext(),parent.getItemAtPosition(position)+" is selected",Toast.LENGTH_LONG);
                    language = parent.getItemAtPosition(position).toString();

                    //Toast.makeText(getApplicationContext(), "Selected language is " + language, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } catch (Exception e) {
        }

    }

    //for language spinner
    private boolean validateEmailPass(String email, String pass) {

        matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            Toast.makeText(this, "Please Enter Name / Email", Toast.LENGTH_LONG).show();
            return false;
        } else if (pass.length() < 8) {
            Toast.makeText(this, "Password should be of minimum 8 characters", Toast.LENGTH_LONG).show();
            return false;
        } /*else if (name.indexOf('@') == -1) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG).show();
            return false;
        } else if (name.indexOf('.') == -1) {
            Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG).show();
            return false;
        }*/
        return true;
    }

    public void OnSignup(View view) {
        //Toast.makeText(this, "Signup clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {

        //Toast.makeText(this, "Logging In Clicked....", Toast.LENGTH_SHORT).show();
        String email = etname.getText().toString();
        // String email = etname.getText().toString();
        String pass = etpass.getText().toString();
        //Invoking the Email Validation
        boolean flag = validateEmailPass(email, pass);
        //Toast.makeText(this,"Logging In....",Toast.LENGTH_LONG).show();

        //If validated
        if (flag == true) {
            //Toast.makeText(getApplicationContext(),language,Toast.LENGTH_LONG).show();
            if(language.equalsIgnoreCase("English")){
                ENGLISH_FLAG=true;
                HINDI_FLAG=false;
                JAPANESE_FLAG=false;
                jsonDecoder(MainActivity.IPV4_URL+"api_rajasthan_monuments_details.php", "monuments_details","English");

            }else if(language.equalsIgnoreCase("Hindi")){
                ENGLISH_FLAG=false;
                HINDI_FLAG=true;
                JAPANESE_FLAG=false;
                jsonDecoder(MainActivity.IPV4_URL+"api_rajasthan_monuments_details_hindi.php", "monuments_details","hindi");

            }else if(language.equalsIgnoreCase("Japanese")){
                ENGLISH_FLAG=false;
                HINDI_FLAG=false;
                JAPANESE_FLAG=true;
                jsonDecoder(MainActivity.IPV4_URL+"api_rajasthan_monuments_details_japanes.php", "monuments_details","japanese");

            }
            String type = "login";
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute(type, email, pass);
            Intent toMainActivity=new Intent(this,MenuActivity.class);
            startActivity(toMainActivity);

        }

    }

    //JsonDecoder is used to parse the json file from server

    public void jsonDecoder(String phpFile, final String arrayName, final String language){

        final RequestQueue rque;
        rque = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.GET, phpFile , null, new Response.Listener<JSONObject>() {
            public void onResponse(JSONObject response) {
                try {
                    SQLiteDatabase writeDatabase = mySQLiteQuery.getWritableDatabase();
                    ContentValues cntval = new ContentValues();
                    JSONArray ja = response.getJSONArray(arrayName);
                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = ja.getJSONObject(i);
                        m_id = jo.getInt("monument_id");
                        imageurl = jo.getString("imageurl");
                        m_name = jo.getString("name");
                        m_desc = jo.getString("description");
                        cntval.put("monument_id", m_id);
                        cntval.put("imageurl", imageurl);
                        cntval.put("name", m_name);
                        cntval.put("description", m_desc);
                        //writeDatabase = mySQLiteQuery.getWritableDatabase();
                        if(language.equalsIgnoreCase("English")){
                            ENGLISH_FLAG=true;
                            HINDI_FLAG=false;
                            JAPANESE_FLAG=false;
                            writeDatabase.insert("e_monuments_table", null, cntval);
                        }else if(language.equalsIgnoreCase("Hindi")){
                            ENGLISH_FLAG=false;
                            HINDI_FLAG=true;
                            JAPANESE_FLAG=false;
                            writeDatabase.insert("h_monuments_table", null, cntval);
                        } else if(language.equalsIgnoreCase("Japanese")){
                            ENGLISH_FLAG=false;
                            HINDI_FLAG=false;
                            JAPANESE_FLAG=true;
                            writeDatabase.insert("j_monuments_table", null, cntval);
                        }
                        //PARSE_FLAG=true;
                        //Toast.makeText(getApplicationContext(),"Eflag:"+ENGLISH_FLAG+"HFlag:"+HINDI_FLAG+"language:"+language, Toast.LENGTH_SHORT).show();
                    }
                    writeDatabase.close();

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "ERROR to connecting volley " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        );rque.add(jsonObjRequest);
    }

}