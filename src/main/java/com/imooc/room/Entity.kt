package com.imooc.room

import androidx.room.*
import androidx.room.ForeignKey.SET_DEFAULT

/**
 * @Author lihl
 * @Date 2022/1/8 6:42
 * @Email 1601796593@qq.com
 *
 * Room 数据表
 */

@Entity(tableName = "db_user",foreignKeys = [
    ForeignKey(entity = Book::class,parentColumns = ["bid"],childColumns = ["bookId"],onDelete = SET_DEFAULT)
],indices = [Index("uid"), Index("bookId")]) // 数据库名
class DbUser{
    @PrimaryKey(autoGenerate = true) // 住建，自增
    var uid = 0
    @ColumnInfo(name="uname") // 表字段
    var name:String?=null
    @ColumnInfo // 表字段
    var city:String?=null
    @ColumnInfo // 表字段
    var age = 0
    @Ignore // 忽略此字段
    var isSingle = false

    // 关联其他类
    @Embedded
    var baby:Child?=null

    var bookId:Int = 0

    override fun toString(): String {
        return "DbUser{"+
                "uid="+uid+
                ", name='"+name+"'\''"+
                ", city='"+city+"'\''"+
                ", age='"+age+"'\''"+
                ", isSingle='"+isSingle+ '}'
    }
}

data class Child(
    val cid:Int,
    val cname:String,
    val cAge:Int,
    val sex:Int
)

@Entity
data class Book(
    @PrimaryKey(autoGenerate = true)
    val bid:Int,
    val name:String,
    val price:Double
)

/**
 * DatabaseView 注解：
 * 该数据类是 sql 的执行结果数据，可用于其他的 dao 操作，用于 class 较为合适，而不是 data class
 */
@DatabaseView(value = "select uname,name from db_user,book where uid = 3 or bookId=3",viewName = "tempBean")
class TempBean{
    var uname=""
    var name =""
    override fun toString(): String {
        return "TempBean(uname='$uname',name = '$name')"
    }
}




