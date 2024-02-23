package com.dept.deptepicchallenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dept.deptepicchallenge.data.model.DateLocal

@Dao
interface DatesDao {

    @Query("SELECT * FROM dates")
    fun getAll(): List<DateLocal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createAll(objects: List<DateLocal>)

}