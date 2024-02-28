package com.majorproject.onlineshopingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Cart_fragment extends Fragment {

    ListView cartListView;
    TextView emptyCart;
    Button orderBtn;
    DBHelper db;
    CustomListAdapterProductCart adapterProductCart;
    ArrayList<PojoClassProduct> arrayCart;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_cart_fragment, container, false);
        db=new DBHelper(getContext());
        emptyCart=view.findViewById(R.id.cartEmpty);
        cartListView=view.findViewById(R.id.cartListView);
        orderBtn=view.findViewById(R.id.orderBtn);

        SharedPreferences sharedPreferences=getContext().getSharedPreferences("MySharedpre", Context.MODE_PRIVATE);
        String emailId= sharedPreferences.getString("emailId_key","guest@gmail.com");
        String email=db.getName(emailId);

        arrayCart=new ArrayList<PojoClassProduct>();
//        try (Cursor cursor = db.getProductsInCart(email)) {
//            if(cursor != null && cursor.moveToFirst()) {
//                do {
//                    String pname = cursor.getString(cursor.getColumnIndex("productName"));
//                    arrayCart.add(new PojoClassProduct(db.getProductImage(pname), pname, db.getProductDesc(pname), db.getProductPrice(pname)));
//                }while (cursor.moveToNext());
//            }
//        }
//        arrayCart.add(new PojoClassProduct(R.drawable.salwar_girl, "Salwaar suit", "Most beatiful Salwar suit ", 2546));
//        arrayCart.add(new PojoClassProduct(R.drawable.salwar_girl, "Salwaar suit", "Most beatiful Salwar suit ", 2546));
//        arrayCart.add(new PojoClassProduct(R.drawable.salwar_girl, "Salwaar suit", "Most beatiful Salwar suit ", 2546));

        /*for(int i=0;i<cursor.getCount();i++){
            cursor.moveToPosition(i);
            String pname = cursor.getString(cursor.getColumnIndex("productName"));
            arrayCart.add(new PojoClassProduct(db.getProductImage(pname),pname,db.getProductDesc(pname),db.getProductPrice(pname)));
        }*/

        adapterProductCart=new CustomListAdapterProductCart(arrayCart,getContext());
        cartListView.setAdapter(adapterProductCart);


        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                arrayCart.remove(new PojoClassProduct(R.drawable.salwar_girl, "Salwaar suit", "Most beatiful Salwar suit ", 2546));
//                arrayCart.remove(new PojoClassProduct(R.drawable.salwar_girl, "Salwaar suit", "Most beatiful Salwar suit ", 2546));
//                arrayCart.remove(new PojoClassProduct(R.drawable.salwar_girl, "Salwaar suit", "Most beatiful Salwar suit ", 2546));
                adapterProductCart.notifyDataSetChanged();
                emptyCart.setText("Cart is Empty");
//                adapterProductCart=new CustomListAdapterProductCart(arrayCart,getContext());
//                cartListView.setAdapter(adapterProductCart);
            }
        });


        return view;
    }
}