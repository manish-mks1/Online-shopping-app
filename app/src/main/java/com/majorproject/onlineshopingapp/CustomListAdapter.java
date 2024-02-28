package com.majorproject.onlineshopingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CustomListAdapter extends BaseAdapter {
    public ArrayList<PojoClass> arrayListListener;
    private List<PojoClass> mListenerList;
    Context mContext;
    public CustomListAdapter(List<PojoClass> mListenerList, Context context) {
        mContext=context;
        this.mListenerList=mListenerList;
        this.arrayListListener=new ArrayList<PojoClass>();
        this.arrayListListener.addAll(mListenerList);
    }

    public class ViewHolder{
        ImageView mItemPic;
        TextView mhead;
        TextView mdesc;
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

            convertView= LayoutInflater.from(mContext).inflate(R.layout.profile_list_view_item,null);

            holder =new ViewHolder();
            holder.mItemPic=(ImageView) convertView.findViewById(R.id.icon);
            holder.mhead=(TextView) convertView.findViewById(R.id.head);
            holder.mdesc=(TextView) convertView.findViewById(R.id.desc);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        try{
            int image=mListenerList.get(position).getImage();
            holder.mItemPic.setImageResource(image);
            holder.mhead.setText(mListenerList.get(position).getHead());
            holder.mdesc.setText(mListenerList.get(position).getDesc());

//            holder.mhead.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
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
