package com.majorproject.onlineshopingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class CustomListAdapterProductCart extends BaseAdapter {
    public ArrayList<PojoClassProduct> arrayListListener;
    private List<PojoClassProduct> mListenerList;
    Context mContext;
    public CustomListAdapterProductCart(List<PojoClassProduct> mListenerList, Context context) {
        mContext=context;
        this.mListenerList=mListenerList;
        this.arrayListListener=new ArrayList<PojoClassProduct>();
        this.arrayListListener.addAll(mListenerList);
    }

    public class ViewHolder{
        ImageView mItemPic;
        TextView mhead;
        TextView mdesc;
        TextView price;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if(convertView==null){

            convertView= LayoutInflater.from(mContext).inflate(R.layout.cart_list_item,null);

            holder =new ViewHolder();
            holder.mItemPic=(ImageView) convertView.findViewById(R.id.itemPic);
            holder.mhead=(TextView) convertView.findViewById(R.id.itemHead);
            holder.mdesc=(TextView) convertView.findViewById(R.id.itemDesc);
            holder.price=(TextView) convertView.findViewById(R.id.itemPrice);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        try{
            int image=mListenerList.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            holder.mhead.setText(mListenerList.get(position).getHead());
            holder.mdesc.setText(mListenerList.get(position).getDesc());
            holder.price.setText(mListenerList.get(position).getPrice());

//            holder.itemCart.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if(holder.itemCart.getColorFilter()==R.color.red) holder.itemCart.setColorFilter(R.color.green);
//                    else holder.itemCart.setColorFilter(R.color.red);
//                 }
//            });

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
