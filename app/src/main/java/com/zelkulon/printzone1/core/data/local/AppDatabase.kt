package com.zelkulon.printzone1.core.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.zelkulon.printzone1.core.data.local.dao.OrderDao
import com.zelkulon.printzone1.core.data.local.dao.PrintMediaDao
import com.zelkulon.printzone1.core.data.local.entity.OrderEntity
import com.zelkulon.printzone1.core.data.local.entity.PrintMediaEntity


@Database(entities = [PrintMediaEntity::class, OrderEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun printMediaDao(): PrintMediaDao
    abstract fun orderDao(): OrderDao
}
