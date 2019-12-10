package com.example.lxx.myalipay.ui.fragment.a_package.ui.logistics.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @package: com.example.lxx.myalipay.ui.handle
 * @date: 2018/7/6 9:57
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 描述：继承自SQLiteOpenHelper数据库类的子类
 */
public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

    private static String name = "temp.db";
    private static Integer version = 1;

    public RecordSQLiteOpenHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 打开数据库 & 建立了一个叫records的表，里面只有一列name来存储历史记录：
        db.execSQL("create table records(id integer primary key autoincrement,name varchar(200))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
