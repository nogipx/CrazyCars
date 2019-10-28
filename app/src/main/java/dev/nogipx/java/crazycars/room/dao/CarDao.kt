package dev.nogipx.java.crazycars.room.dao

import androidx.room.*
import dev.nogipx.java.crazycars.room.entity.Car


@Dao
interface CarDao {
  
  @Insert
  fun insertCars(vararg cars: Car)
  
  @Update
  fun updateCars(vararg cars: Car)
  
  @Delete
  fun deleteCars(vararg cars: Car)
  
  @Query("SELECT COUNT(carId) FROM car")
  fun countCars() : Int
  
  @Query("SELECT * FROM car WHERE carId = :id")
  fun getCar(id: Int) : Car
  
}