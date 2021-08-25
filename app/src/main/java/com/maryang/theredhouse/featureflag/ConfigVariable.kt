package com.maryang.theredhouse.featureflag

sealed class ConfigVariable<T> {
    abstract val key: String
    abstract val defaultValue: T

    object ShowHouseListItemImageOnRightSide : ConfigVariable<Boolean>() {
        override val key: String = "show_house_list_item_image_on_right_side"
        override val defaultValue: Boolean = false
    }

    object ShowHouseListGrid2Column : ConfigVariable<Boolean>() {
        override val key: String = "show_house_list_grid_2column"
        override val defaultValue: Boolean = false
    }

    object ContactButtonText : ConfigVariable<String>() {
        override val key: String = "contact_button_text"
        override val defaultValue: String = "연락하기"
    }

    object MoveContactButtonBottomToFloating : ConfigVariable<Boolean>() {
        override val key: String = "move_contact_button_bottom_to_floating"
        override val defaultValue: Boolean = false
    }

    fun toPair() = key to defaultValue
}
