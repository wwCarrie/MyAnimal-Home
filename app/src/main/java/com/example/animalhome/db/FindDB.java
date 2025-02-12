package com.example.animalhome.db;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.animalhome.entity.Find;
import com.example.animalhome.utils.CurrentUserUtils;
import com.example.animalhome.utils.SqliteUtils;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Range")
public class FindDB {

    /**
     * 查询所有发现
     */
    public static BusinessResult<List<Find>> getAllFindList() {
        BusinessResult<List<Find>> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getReadableDatabase();
        List<Find> findList = new ArrayList<>();
        //根据id倒序查询
        Cursor cursor = db.rawQuery("select * from _find order by _id desc", null);
        while (cursor.moveToNext()) {
            int findId = cursor.getInt(cursor.getColumnIndex("_id"));
            BusinessResult<Boolean> likeResult = FavoriteDB.isLike(CurrentUserUtils.getCurrentUser().getId(), findId);
            Find find = new Find();
            find.setId(findId);
            find.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            find.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            find.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            find.setFavorite(likeResult.getData());
            findList.add(find);
        }
        cursor.close();
        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(findList);
        return result;
    }

    /**
     * 查询用户发现
     */
    public static BusinessResult<List<Find>> getFindListByUserId(Integer userId) {
        BusinessResult<List<Find>> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getReadableDatabase();
        List<Find> findList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from _find where user_id=?", new String[]{String.valueOf(userId)});
        while (cursor.moveToNext()) {
            int findId = cursor.getInt(cursor.getColumnIndex("_id"));
            BusinessResult<Boolean> likeResult = FavoriteDB.isLike(CurrentUserUtils.getCurrentUser().getId(), findId);
            Find find = new Find();
            find.setId(findId);
            find.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            find.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            find.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            find.setFavorite(likeResult.getData());
            findList.add(find);
        }
        cursor.close();
        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(findList);
        return result;
    }

    /**
     * 查询用户喜欢的发现
     */
    public static BusinessResult<List<Find>> getFavoriteFindListByUserId(Integer userId) {
        BusinessResult<List<Find>> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getReadableDatabase();
        List<Find> findList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from _find where _id in (select find_id from _favorite where user_id=?)", new String[]{String.valueOf(userId)});
        while (cursor.moveToNext()) {
            int findId = cursor.getInt(cursor.getColumnIndex("_id"));
            BusinessResult<Boolean> likeResult = FavoriteDB.isLike(CurrentUserUtils.getCurrentUser().getId(), findId);
            Find find = new Find();
            find.setId(findId);
            find.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            find.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            find.setUserId(cursor.getInt(cursor.getColumnIndex("user_id")));
            find.setFavorite(likeResult.getData());
            findList.add(find);
        }
        cursor.close();
        result.setSuccess(true);
        result.setMessage("查询成功");
        result.setData(findList);
        return result;
    }

    /**
     * 添加发现
     */
    public static BusinessResult<Void> addFind(Find find) {
        BusinessResult<Void> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getWritableDatabase();
        db.execSQL("insert into _find(title,url,user_id) values(?,?,?)", new Object[]{find.getTitle(), find.getUrl(), find.getUserId()});
        result.setSuccess(true);
        result.setMessage("添加成功");
        return result;
    }

    /**
     * 删除发现
     */
    public static BusinessResult<Void> deleteFind(Integer id) {
        BusinessResult<Void> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getWritableDatabase();
        db.execSQL("delete from _find where _id=?", new Object[]{id});
        result.setSuccess(true);
        result.setMessage("删除成功");
        return result;
    }

    public static BusinessResult<Void> updateFind(Find find) {
        BusinessResult<Void> result = new BusinessResult<>();
        SQLiteDatabase db = SqliteUtils.getInstance().getWritableDatabase();
        db.execSQL("update _find set title=?,url=? where _id=?", new Object[]{find.getTitle(), find.getUrl(), find.getId()});
        result.setSuccess(true);
        result.setMessage("更新成功");
        return result;
    }
}
