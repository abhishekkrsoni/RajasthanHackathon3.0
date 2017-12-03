package com.example.abhishek.rajasthane_paryatanam;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


public class DetailActivity extends FragmentActivity {

    ListView lvDetail;
    Context context = DetailActivity.this;
    ArrayList myList = new ArrayList();
    MySQLiteQuery mySQLiteQuery;
    String[] column = {"monument_id", "imageurl","name","description"};
    public final String[] relted_exibits={" RELATED EXHIBITS "," संबंधित प्रदर्शन  "," 関連展示品 "};
    public static final String EXTRA_IMAGE_URL = "detailImageUrl";
    public static final String IMAGE_TRANSITION_NAME = "transitionImage";
    public static final String ADDRESS4_TRANSITION_NAME = "address4";
    public static final String HEAD1_TRANSITION_NAME = "head1";
    public static final String HEAD2_TRANSITION_NAME = "head2";
    public static final String HEAD3_TRANSITION_NAME = "head3";
    public static final String HEAD4_TRANSITION_NAME = "head4";
    private TextView address4,name,related_exibit;//View
    private ImageView imageView;
    //public static int[] m_id = new int[10];
    public static String[] m_name = new String[10];
    public static String[] m_desc = new String[10];
    public static String[] m_image = new String[10];
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        related_exibit=(TextView)findViewById(R.id.related_exibts);
        name=(TextView)findViewById(R.id.name);
        imageView = (ImageView)findViewById(R.id.image);
        address4 =(TextView)findViewById(R.id.address4);
        lvDetail=(ListView) findViewById(R.id.lvCustomList);
        mySQLiteQuery=new MySQLiteQuery(this);

        if(LoginActivity.language.equalsIgnoreCase("English"))
            related_exibit.setText(relted_exibits[0]);
        else if(LoginActivity.language.equalsIgnoreCase("Hindi"))
            related_exibit.setText(relted_exibits[1]);
        else if(LoginActivity.language.equalsIgnoreCase("Japanese"))
            related_exibit.setText(relted_exibits[2]);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        getDataFromDataBase();
        name.setText(m_name[0]);
        address4.setText(m_desc[0]);

        String imageUrl = getIntent().getStringExtra(EXTRA_IMAGE_URL);
        ImageLoader.getInstance().displayImage(imageUrl, imageView);
        ViewCompat.setTransitionName(imageView, IMAGE_TRANSITION_NAME);
        //ViewCompat.setTransitionName(address4, ADDRESS4_TRANSITION_NAME);
        getDataInList();
        lvDetail.setAdapter(new MyBaseAdapter(context, myList));
        lvDetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition = position;// ListView Clicked item index
                Intent i = new Intent(getApplicationContext(), QRActivity.class);
                i.putExtra("image_id",m_image[position+1]);
                //Toast.makeText(getApplicationContext(), "Position:- "+itemPosition, Toast.LENGTH_LONG).show();// Show Alert
                startActivity(i);
            }
        });
    }

    private void getDataInList() {
        //String path = Environment.getExternalStorageDirectory().toString()+"/Museum_Images/";
        String path="/storage/38B6-1238/rajasthan_tourisum_pics/amber_palace.png";
        Bitmap bmp;
        for (int i = 1; i<m_image.length; i++) {
            ListData ld = new ListData();       // Create a new object for each list item
            ld.setTitle(m_name[i]);
            bmp= BitmapFactory.decodeFile(path+m_image[i]);
            //Toast.makeText(getApplicationContext()," "+m_image[i],Toast.LENGTH_SHORT).show();
            //bmp= BitmapFactory.decodeFile(path);
            ld.setImgResId(bmp);
            myList.add(ld);     // Add this object into the ArrayList myList
        }
    }

    void getDataFromDataBase(){

        try {
            if(LoginActivity.ENGLISH_FLAG) {
                SQLiteDatabase writeDatabase = mySQLiteQuery.getReadableDatabase();
                String selectQuery = " select * from e_monuments_table where monument_id > " + ((GalleryActivity.INDEX_NO + 1) * 100 - 1) + " and monument_id < " + (GalleryActivity.INDEX_NO + 2) * 100;
                Cursor cursor = writeDatabase.rawQuery(selectQuery, null);
                int i = 0;
                while (cursor.moveToNext()) {
                    m_image[i] = cursor.getString(1);
                    m_name[i] = cursor.getString(2);
                    m_desc[i] = cursor.getString(3);
                    i++;
                    //Toast.makeText(getApplicationContext(),"id= "+cursor.getString(0), Toast.LENGTH_LONG).show();
                }
                writeDatabase.close();
            }
            else if(LoginActivity.HINDI_FLAG)
            {
                SQLiteDatabase writeDatabase1 = mySQLiteQuery.getReadableDatabase();
                String selectQuery1 = " select * from h_monuments_table where monument_id > " + ((GalleryActivity.INDEX_NO + 1) * 100 - 1) + " and monument_id < " + (GalleryActivity.INDEX_NO + 2) * 100;
                Cursor cursor1 = writeDatabase1.rawQuery(selectQuery1, null);
                int i1 = 0;
                while (cursor1.moveToNext()) {
                    m_image[i1] = cursor1.getString(1);
                    m_name[i1] = cursor1.getString(2);
                    m_desc[i1] = cursor1.getString(3);
                    i1++;
                    //Toast.makeText(getApplicationContext(),"id= "+cursor.getString(0), Toast.LENGTH_LONG).show();
                }
                writeDatabase1.close();
            }
            else if(LoginActivity.JAPANESE_FLAG) {
                SQLiteDatabase writeDatabase2 = mySQLiteQuery.getReadableDatabase();
                String selectQuery2 = " select * from j_monuments_table where monument_id > " + ((GalleryActivity.INDEX_NO + 1) * 100 - 1) + " and monument_id < " + (GalleryActivity.INDEX_NO + 2) * 100;
                Cursor cursor2 = writeDatabase2.rawQuery(selectQuery2, null);
                int i2 = 0;
                while (cursor2.moveToNext()) {
                    m_image[i2] = cursor2.getString(1);
                    m_name[i2] = cursor2.getString(2);
                    m_desc[i2] = cursor2.getString(3);
                    i2++;
                    //Toast.makeText(getApplicationContext(),"id= "+cursor.getString(0), Toast.LENGTH_LONG).show();
                }
                writeDatabase2.close();
            }
        }
        catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Some Error "+e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}