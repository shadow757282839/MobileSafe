package cn.edu.gdmec.android.mobileguard.m3communicationguard.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.mobileguard.m3communicationguard.db.BlackNumberOpenHelper;
import cn.edu.gdmec.android.mobileguard.m3communicationguard.entity.BlackContactInfo;

/**
 * Created by Z_er on 2017/11/4.
 */

public class BlackNumberDao {
    private BlackNumberOpenHelper blackNumberOpenHelper;

    public BlackNumberDao(Context context){
        super();
        blackNumberOpenHelper = new BlackNumberOpenHelper(context,"blackNumber.db",null,1);
    }
    /*
    添加数据
    @param blackContactInfo
    @return
     */
    public boolean add(BlackContactInfo blackContactInfo){
        SQLiteDatabase db = blackNumberOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (blackContactInfo.phoneNumber.startsWith("+86")){
            blackContactInfo.phoneNumber = blackContactInfo.phoneNumber
                    .substring(3,blackContactInfo.phoneNumber.length());
        }
        values.put("number",blackContactInfo.phoneNumber);
        values.put("name",blackContactInfo.contactName);
        values.put("mode",blackContactInfo.mode);
        long rowid = db.insert("blacknumber",null,values);
        if (rowid == -1){//插入数据不成功
            return false;
        }else {
            return true;
        }
    }
    /*
    删除数据
     */
    public boolean delete(BlackContactInfo blackContactInfo){
        SQLiteDatabase db = blackNumberOpenHelper.getWritableDatabase();
        int rownumber = db.delete("blacknumber","number=?",
                new String[]{blackContactInfo.phoneNumber});
        if (rownumber == 0){
            return false;//删除数据不成功
        }else {
            return true;
        }
    }
    /*
    分页查询数据库的几率
    @param pagenumber,第几页页码 从第0页开始
    @param pagesize 每一个页面的大小
     */
    public List<BlackContactInfo> getPageBlackNumber(int pagenumber,
                                                     int pagesize){
        //得到可读的数据库
        SQLiteDatabase db = blackNumberOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select number,mode,name from blacknumber limit ? offset ?",
                new String[]{String.valueOf(pagesize),
                String.valueOf(pagesize * pagenumber)});
        List<BlackContactInfo> mBlackContactInfos = new ArrayList<BlackContactInfo>();
        while (cursor.moveToNext()){
            BlackContactInfo info = new BlackContactInfo();
            info.phoneNumber = cursor.getString(0);
            info.mode = cursor.getInt(1);
            info.contactName = cursor.getString(2);
            mBlackContactInfos.add(info);
        }
        cursor.close();
        db.close();
        SystemClock.sleep(30);
        return mBlackContactInfos;
    }
    /*
    判断号码是否在黑名单中存在
     */
    public boolean IsNumberExist(String number){
        //得到可读的数据库
        SQLiteDatabase db = blackNumberOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("blacknumber",null,"number=?",
                new String[]{number},null,null,null);
        if (cursor.moveToNext()){
            cursor.close();
            db.close();
            return true;
        }
        cursor.close();
        db.close();
        return false;
    }
    /*
    根据号码查询黑名单信息
     */
    public int getBlackContactMode(String number){
        Log.d("incoming phonenumber",number);
        //得到可读的数据库
        SQLiteDatabase db = blackNumberOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("blacknumber",new String[]{"mode"},"number=?",
        new String[]{number},null,null,null);
        int mode = 0;
        if (cursor.moveToNext()){
            mode = cursor.getInt(cursor.getColumnIndex("mode"));
        }
        cursor.close();
        db.close();
        return mode;
    }
    /*
    获取数据库的总条目个数
     */
    public int getTotalNumber(){
        //得到可读的数据库
        SQLiteDatabase db = blackNumberOpenHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from blacknumber",null);
        cursor.moveToNext();
        int count = cursor.getInt(0);
        cursor.close();
        db.close();
        return count;
    }




}