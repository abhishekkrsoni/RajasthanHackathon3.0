package com.example.abhishek.rajasthane_paryatanam;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

import java.util.ArrayList;
import java.util.List;


public class GalleryActivity extends FragmentActivity {

    private TextView indicatorTv,name,gal_title;
    private ViewPager viewPager;
    public static int INDEX_NO;
    private List<CommonFragment> fragments = new ArrayList<>();
    private final String[] eNameArray={"UDAIPUR","JAIPUR","CHITTORGARH","JODHPUR"};
    private final String[] hNameArray={" उदयपुर ","जयपुर "," चित्तौढगढ "," जोधपुर "};
    private final String[] jNameArray={" ウダイプル "," ジャイプール "," チトゴガル "," ジョドプール "};
    private final String[] imageArray = {"assets://udaipur.jpg", "assets://jaipur.jpg","assets://chittorgarh.jpg","assets://jodhpur.jpg"};
    public final String[] gallery_title={" GALLERY "," गेलरी ", " ギャラリー "};
    public final String[] click_here={" Click here to know details "," विवरण जानने के लिए यहां क्लिक करें "," 詳細を知るにはここをクリック "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        gal_title=(TextView)findViewById(R.id.gallery_titl);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            } else {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
        INDEX_NO=0;
        //Toast.makeText(getApplicationContext(), "INDEX_NO:- "+INDEX_NO, Toast.LENGTH_LONG).show();
        initImageLoader();// 2. ImageLoader
        fillViewPager();// 3. ViewPager
    }


    @SuppressWarnings("deprecation")
    private void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .memoryCacheExtraOptions(480, 800)      // default = device screen dimensions
                .threadPoolSize(3)      // default
                .threadPriority(Thread.NORM_PRIORITY - 1)      // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
                .discCacheSize(50 * 1024 * 1024)
                .discCacheFileCount(100)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(this)) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs().build();

        ImageLoader imageLoader = ImageLoader.getInstance();    // 2.ImageLoader
        imageLoader.init(config);
    }


    private void fillViewPager() {      //ViewPager
        indicatorTv = (TextView) findViewById(R.id.indicator_tv);
        name=(TextView)findViewById(R.id.name);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setPageTransformer(false, new CustPagerTransformer(this));// 1. viewPager parallax PageTransformer
        // 2. viewPageradapter
        for (int i = 0; i < 5; i++) {
            fragments.add(new CommonFragment());//fragment
        }

        viewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                CommonFragment fragment = fragments.get(position % 5);
                fragment.bindData(imageArray[position % imageArray.length]);
                return fragment;
            }

            @Override
            public int getCount() {
                return 50;
            }
        });

        // 3. viewPager
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                updateIndicatorTv();
                INDEX_NO=position%9;
                //Toast.makeText(getApplicationContext(), "INDEX_NO:- "+INDEX_NO, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        updateIndicatorTv();
    }

    private void updateIndicatorTv() {
        int totalNum = viewPager.getAdapter().getCount();
        int currentItem = viewPager.getCurrentItem() + 1;
       // Toast.makeText(getApplicationContext(), "v "+LoginActivity.ENGLISH_FLAG+" "+LoginActivity.HINDI_FLAG, Toast.LENGTH_LONG).show();
        if(LoginActivity.ENGLISH_FLAG) {
            name.setText(eNameArray[(currentItem - 1) % 4]);
            gal_title.setText(gallery_title[0]);
        }
        else if(LoginActivity.HINDI_FLAG) {
            name.setText(hNameArray[(currentItem - 1) % 4]);
            gal_title.setText(gallery_title[1]);
        }
        else if(LoginActivity.JAPANESE_FLAG) {
            name.setText(jNameArray[(currentItem - 1) % 4]);
            gal_title.setText(gallery_title[2]);
        }
        indicatorTv.setText(Html.fromHtml("<font color='#12edf0'>" + currentItem + "</font>  /  " + totalNum));
    }

}


