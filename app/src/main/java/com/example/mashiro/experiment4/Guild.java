package com.example.mashiro.experiment4;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Guild extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    private int[] imageArray;
    private List<View> views;
    private ViewGroup viewGroup;

    //实例化原点
    private ImageView point;
    private ImageView[] points;

    private ImageButton btn_starter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guild);

        btn_starter = (ImageButton) findViewById(R.id.guide_starter);
        btn_starter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Guild.this, Login.class));
                finish();
            }
        });

        initViewPager();
        initPoint();
    }

    private void initPoint() {
        viewGroup = (ViewGroup) findViewById(R.id.layout_point);
        points = new ImageView[views.size()];

        for (int i = 0; i < views.size(); i++) {
            point = new ImageView(this);
            point.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            point.setPadding(30, 0, 30, 0);
            points[i] = point;
            if(i == 0){
                point.setBackgroundResource(R.drawable.ic_fullpoint);
            }else{
                point.setBackgroundResource(R.drawable.ic_emptypoint);
            }
            viewGroup.addView(points[i]);
        }
    }

    private void initViewPager() {
        viewPager = (ViewPager) findViewById(R.id.guide_paper);
        imageArray = new int[]{R.drawable.img_guider1, R.drawable.img_guider2, R.drawable.img_guider3};
        views = new ArrayList<>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        for (int i = 0; i < imageArray.length; i++) {
            ImageView view = new ImageView(this);
            view.setLayoutParams(params);
            view.setBackgroundResource(imageArray[i]);

            views.add(view);
        }

        viewPager.setAdapter(new GuidePagerAdapter(views));
        viewPager.setOnPageChangeListener(this);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < imageArray.length; i++) {
            points[position].setBackgroundResource(R.drawable.ic_fullpoint);
            if(position != i){
                points[i].setBackgroundResource(R.drawable.ic_emptypoint);
            }
        }

        if(position == imageArray.length-1){
            btn_starter.setVisibility(View.VISIBLE);
        }else{
            btn_starter.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

class GuidePagerAdapter extends PagerAdapter {

    private List<View> views;

    public GuidePagerAdapter(List<View> views){
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }
}