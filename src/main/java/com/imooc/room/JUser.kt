package com.imooc.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author lihl
 * @Date 2022/1/8 8:11
 * @Email 1601796593@qq.com
 */

@Entity(tableName = "tb_juser")
class JUser {

    @PrimaryKey(autoGenerate = true)
    var jId:Int = 0

    var age:Int = 0

    override fun toString(): String {
        return "JUser{"+
                "jId="+jId+
                ",age="+age
    }
}