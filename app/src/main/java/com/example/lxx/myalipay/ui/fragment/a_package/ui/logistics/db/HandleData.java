package com.example.lxx.myalipay.ui.fragment.a_package.ui.logistics.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.lxx.myalipay.R;


/**
 * @package: com.example.lxx.myalipay.ui.handle
 * @date: 2018/5/16 11:41
 * @auther: 李熙祥
 * @email: 2914424169@qq.com
 * @descibe: 快递数据
 */
public class HandleData {
    Context context;
    private BaseAdapter adapter;
    private RecordSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    //构造方法
    public HandleData(Context context){
        this.context = context;
        helper = new RecordSQLiteOpenHelper(context);
    }


    /**
     * 插入数据
     */
    public void insertData(String tempName) {
        if(!hasData(tempName)){
            db = helper.getWritableDatabase();
            db.execSQL("insert into records(name) values('" + tempName + "')");
            db.close();
        }
    }


    /**
     * 检查数据库中是否已经有该条记录
     */
    public boolean hasData(String tempName) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name =?", new String[]{tempName});
        //判断是否有下一个
        return cursor.moveToNext();
    }

    /**
     * 模糊查询数据
     */
    public int queryData(String tempName, ListView listView) {
        Cursor cursor = helper.getReadableDatabase().rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);   // 创建adapter适配器对象
        adapter = new SimpleCursorAdapter(context, R.layout.css_text_1, cursor, new String[] { "name" },
                new int[] { R.id.tv_record_text }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        cursor.getCount();
        //return adapter;
        // 设置适配器
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return cursor.getCount();
    }

    /**
     * 清空数据
     */
    public void deleteData() {
        db = helper.getWritableDatabase();
        db.execSQL("delete from records");
        db.close();
    }
}


