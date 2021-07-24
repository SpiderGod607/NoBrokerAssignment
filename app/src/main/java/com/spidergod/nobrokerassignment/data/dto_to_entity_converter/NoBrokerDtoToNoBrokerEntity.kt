package com.spidergod.nobrokerassignment.data.dto_to_entity_converter

import com.spidergod.nobrokerassignment.data.local.entity.NoBrokerEntity
import com.spidergod.nobrokerassignment.data.remote.dto.no_broker_response.NoBrokerResponseDto

object NoBrokerDtoToNoBrokerEntity {

    fun noBrokerDtoToNoBrokerEntity(data: NoBrokerResponseDto): List<NoBrokerEntity> {
        return data.map { noBrokerResponseDtoItem ->
            NoBrokerEntity(
                image = noBrokerResponseDtoItem.image ?: "",
                title = noBrokerResponseDtoItem.title ?: "",
                subTitle = noBrokerResponseDtoItem.subTitle ?: ""
            )
        }
    }

}