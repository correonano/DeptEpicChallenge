package com.dept.deptepicchallenge.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dept.deptepicchallenge.data.model.EpicDetailLocal

@Dao
interface EpicDetailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createAll(objects: List<EpicDetailLocal>)

    @Query("SELECT * FROM details WHERE day LIKE :date")
    fun getDetails(date: String) : List<EpicDetailLocal>?

}