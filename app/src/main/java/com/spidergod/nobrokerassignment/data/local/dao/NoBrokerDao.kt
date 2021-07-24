package com.spidergod.nobrokerassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.spidergod.nobrokerassignment.data.local.entity.NoBrokerEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface NoBrokerDao {

    @Query("SELECT * FROM noBrokerTable")
    fun getAllDataInDatabase(): Flow<List<NoBrokerEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataToTheDatabase(data: List<NoBrokerEntity>)

    @Query("DELETE FROM noBrokerTable")
    suspend fun deleteAllDataInDatabase()

}