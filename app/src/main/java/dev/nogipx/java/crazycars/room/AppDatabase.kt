package dev.nogipx.java.crazycars.room

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.nogipx.java.crazycars.room.dao.CarDao
import dev.nogipx.java.crazycars.room.dao.FirstRunDao
import dev.nogipx.java.crazycars.room.entity.Car
import dev.nogipx.java.crazycars.room.entity.FirstRun


@Database(entities =
  arrayOf(Car::class, FirstRun::class),
  version = 1)
abstract class AppDatabase: RoomDatabase() {
  
  abstract fun carDao(): CarDao
  abstract fun firstRunDao(): FirstRunDao
}