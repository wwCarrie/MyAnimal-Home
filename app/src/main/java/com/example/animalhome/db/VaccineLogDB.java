package com.example.animalhome.db;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.animalhome.entity.VaccineLog;
import com.example.animalhome.utils.SqliteUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Range")
public class VaccineLogDB {

    /**
     * 插入
     */
    public static BusinessResult<Void> insert(VaccineLog vaccineLog) {
        BusinessResult<Void> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getWritableDatabase();
        db.execSQL("insert into _vaccine_log(user_id,content,create_time) values(?,?,?)", new Object[]{vaccineLog.getUserId(), vaccineLog.getContent(), vaccineLog.getCreateTime()});
        result.setSuccess(true);
        result.setMessage("添加成功");
        return result;
    }

    /**
     * 删除
     */
    public static BusinessResult<Void> delete(Integer id) {
        BusinessResult<Void> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getWritableDatabase();
        db.execSQL("delete from _vaccine_log where _id=?", new Object[]{id});
        result.setSuccess(true);
        result.setMessage("删除成功");
        return result;
    }

    /**
     * 查询
     */
    public static BusinessResult<List<VaccineLog>> listByUserId(Integer userId) {
        BusinessResult<List<VaccineLog>> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from _vaccine_log where user_id=?", new String[]{String.valueOf(userId)});
        List<VaccineLog> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            VaccineLog vaccineLog = new VaccineLog();
            vaccineLog.setId(cursor.getInt(cursor.getColumnIndex("_id")));
            vaccineLog.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            vaccineLog.setContent(cursor.getString(cursor.getColumnIndex("content")));
            vaccineLog.setCreateTime(cursor.getString(cursor.getColumnIndex("create_time")));
            list.add(vaccineLog);
        }
        cursor.close();
        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(list);
        return result;
    }

}
