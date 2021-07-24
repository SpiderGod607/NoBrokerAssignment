package com.spidergod.nobrokerassignment.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spidergod.nobrokerassignment.data.local.dao.NoBrokerDao
import com.spidergod.nobrokerassignment.data.local.entity.NoBrokerEntity

@Database(entities = [NoBrokerEntity::class], version = 1)
abstract class NoBrokerDatabase() : RoomDatabase() {
    abstract fun getNoBrokerDao(): NoBrokerDao
}