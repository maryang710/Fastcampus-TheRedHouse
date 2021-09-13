package com.maryang.theredhouse.event

object EventDefinitions {

    fun openApp() = EventDefinition(
        eventName = "open_app"
    )

    fun signUp() = EventDefinition(
        eventName = "sign_up"
    )

    fun completeUploadHouse() = EventDefinition(
        eventName = "complete__upload_house"
    )

    fun clickContactHouseDetail() = EventDefinition(
        eventName = "click_contact__house_detail"
    )
}
