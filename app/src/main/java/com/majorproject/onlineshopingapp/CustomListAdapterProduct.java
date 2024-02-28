package com.majorproject.onlineshopingapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;


public class CustomListAdapterProduct extends BaseAdapter {
    public ArrayList<PojoClassProduct> arrayListListener;
    private List<PojoClassProduct> mListenerList;
    DBHelper db;
    Context mContext;
    public CustomListAdapterProduct(List<PojoClassProduct> mListenerList, Context context) {
        this.mContext=context;
        this.db=new DBHelper(mContext);
        this.mListenerList=mListenerList;
        this.arrayListListener=new ArrayList<PojoClassProduct>();
        this.arrayListListener.addAll(mListenerList);
    }

    public class ViewHolder{
        ImageView mItemPic;
        TextView mhead;
        TextView mdesc;
        TextView price;
        ImageView itemCart;
    }

    @Override
    public int getCount() {
        return mListenerList.size();
    }

    @Override
    public Object getItem(int pos) {
        return mListenerList.get(pos);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){

            convertView= LayoutInflater.from(mContext).inflate(R.layout.list_item,null);

            holder =new ViewHolder();
            holder.mItemPic=(ImageView) convertView.findViewById(R.id.itemPic);
            holder.mhead=(TextView) convertView.findViewById(R.id.itemHead);
            holder.mdesc=(TextView) convertView.findViewById(R.id.itemDesc);
            holder.itemCart=(ImageView) convertView.findViewById(R.id.itemCart);
            holder.price=(TextView) convertView.findViewById(R.id.itemPrice);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        try{


            int image=mListenerList.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            String pname=mListenerList.get(position).getHead();
            String pdesc=mListenerList.get(position).getDesc();
            int price=mListenerList.get(position).getPrice();
            holder.mhead.setText(pname);
            holder.mdesc.setText(pdesc);
//            holder.price.setText(price);
            holder.price.setText(String.valueOf(price));


            SharedPreferences sharedPreferences=mContext.getSharedPreferences("MySharedpre", Context.MODE_PRIVATE);
            String emailId= sharedPreferences.getString("emailId_key","guest@gmail.com");


            if (mListenerList.get(position).isAddedToCart()) {
                holder.itemCart.setColorFilter(ContextCompat.getColor(mContext, R.color.darkgreen));
            } else {
                holder.itemCart.setColorFilter(ContextCompat.getColor(mContext, R.color.red));
            }

            holder.itemCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListenerList.get(position).isAddedToCart()) {
                        holder.itemCart.setColorFilter(ContextCompat.getColor(mContext, R.color.darkgreen));
                    } else {
                        holder.itemCart.setColorFilter(ContextCompat.getColor(mContext, R.color.red));
                    }
                    boolean isAdded = mListenerList.get(position).isAddedToCart();
                    mListenerList.get(position).setAddedToCart(!isAdded); // Toggle the state
                    notifyDataSetChanged();
//                    holder.itemCart.setColorFilter(ContextCompat.getColor(mContext,R.color.darkgreen));
                    if(!db.checkItemInProduct(pname)){
                        db.insertProduct(pname,pdesc,price,image);
                    }
                    if(!db.checkItemInCart(pname)){
                        db.insertItemToCart(emailId,pname);
                    }
                 }
            });

//            holder.mItemPic.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    }
//            });

        }catch (Exception ex){

        }
        return convertView;
    }
}
