package dev.nogipx.java.crazycars.room.dao

import androidx.room.*
import dev.nogipx.java.crazycars.room.entity.FirstRun

@Dao
interface FirstRunDao {
  
  @Query("SELECT isFirstRun FROM FirstRun WHERE record = 0")
  fun isFirstRun() : Boolean
  
  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertFirstRun(record: FirstRun)
  
  @Update
  fun updateFirstRun(record: FirstRun)
}