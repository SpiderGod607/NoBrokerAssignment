package com.spidergod.nobrokerassignment.repository

import com.spidergod.nobrokerassignment.data.local.database.NoBrokerDatabase
import com.spidergod.nobrokerassignment.data.remote.api.NoBrokerApi
import javax.inject.Inject

class NoBrokerRepository @Inject constructor(
    database: NoBrokerDatabase,
    api: NoBrokerApi
) {



}