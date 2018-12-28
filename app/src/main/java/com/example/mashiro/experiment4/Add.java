package com.example.mashiro.experiment4;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mashiro.experiment4.DataBase.MyDatabaseHelper;

import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.content.DialogInterface.BUTTON_NEGATIVE;
import static android.content.DialogInterface.BUTTON_POSITIVE;

public class Add extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    String dbdata;
    static int typeid = 0;
    double money;




    RecyclerView recyclerView = null;
    List<Item> itemList = null;
    private String[] titles = new String[]{"支出","收入"};
    private List<Fragment> mFragmentList = new ArrayList<Fragment>();
    private TextView date;
    private TextView time;
    DecimalFormat format = new DecimalFormat("00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        itemList = new ArrayList<Item>();
        init();

        //viewpage
        mFragmentList.add(new expend());
        mFragmentList.add(new income());

        TabLayout tabLayout = (TabLayout) findViewById(R.id.add_tablayout);

        //设置adapter，滑动时间
        final ViewPager viewPager = (ViewPager) findViewById(R.id.add_viewpager);
        FragmentPagerAdapter fragmentPagerAdapter = new PagerAdapter(this.getSupportFragmentManager(),mFragmentList);
        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        RelativeLayout timedate = (RelativeLayout) findViewById(R.id.add_datetime);

        timedate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showTime();
                showCalendar();
            }
        });

        date = (TextView)findViewById(R.id.add_date_text);
        time = (TextView)findViewById(R.id.add_time_text);

        Button button = (Button)findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText value = findViewById(R.id.add_type_value);
                money = Double.parseDouble(value.getText().toString());
                if(typeid != 9 && typeid != 10)
                    money = -money;
                dbHelper = new MyDatabaseHelper(getApplicationContext(),"YiJi.db",null,1);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("typeid",typeid);
                if(dbdata!=null) {
                    values.put("time", dbdata);
                    values.put("money", money);
                    db.insert("RECORD", null, values);
                    Intent intent = new Intent(Add.this, Main.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"请选择时间",Toast.LENGTH_SHORT).show();
                }
            }
        });


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

    private void showCalendar(){
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(Add.this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                date.setText(format.format(year)+"年"+format.format(monthOfYear+1)+"月"+format.format(dayOfMonth)+"日");
                dbdata = format.format(year)+"-"+format.format(monthOfYear+1)+"-"+format.format(dayOfMonth);

            }

        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();


    }
    private void showTime(){
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(Add.this, new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                time.setText(format.format(hourOfDay)+":"+format.format(minute));
            }
        },calendar.get(calendar.HOUR),calendar.get(calendar.MINUTE),false).show();
    }



}

