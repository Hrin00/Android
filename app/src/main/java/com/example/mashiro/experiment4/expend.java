package com.example.mashiro.experiment4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class expend extends Fragment {

    TextView type_text = null;
    ImageView type_image = null;
    RelativeLayout vp1 = null;
    RelativeLayout vp2 = null;
    RelativeLayout vp3 = null;
    RelativeLayout vp4 = null;
    RelativeLayout vp5 = null;
    RelativeLayout vp6 = null;
    RelativeLayout vp7 = null;
    RelativeLayout vp8 = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_viewpage_expend, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        vp1 = getActivity().findViewById(R.id.add_viewpage_1);
        vp2 = getActivity().findViewById(R.id.add_viewpage_2);
        vp3 = getActivity().findViewById(R.id.add_viewpage_3);
        vp4 = getActivity().findViewById(R.id.add_viewpage_4);
        vp5 = getActivity().findViewById(R.id.add_viewpage_5);
        vp6 = getActivity().findViewById(R.id.add_viewpage_6);
        vp7 = getActivity().findViewById(R.id.add_viewpage_7);
        vp8 = getActivity().findViewById(R.id.add_viewpage_8);
        type_text = getActivity().findViewById(R.id.add_type_text);
        type_image = getActivity().findViewById(R.id.add_type_img);


        vp1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 1;
                type_text.setText("餐饮食品");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
        vp2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 2;
                type_text.setText("衣服饰品");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
        vp3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 3;
                type_text.setText("居家生活");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
        vp4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 4;
                type_text.setText("行车交通");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
        vp5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 5;
                type_text.setText("文化教育");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
        vp6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 6;
                type_text.setText("健康医疗");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
        vp7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 7;
                type_text.setText("投资支出");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
        vp8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 8;
                type_text.setText("其他支出");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
    }



}
