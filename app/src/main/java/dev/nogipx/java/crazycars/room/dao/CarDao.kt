package dev.nogipx.java.crazycars.room.dao

import androidx.room.*
import dev.nogipx.java.crazycars.room.entity.Car


@Dao
abstract class CarDao {
  
  @Insert
  abstract fun insertCars(vararg cars: Car)
  
  @Update
  abstract fun updateCars(vararg cars: Car)
  
  @Delete
  abstract fun deleteCars(vararg cars: Car)
  
  fun deleteCar(carUuid: String) {
    deleteCars(getCar(carUuid))
  }
  
  @Query("SELECT * FROM car")
  abstract fun getAllCars() : List<Car>
  
  @Query("SELECT COUNT(uuid) FROM car")
  abstract fun countCars() : Int
  
  @Query("SELECT * FROM car WHERE uuid = :uuid")
  abstract fun getCar(uuid: String) : Car
  
}