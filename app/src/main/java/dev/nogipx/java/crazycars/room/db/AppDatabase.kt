package dev.nogipx.java.crazycars.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.nogipx.java.crazycars.room.dao.CarDao
import dev.nogipx.java.crazycars.room.entity.Car


@Database(entities = arrayOf(Car::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
  
  abstract fun carDao(): CarDao
}