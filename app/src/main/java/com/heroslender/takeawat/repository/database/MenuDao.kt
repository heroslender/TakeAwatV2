package com.heroslender.takeawat.repository.database

import androidx.room.*

@Dao
interface MenuDao {
    @Query("SELECT * FROM menus")
    fun getAll(): List<MenuEntity>

    @Query("SELECT date FROM menus GROUP BY date")
    fun getAllDates(): List<Long>

    @Query("SELECT * FROM menus WHERE id IN (:ids)")
    fun loadAllByIds(ids: IntArray): List<MenuEntity>

    @Query("SELECT * FROM menus WHERE date=:date")
    fun findByDate(date: Long): List<MenuEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(menu: List<MenuEntity>)

    @Delete
    fun delete(menu: MenuEntity)

    @Query("DELETE FROM menus")
    fun delete()

    @Query("DELETE FROM menus WHERE date=:date")
    fun delete(date: Long)
}