package com.dept.deptepicchallenge.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dept.deptepicchallenge.data.model.DateLocal
import com.dept.deptepicchallenge.data.model.EpicDetailLocal


@Database(entities = [DateLocal::class, EpicDetailLocal::class], version = 1)
abstract class EpicDB : RoomDatabase() {
    abstract fun datesDao(): DatesDao
    abstract fun epicDetailsDao(): EpicDetailDao
}