package com.heroslender.takeawat.repository.database

class MenuDaoFake : MenuDao {
    override fun getAll(): List<MenuEntity> {
        return emptyList()
    }

    override fun getAllDates(): List<Long> {
        return emptyList()
    }

    override fun loadAllByIds(ids: IntArray): List<MenuEntity> {
        return emptyList()
    }

    override fun findByDate(date: Long): List<MenuEntity> {
        return emptyList()
    }

    override fun insertAll(vararg menu: MenuEntity) {
    }

    override fun delete(menu: MenuEntity) {
    }
}