package com.spidergod.nobrokerassignment.repository

import androidx.room.withTransaction
import com.spidergod.nobrokerassignment.data.dto_to_entity_converter.NoBrokerDtoToNoBrokerEntity.noBrokerDtoToNoBrokerEntity
import com.spidergod.nobrokerassignment.data.local.database.NoBrokerDatabase
import com.spidergod.nobrokerassignment.data.remote.api.NoBrokerApi
import com.spidergod.nobrokerassignment.util.networkBoundResource
import javax.inject.Inject

class NoBrokerRepository @Inject constructor(
    private val database: NoBrokerDatabase,
    private val api: NoBrokerApi
) {
    private val dao = database.getNoBrokerDao()

    fun getDataFromNoBroker() = networkBoundResource(
        query = {
            dao.getAllDataInDatabase()
        },
        fetch = {
            noBrokerDtoToNoBrokerEntity(api.getDataFromNoBrokerApi())
        },
        saveFetchResult = { data ->
            database.withTransaction {
                dao.deleteAllDataInDatabase()
                dao.insertDataToTheDatabase(data = data)
            }
        }
    )

}