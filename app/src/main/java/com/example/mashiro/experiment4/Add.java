package com.example.mashiro.experiment4;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mashiro.experiment4.DataBase.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {
    RecyclerView recyclerView = null;
    List<Item> itemList = null;
    private String[] titles = new String[]{"支出","收入"};
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        itemList = new ArrayList<Item>();
        init();

        recyclerView = (RecyclerView) findViewById(R.id.add_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(itemList);
        recyclerView.setAdapter(myAdapter);


        //viewpage
        mFragmentList.add(new expend());
        mFragmentList.add(new income());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.add_tablayout);

        //设置adapter，滑动时间
        final ViewPager viewPager = (ViewPager) findViewById(R.id.add_viewpager);
        FragmentPagerAdapter fragmentPagerAdapter = new PagerAdapter(this.getSupportFragmentManager(),mFragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        Button button = (Button)findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add.this,Main.class);
                startActivity(intent);
            }
        });

        //Database
        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this,"YiJi.db",null,1);
    }

    public void init() {
        itemList.add(new Item(R.drawable.baseline_date_range_black_48, "2018年12月1日                              10:51"));
        itemList.add(new Item(R.drawable.baseline_location_on_black_48, ""));
        itemList.add(new Item(R.drawable.baseline_edit_black_48, "备注"));
        itemList.add(new Item(R.drawable.ic_menu_camera, "上传图片或拍摄图片"));
    }


    class Item {
        int imageid;
        String text;

        public Item() {
        }

        public Item(int imageid) {
            this.imageid = imageid;
        }

        public Item(int imageid, String text) {
            this.imageid = imageid;
            this.text = text;
        }
    }


    public class PagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mFragmentList = null;

        public PagerAdapter(FragmentManager fm,List<Fragment> mFragmentList) {
            super(fm);
            this.mFragmentList = mFragmentList;

        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }


    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
        private List<Item> itemList;

        public RecyclerViewAdapter(List<Item> itemList) {
            this.itemList = itemList;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;

            public ViewHolder(View view) {
                super(view);
                imageView = (ImageView) view.findViewById(R.id.add_item_img);
                textView = (TextView) view.findViewById(R.id.add_item_text);
            }
        }


        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.add_list_item, null);
            ViewHolder holder = new ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
            Item item = itemList.get(position);
            holder.imageView.setImageResource(item.imageid);
            holder.textView.setText(item.text);
        }

        @Override
        public int getItemCount() {
            return itemList.size();
        }
    }



}

