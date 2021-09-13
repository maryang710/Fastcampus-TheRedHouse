package com.maryang.theredhouse.event

object EnterEventDefinitions {

    fun houseList() = EventDefinition(
        eventName = "enter__house_list"
    )

    fun houseDetail() = EventDefinition(
        eventName = "enter__house_detail"
    )

    fun signUp() = EventDefinition(
        eventName = "enter__sign_up"
    )

    fun uploadType() = EventDefinition(
        eventName = "enter__upload_type"
    )

    fun uploadInfo() = EventDefinition(
        eventName = "enter__upload_info"
    )

    fun uploadContact() = EventDefinition(
        eventName = "enter__upload_contact"
    )
}
