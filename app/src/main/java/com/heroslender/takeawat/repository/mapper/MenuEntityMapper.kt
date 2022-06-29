package com.heroslender.takeawat.repository.mapper

import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.repository.database.MenuEntity
import java.util.*

fun MenuEntity.toMenu(): Menu = Menu(
    id = id,
    date = Date(date),
    name = name,
    price = price,
    halfPrice = halfPrice,
    description = description ?: "",
)

fun Menu.toMenuEntity(): MenuEntity = MenuEntity(
    id = id,
    date = date.time,
    name = name,
    description = description,
    halfPrice = halfPrice,
    price = price
)