package com.maryang.theredhouse.data.mock

import com.maryang.theredhouse.R
import com.maryang.theredhouse.domain.entity.HouseEntity
import com.maryang.theredhouse.domain.entity.HouseType

object MockDataProvider {
    fun houseList() =
        listOf(
            HouseEntity(
                id = 1,
                name = "타워펠리스",
                imageResId = R.drawable.tower,
                price = 4200000000,
                address = "대치동",
                contact = "01089665832",
                type = HouseType.APART
            ),
            HouseEntity(
                id = 2,
                name = "주공",
                imageResId = R.drawable.jugong,
                price = 2700000000,
                address = "개포동",
                contact = "01089665832",
                type = HouseType.APART
            ),
            HouseEntity(
                id = 3,
                name = "자이",
                imageResId = R.drawable.xi,
                price = 2300000000,
                address = "방배동",
                contact = "01089665832",
                type = HouseType.APART
            ),
            HouseEntity(
                id = 4,
                name = "프루지오",
                imageResId = R.drawable.pruzio,
                price = 1000000000,
                address = "신림동",
                contact = "01089665832",
                type = HouseType.APART
            ),
            HouseEntity(
                id = 5,
                name = "아이파크",
                imageResId = R.drawable.ipark,
                price = 1400000000,
                address = "목동",
                contact = "01089665832",
                type = HouseType.APART
            ),
            HouseEntity(
                id = 6,
                name = "허브빌라",
                imageResId = R.drawable.hubvil,
                price = 490000000,
                address = "망원동",
                contact = "01089665832",
                type = HouseType.VILLA
            ),
            HouseEntity(
                id = 7,
                name = "그린빌라",
                imageResId = R.drawable.greenvil,
                price = 298000000,
                address = "합정동",
                contact = "01089665832",
                type = HouseType.VILLA
            ),
            HouseEntity(
                id = 8,
                name = "아트빌라",
                imageResId = R.drawable.artvil,
                price = 1100000000,
                address = "천호동",
                contact = "01089665832",
                type = HouseType.VILLA
            ),
            HouseEntity(
                id = 9,
                name = "더 리버빌",
                imageResId = R.drawable.rivervil,
                price = 550000000,
                address = "신길동",
                contact = "01089665832",
                type = HouseType.VILLA
            ),
            HouseEntity(
                id = 10,
                name = "롯데캐슬",
                imageResId = R.drawable.castle,
                price = 500000000,
                address = "독산동",
                contact = "01089665832",
                type = HouseType.APART
            ),
            HouseEntity(
                id = 11,
                name = "더 퍼스트",
                imageResId = R.drawable.first,
                price = 1500000000,
                address = "암사동",
                contact = "01089665832",
                type = HouseType.APART
            ),
            HouseEntity(
                id = 12,
                name = "레미안",
                imageResId = R.drawable.remian,
                price = 2000000000,
                address = "합정동",
                contact = "01089665832",
                type = HouseType.APART
            ),
        )
}
