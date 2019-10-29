package dev.nogipx.java.crazycars.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.nogipx.java.crazycars.room.entity.FirstRun

@Dao
interface FirstRunDao {
  
  @Query("SELECT isFirstRun FROM FirstRun WHERE record = 0")
  fun isFirstRun() : Boolean
  
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun processFirstRun(record: FirstRun)
}