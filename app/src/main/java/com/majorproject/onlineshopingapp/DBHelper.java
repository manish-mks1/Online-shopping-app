package com.majorproject.onlineshopingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_Name="Login.db";
    public DBHelper(@Nullable Context context) {
        super(context, DB_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        myDB.execSQL("Create table users(userName Text,emailId Text primary key, password Text,icon int,mobileNo Text,Address Text,DOB Text)");
        myDB.execSQL("Create table cart(emailId Text, productName Text)");
        myDB.execSQL("Create table products(productName Text,productDesc Text,productPrice int,productImage int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int i, int i1) {
        myDB.execSQL("drop table if exists users");
        myDB.execSQL("drop table if exists cart");
        myDB.execSQL("drop table if exists products");
    }


    public Boolean insertProduct(String name,String desc,int price,int image){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("productName",name);
        contentValues.put("productDesc",desc);
        contentValues.put("productPrice",price);
        contentValues.put("productImage",image);
        long res=db.insert("products",null,contentValues);
        db.close();
        return res!=-1;

    }
    public Boolean insertItemToCart(String email,String itemname){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("emailId",email);
        contentValues.put("productName",itemname);
        long res=db.insert("cart",null,contentValues);
        db.close();
        return res!=-1;

    }

    public Boolean checkItemInProduct(String pname){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select productName from products where productName=?",new String[]{pname});
        if(cursor.getCount()>0){
            cursor.close();
            db.close();
            return true;
        }
        else return false;
    }
    public Boolean checkItemInCart(String pname){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select productName from cart where productName=?",new String[]{pname});
        if(cursor.getCount()>0){
            cursor.close();
            db.close();
            return true;
        }
        else return false;
    }
    public Cursor getProductsInCart(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("cart", new String[]{"productName"}, "emailId = ?", new String[]{email}, null, null, null, null);

        if (cursor.getCount() > 0) {
            return cursor;
        } else {
            cursor.close();
            db.close();
            Log.e("database error", "No records Found");
            return null;
        }
    }

//        SQLiteDatabase db=this.getReadableDatabase();
//        Cursor cursor=db.rawQuery("Select productName from cart where emailId =?",new String[]{email});
//        if (cursor.getCount()>0) {
//            cursor.close();
//            db.close();
//            return cursor;
//        } else {
//            cursor.close();
//            db.close();
//            return null;
//        }

    public String getProductDesc(String pName){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select productDesc from products where productName =?",new String[]{pName});
        if (cursor.getCount()>0 && cursor.moveToFirst()) {
            String desc = cursor.getString(0);
            cursor.close();
            db.close();
            return desc;
        } else {
            cursor.close();
            db.close();
            return "No any description";
        }
    }
    public int getProductPrice(String pName){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select productPrice from products where productName =?",new String[]{pName});
        if (cursor.getCount()>0 && cursor.moveToFirst()) {
            int price = cursor.getInt(0);
            cursor.close();
            db.close();
            return price;
        } else {
            cursor.close();
            db.close();
            return 0;
        }
    }
    public int getProductImage(String pname){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select productImage from products where productName =?",new String[]{pname});
        if (cursor.getCount()>0 && cursor.moveToFirst()) {
            int icon = cursor.getInt(0);
            cursor.close();
            db.close();
            return icon;
        } else {
            cursor.close();
            db.close();
            return R.drawable.dog;
        }
    }

    public Boolean insertData(String name,String email,String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("userName",name);
        contentValues.put("emailId",email);
        contentValues.put("password",pass);
        long res=db.insert("users",null,contentValues);
        db.close();
        return res!=-1;
    }
    public Boolean checkEmailPassword(String email,String password){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where emailId=? and password=?",new String[]{email,password});
        Boolean res=cursor.getCount()>0;
        cursor.close();
        db.close();
        return res;
    }
    public Boolean checkEmail(String email){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from users where emailId=?",new String[]{email});
        Boolean res=cursor.getCount()>0;
        cursor.close();
        db.close();
        return res;
    }

    public String getName(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select userName from users where emailId =?",new String[]{email});
        if (cursor.getCount()>0 && cursor.moveToFirst()) {
            String userName = cursor.getString(0);
            cursor.close();
            db.close();
            if(userName==null){
                return "Default kumar";
            }
            return userName;
        } else {
            cursor.close();
            db.close();
            return "Default kumar";
        }
    }
    public String getMobileNo(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select mobileNo from users where emailId =?",new String[]{email});
        if (cursor.getCount()>0 && cursor.moveToFirst()) {
            String mob = cursor.getString(0);
            cursor.close();
            db.close();
            if(mob==null){
                return "+91-8129999999";
            }
            return mob;
        } else {
            cursor.close();
            db.close();
            return "+91-8129999999";
        }
    }
    public String getAddress(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select Address from users where emailId =?",new String[]{email});
        if (cursor.getCount()>0 && cursor.moveToFirst()) {
            String add = cursor.getString(0);
            cursor.close();
            db.close();
            if(add==null){
                return "PariChowk, Greater Noida Utter Pradesh.";
            }
            return add;
        } else {
            cursor.close();
            db.close();
            return "PariChowk, Greater Noida Utter Pradesh.";
        }
    }
    public String getDOB(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select DOB from users where emailId =?",new String[]{email});
        if (cursor.getCount()>0 && cursor.moveToFirst()) {
            String dob = cursor.getString(0);
            cursor.close();
            db.close();
            if(dob==null){
                return "10-10-2000";
            }
            return dob;
        } else {
            cursor.close();
            db.close();
            return "10-10-2000";
        }
    }

    public int getIcon(String email){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select icon from users where emailId =?",new String[]{email});
        if (cursor.getCount()>0 && cursor.moveToFirst()) {
            int icon = cursor.getInt(0);
            cursor.close();
            db.close();
            if(icon==0){
                return R.drawable.account;
            }
            return icon;
        } else {
            cursor.close();
            db.close();
            return R.drawable.account;
        }
    }




}

