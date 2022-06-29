package com.heroslender.takeawat.repository.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "menus")
data class MenuEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "half_price") val halfPrice: Double,
    @ColumnInfo(name = "price") val price: Double,
)