package com.heroslender.takeawat.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MenuEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao
}