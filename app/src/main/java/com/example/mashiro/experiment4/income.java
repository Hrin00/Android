package com.example.mashiro.experiment4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class income extends Fragment {
    TextView type_text = null;
    ImageView type_image = null;
    RelativeLayout vp9 = null;
    RelativeLayout vp10 = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.add_viewpage_income, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        vp9 = getActivity().findViewById(R.id.add_viewpage_9);
        vp10 = getActivity().findViewById(R.id.add_viewpage_10);
        type_text = getActivity().findViewById(R.id.add_type_text);
        type_image = getActivity().findViewById(R.id.add_type_img);


        vp9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 9;
                type_text.setText("职业收入");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
        vp10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Add.typeid = 10;
                type_text.setText("业余收入");
                type_image.setVisibility(View.INVISIBLE);
            }
        });
    }


}
