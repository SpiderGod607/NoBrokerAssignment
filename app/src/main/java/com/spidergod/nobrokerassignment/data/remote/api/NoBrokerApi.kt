package com.spidergod.nobrokerassignment.data.remote.api

import com.spidergod.nobrokerassignment.data.remote.dto.no_broker_response.NoBrokerResponseDto
import retrofit2.http.GET

interface NoBrokerApi {

    @GET("/b/60fa8fefa917050205ce5470")
    suspend fun getDataFromNoBrokerApi(): NoBrokerResponseDto

}