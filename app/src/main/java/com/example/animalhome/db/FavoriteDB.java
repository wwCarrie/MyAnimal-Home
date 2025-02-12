package com.example.animalhome.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.animalhome.utils.SqliteUtils;

public class FavoriteDB {

    /**
     * 喜欢
     */
    public static BusinessResult<Void> like(Integer userId, Integer findId) {
        BusinessResult<Void> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getWritableDatabase();
        db.execSQL("insert into _favorite(user_id,find_id) values(?,?)", new Object[]{userId, findId});
        result.setSuccess(true);
        result.setMessage("添加成功");
        return result;
    }

    /**
     * 取消喜欢
     */
    public static BusinessResult<Void> unlike(Integer userId, Integer findId) {
        BusinessResult<Void> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getWritableDatabase();
        db.execSQL("delete from _favorite where user_id=? and find_id=?", new Object[]{userId, findId});
        result.setSuccess(true);
        result.setMessage("取消成功");
        return result;
    }

    /**
     * 查询用户是否喜欢
     */
    public static BusinessResult<Boolean> isLike(Integer userId, Integer findId) {
        BusinessResult<Boolean> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from _favorite where user_id=? and find_id=?", new String[]{String.valueOf(userId), String.valueOf(findId)});
        boolean isLike = cursor.moveToNext();
        cursor.close();
        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(isLike);
        return result;
    }

}
