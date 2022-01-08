package com.imooc.room

import androidx.room.*

/**
 * @Author lihl
 * @Date 2022/1/8 6:49
 * @Email 1601796593@qq.com
 *
 * 数据表 Dao 层，接口 ，负责数据的操作
 */
@Dao
interface UserDao {

    // 查询所有数据
    @Query(value = "select * from db_user")
    fun getAll():List<DbUser?>?

    // 查询 uid 在 ids 集合中的所有数据
    @Query(value = "select * from db_user where uid in (:ids)")
    fun loadAllByIds(vararg ids:IntArray?):List<DbUser?>?

    // 查询 uid 为 id 的数据
    @Query(value = "select * from db_user where uid = :id")
    fun getUserById(id:Int):DbUser?

    // 查询 name，age 匹配的数据
    @Query(value = "select * from db_user where uname like :name and age = :age limit 1")
    fun findByName(name:String?,age:Int):DbUser?

    // 插入多个数据
    @Insert()
    fun insertAll(vararg users:DbUser?)

    // 删除数据
    @Delete()
    fun delete(user:DbUser?)

    // 更新数据 ，如果出现冲突，则使用替换策略
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user:DbUser?)


    // 插入 JUser 数据
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJUser(user:JUser)

    // 查询 JUser 表列表
    @Query("select * from tb_juser")
    fun queryJuser():List<JUser>


    // 插入 Book
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(book:Book?)

    // 查询 Book
    @Query("select * from book")
    fun getBooks():List<Book>

    @Query("select * from tempBean")
    fun queryUserBook():List<TempBean>

}