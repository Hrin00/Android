package com.example.mashiro.experiment4;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final int TYPE_TAG = 0;
    private final int TYPE_ITEM = 1;

    private LinkedList<Item> list = null;
    private LinkedList<Item> groupkey = new LinkedList<Item>();
    private LinkedList<Item> income = new LinkedList<Item>();
    private LinkedList<Item> expend = new LinkedList<Item>();
    RecyclerView recyclerView = null;
    RecyclerViewAdapter recyclerViewAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String pwd = intent.getStringExtra("pwd");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View Headerview = navigationView.getHeaderView(0);
        ImageView icon = (ImageView) Headerview.findViewById(R.id.icon_head);
        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Login.class);
                startActivity(intent);
            }
        });
        TextView Userid = (TextView) Headerview.findViewById(R.id.text_id);
        TextView Hint = (TextView) Headerview.findViewById(R.id.text_hint);
        if (!"".equals(id) && !"".equals(pwd)) {
            Userid.setText(id);
            Hint.setVisibility(View.INVISIBLE);
        } else {
            Userid.setText("未登录");
            Hint.setVisibility(View.VISIBLE);
        }

        init();

/*
        //ListView
        ListView listView = (ListView)findViewById(R.id.list_view);
        MyAdapter myadapter = new MyAdapter();
        listView.setAdapter(myadapter);
*/

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setItemAnimator(new MyAnimator());


    }

    public class MyAnimator extends SimpleItemAnimator {
        List<RecyclerView.ViewHolder> removeHolders = new ArrayList<>();
        List<RecyclerView.ViewHolder> removeAnimators = new ArrayList<>();
        List<RecyclerView.ViewHolder> moveHolders = new ArrayList<>();
        List<RecyclerView.ViewHolder> moveAnimators = new ArrayList<>();
        List<RecyclerView.ViewHolder> addHolders = new ArrayList<>();
        List<RecyclerView.ViewHolder> addAnimators = new ArrayList<>();
        @Override
        public boolean animateRemove(RecyclerView.ViewHolder holder) {
            removeHolders.add(holder);
            return true;
        }
        @Override
        public boolean animateAdd(RecyclerView.ViewHolder holder) {
            addHolders.add(holder);
            return false;
        }
        @Override
        public boolean animateMove(RecyclerView.ViewHolder holder, int fromX, int fromY, int toX, int toY) {
            holder.itemView.setTranslationY(fromY - toY);
            moveHolders.add(holder);
            return true;
        }
        @Override
        public boolean animateChange(RecyclerView.ViewHolder oldHolder, RecyclerView.ViewHolder newHolder, int fromLeft, int fromTop, int toLeft, int toTop) {
            return false;
        }
        @Override
        public void runPendingAnimations() {
            if(!removeHolders.isEmpty()) {
                for(RecyclerView.ViewHolder holder : removeHolders) {
                    remove(holder);
                }
                removeHolders.clear();
            }
            if(!moveHolders.isEmpty()){
                for(RecyclerView.ViewHolder holder : moveHolders) {
                    move(holder);
                }
                moveHolders.clear();
            }
            if(!addHolders.isEmpty()){
                for(RecyclerView.ViewHolder holder : addHolders){
                    add(holder);
                }
            }
        }
        @Override
        public void endAnimation(RecyclerView.ViewHolder item) {
        }
        @Override
        public void endAnimations() {
        }
        @Override
        public boolean isRunning() {
            return !(removeHolders.isEmpty() && removeAnimators.isEmpty() && moveHolders.isEmpty() && moveAnimators.isEmpty());
        }

        private void remove(final RecyclerView.ViewHolder holder){
            removeAnimators.add(holder);
            TranslateAnimation animation = new TranslateAnimation(0, 1000, 0, 0);
            animation.setDuration(500);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    dispatchRemoveStarting(holder);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    removeAnimators.remove(holder);
                    dispatchRemoveFinished(holder);
                    if(!isRunning()){
                        dispatchAnimationsFinished();
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            holder.itemView.startAnimation(animation);
        }

        private void move(final RecyclerView.ViewHolder holder){
            moveAnimators.add(holder);
            ObjectAnimator animator = ObjectAnimator.ofFloat(holder.itemView,
                    "translationY", holder.itemView.getTranslationY(), 0);
            animator.setStartDelay(200);
            animator.setDuration(500);
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationStart(android.animation.Animator animation) {
                    dispatchMoveStarting(holder);
                }

                @Override
                public void onAnimationEnd(android.animation.Animator animation) {
                    dispatchMoveFinished(holder);
                    moveAnimators.remove(holder);
                    if(!isRunning()) dispatchAnimationsFinished();
                }
            });
            animator.start();
        }
        private void add(final RecyclerView.ViewHolder holder){
            moveAnimators.add(holder);

            /*removeAnimators.add(holder);
            TranslateAnimation animation = new TranslateAnimation(0, 1000, 0, 0);
            animation.setDuration(500);
            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    dispatchRemoveStarting(holder);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    removeAnimators.remove(holder);
                    dispatchRemoveFinished(holder);
                    if(!isRunning()){
                        dispatchAnimationsFinished();
                    }
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            holder.itemView.startAnimation(animation);*/

        }

    }

    public void init() {
        list = new LinkedList<Item>();

        groupkey.add((new Item("收入")));
        Item item1 = new Item(this.getResources().getDrawable(R.drawable.ic_launcher_background), "工作收入", "2018-11-01", "2700");
        Item item2 = new Item(this.getResources().getDrawable(R.drawable.ic_launcher_background), "业余收入", "2018-11-09", "500");
        income.add(item1);
        income.add(item2);

        groupkey.add((new Item("支出")));
        Item item3 = new Item(this.getResources().getDrawable(R.drawable.ic_launcher_background), "餐饮食品", "2018-11-02", "-1300");
        Item item4 = new Item(this.getResources().getDrawable(R.drawable.ic_launcher_background), "行车交通", "2018-11-05", "-700");
        expend.add(item3);
        expend.add(item4);

        list.add(groupkey.get(0));
        list.addAll(income);
        list.add(groupkey.get(1));
        list.addAll(expend);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent intent = new Intent(Main.this, Add.class);
            startActivity(intent);
        } else if (id == R.id.action_remove) {
            if(list.size() == 0)
                Toast.makeText(this,"列表为空，无法删除！",Toast.LENGTH_SHORT).show();
            else
                recyclerViewAdapter.delData(list.size()-1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_setting) {
            Intent intent = new Intent(Main.this, Setting.class);
            startActivity(intent);

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_about_us) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class Item {

        Item(Drawable image, String title, String time, String money) {
            this.image = image;
            this.title = title;
            this.time = time;
            this.money = money;
        }
        Item(String title, String time, String money) {
            this.title = title;
            this.time = time;
            this.money = money;
        }

        Item(String title) {
            this.title = title;
        }

        private Drawable image;
        private String title;
        private String time;
        private String money;
    }


    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        abstract class ViewHolder extends RecyclerView.ViewHolder {
            ViewHolder(View itemView) {
                super(itemView);
            }

            public abstract int getType();
        }

        class item extends ViewHolder {
            View ItemView;
            ImageView icon;
            TextView title;
            TextView time;
            TextView money;

            public item(View view) {
                super(view);
                ItemView = view;
                icon = (ImageView) view.findViewById(R.id.item_image);
                title = (TextView) view.findViewById(R.id.item_text_title);
                time = (TextView) view.findViewById(R.id.item_text_time);
                money = (TextView) view.findViewById(R.id.item_text_money);
            }

            @Override
            public int getType() {
                return TYPE_ITEM;
            }

        }

        class tag extends ViewHolder {
            TextView title;

            public tag(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.tag);
            }

            @Override
            public int getType() {
                return TYPE_TAG;
            }
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view;
            ViewHolder viewHolder = null;
            if (viewType == TYPE_TAG) {
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_tag, null);
                viewHolder = new tag(view);
            } else if (viewType == TYPE_ITEM) {
                view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.list_item, null);
                viewHolder = new item(view);

                //设置点击事件
                final ViewHolder finalViewHolder = viewHolder;
                ((item) viewHolder).ItemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = finalViewHolder.getAdapterPosition();
                        Item item = list.get(position);
                        Toast.makeText(v.getContext(), "you clicked view" + item.title, Toast.LENGTH_SHORT).show();
                    }
                });
                ((item) viewHolder).icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = finalViewHolder.getAdapterPosition();
                        Item item = list.get(position);
                        Toast.makeText(v.getContext(), "you clicked image" + item.title, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Item item = list.get(position);
            if (getItemViewType(position) == TYPE_TAG) {
                tag tagviewholder = (tag) holder;
                tagviewholder.title.setText(list.get(position).title);
            } else if (getItemViewType(position) == TYPE_ITEM) {
                item itemviewholder = (item) holder;
                itemviewholder.title.setText(list.get(position).title);
                itemviewholder.time.setText(list.get(position).time);
                int m = Integer.parseInt(list.get(position).money);
                if (m > 0) {
                    itemviewholder.money.setTextColor(getColor(R.color.colorGreen));
                    itemviewholder.money.setText(m + ".0");
                } else {
                    itemviewholder.money.setTextColor(getColor(R.color.colorRed));
                    itemviewholder.money.setText(m + ".0");
                }

                itemviewholder.title.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Main.this, "you clicked", Toast.LENGTH_LONG).show();
                    }
                });

            }


        }

        @Override
        public int getItemViewType(int position) {
            if (groupkey.contains(list.get(position))) {
                return TYPE_TAG;
            } else {
                return TYPE_ITEM;
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public void addData(int position){
            list.add(position,new Item("我被添加了","2018-11-13","100"));
            recyclerView.scrollToPosition(position);
//            notifyItemInserted(position);
        }

        public void delData(int position){
            list.remove(position);
            notifyItemRemoved(position);
        }

    }

}


