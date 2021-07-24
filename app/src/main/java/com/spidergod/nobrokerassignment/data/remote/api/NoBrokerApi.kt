package com.spidergod.nobrokerassignment.data.remote.api

import com.spidergod.nobrokerassignment.data.remote.dto.no_broker_response.NoBrokerResponseDto
import retrofit2.http.GET

interface NoBrokerApi {

    @GET("357d2598408a8fd58941aec65578266d/raw/5acf1f859190de47e15dfb6c68796c7492e8a9ce/nobroker.json")
    suspend fun getDataFromNoBrokerApi(): NoBrokerResponseDto

}