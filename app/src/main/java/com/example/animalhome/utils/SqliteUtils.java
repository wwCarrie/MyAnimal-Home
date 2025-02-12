package com.example.animalhome.utils;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteUtils extends SQLiteOpenHelper {

    public SqliteUtils() {
        super(AppUtils.getApplication(), "animal_home.db", null, 1);
    }

    /**
     * 创建并获取单例
     */
    public static SqliteUtils getInstance() {
        return InstanceHolder.instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
        用户表(_user)：
        _id       integer  用户id
        username  varchar  用户名
        password  varchar  密码
         */
        sqLiteDatabase.execSQL("CREATE TABLE _user(_id INTEGER PRIMARY KEY AUTOINCREMENT,username VARCHAR(20) ,password VARCHAR(20))");
        /*
        发现表(_find):
        _id      integer  发现id
        title    varchar  名称
        url      varchar  图片url
        user_id  integer  用户id
         */
        sqLiteDatabase.execSQL("CREATE TABLE _find(_id INTEGER PRIMARY KEY AUTOINCREMENT,title VARCHAR(20),url VARCHAR(200),user_id INTEGER)");
        /*
         收藏表(_favorite)：
         _id      integer  收藏id
         user_id  integer  用户id
         find_id  integer  发现id
         */
        sqLiteDatabase.execSQL("CREATE TABLE _favorite(_id INTEGER PRIMARY KEY AUTOINCREMENT,user_id INTEGER,find_id INTEGER)");
        /*
        疫苗记录表(_vaccine_log)：
        _id          integer   记录id
        user_id      integer   用户id
        content      varchar   记录内容
        create_time  varchar   记录时间
         */
        sqLiteDatabase.execSQL("CREATE TABLE _vaccine_log(_id INTEGER PRIMARY KEY AUTOINCREMENT,user_id INTEGER,content VARCHAR(200),create_time VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    private static final class InstanceHolder {
        /**
         * 单例
         */
        static final SqliteUtils instance = new SqliteUtils();
    }
}
