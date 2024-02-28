package com.majorproject.onlineshopingapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class Home_fragment extends Fragment {

    ListView listView;
    CustomListAdapterProduct adapterProduct;
    public ArrayList<PojoClassProduct> arrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        listView=(ListView) view.findViewById(R.id.listView);
        arrayList=new ArrayList<PojoClassProduct>();

        for(int i=0;i<products.img.length;i++){
            arrayList.add(new PojoClassProduct(products.img[i],products.head[i],products.desc[i],products.price[i]));
        }
        adapterProduct=new CustomListAdapterProduct(arrayList,getContext());
        listView.setAdapter(adapterProduct);


        return view;
    }
}