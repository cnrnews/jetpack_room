package com.imooc.room

import android.content.Context
import android.text.style.TtsSpan
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * @Author lihl
 * @Date 2022/1/8 6:59
 * @Email 1601796593@qq.com
 *
 * Room 数据库定义
 * entities: 数据表
 * views：虚拟实体表
 */
@Database(entities = [DbUser::class,Book::class,JUser::class],version = 1,exportSchema = true, views = [TempBean::class])
abstract class UserDatabase:RoomDatabase() {

    abstract val userDao:UserDao

    companion object{
        const val DB_NAME = "user.db"
        private var instance:UserDatabase?=null

        // 单例设计模式
        @Synchronized
        fun getInstance(context: Context?):UserDatabase?{
            if (instance == null){
                instance = Room.databaseBuilder(
                    context!!,
                    UserDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries() // 允许在主线程操作数据
                    .addMigrations(migration1_2) // 数据库升级策略
                    .build();
            }
            return instance
        }


        /**
         * 数据库升级
         *
         * Migration(int startVersion, int endVersion)
         * startVersion 大于  endVersion 升级
         * startVersion 小于  endVersion 降级
         */
        val migration1_2 = object :Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("")
            }
        }
    }
}