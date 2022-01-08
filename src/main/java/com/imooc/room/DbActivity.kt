package com.imooc.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.lang.StringBuilder

class DbActivity : AppCompatActivity() {


    private var userDao:UserDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)


        val userDatabase = UserDatabase.getInstance(this)
        userDao = userDatabase?.userDao
    }


    /**
     * 插入数据
     */
    fun insert(){

        val users = arrayListOf<DbUser>()
        val sb = StringBuilder()
        for (i in 0..3){
            val user = DbUser()
            user.age = 20 + i
            user.city = "city ${i}"
            user.isSingle = (i%2) == 0
            user.name = "name:${i}"

            user.baby = Child(i,"cname:$i",i+1,i%2)

            userDao?.insertBook(Book(i,"数据 $i",78.3+i))

            sb.append(user.toString()).append("\n")

            users.add(user)

            //  插入数据
//            userDao!!.insertAll(user)
        }

        val jUser = JUser()
        jUser.age = 20
        userDao?.insertJUser(jUser)

        val rowIds = userDao?.insertAll(* users.toTypedArray())
        sb.append("rowId=${rowIds.toString()}")

        Log.d("insert",sb.toString())
        getAll()
    }

    // 获取所有数据
    fun getAll(){

        val sb = StringBuilder()
        val users = userDao!!.getAll()
        users?.forEach { user ->
            sb.append(user.toString())
                .append("\n")
        }
        Log.d("getAll",sb.toString())
    }

    // 删除数据
    fun deleteUser(){
        val user:DbUser? = userDao!!.findByName("name2", 3)
        userDao!!.delete(user)

        Log.d("deleteUser",user.toString())
    }

    // 更新数据
    fun updateUser(){
        val user:DbUser? = userDao!!.findByName("name3", 2)
        user?.age = 12
        user?.name = "jake"
        user?.isSingle = true
        userDao!!.update(user)

        Log.d("updateUser",user.toString())
    }

    // 按照 id 查询
    fun queryId(){
        val userById = userDao!!.getUserById(2)
        if (userById!=null){
            Log.d("queryId",userById.toString())
        }else{
            Log.d("queryId","未查询到指定数据")
        }
    }
}