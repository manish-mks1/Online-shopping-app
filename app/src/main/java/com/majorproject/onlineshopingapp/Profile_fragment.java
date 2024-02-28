package com.majorproject.onlineshopingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile_fragment extends Fragment {

    ListView lv;
    ImageView usericon;
    TextView username,useremail;
    CustomListAdapter adapter;
    LinearLayout backToHomeBtn;
    DBHelper db;
    public ArrayList<PojoClass> arr_bean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_profile_fragment, container, false);

        SharedPreferences sharedPreferences= getContext().getSharedPreferences("MySharedpre", Context.MODE_PRIVATE);
        String emailId=sharedPreferences.getString("emailId_key","guest@gmail.com");


        db=new DBHelper(getContext());

        lv=(ListView) view.findViewById(R.id.profileListView);
        arr_bean=new ArrayList<PojoClass>();

        int img[]={R.drawable.person_icon,R.drawable.phone_icon,R.drawable.red_email_icon,R.drawable.flat_location_logo,R.drawable.dob_icon};
        String head[]={"Name","Mobile","Email","Address","D.O.B"};
        String desc[]={db.getName(emailId),db.getMobileNo(emailId),emailId,db.getAddress(emailId),db.getDOB(emailId)};



        usericon=(ImageView) view.findViewById(R.id.iconuser);
        username=(TextView) view.findViewById(R.id.nameuser);
        useremail=(TextView) view.findViewById(R.id.emailuser);
        int image=db.getIcon(emailId);
        usericon.setImageResource(image);
        username.setText(db.getName(emailId));
        useremail.setText(emailId);

        backToHomeBtn=(LinearLayout) view.findViewById(R.id.backToHomeBtn);
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),MainActivity.class);
                startActivity(intent);

            }
        });


        for(int i=0;i<5;i++) {
            arr_bean.add(new PojoClass(img[i], head[i],desc[i]));
        }
        adapter=new CustomListAdapter(arr_bean,getContext());
        lv.setAdapter(adapter);
        return view;
    }
}