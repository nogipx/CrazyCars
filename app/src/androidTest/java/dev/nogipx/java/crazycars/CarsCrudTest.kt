package dev.nogipx.java.crazycars

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import dev.nogipx.java.crazycars.room.dao.CarDao
import dev.nogipx.java.crazycars.room.AppDatabase
import dev.nogipx.java.crazycars.room.entity.Car
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class CarsCrudTest {
  
  private lateinit var dao: CarDao
  private lateinit var db: AppDatabase
  private val carsSample = arrayOf(
    Car(2009, "CR-V", "Honda", "C","crossover"),
    Car(2005, "RAV4", "Toyota", "C", "crossover"),
    Car(2017, "Model 3", "Tesla", "D", "sedan")
  )
  
  
  @Before
  fun setUp() {
    val ctx = ApplicationProvider.getApplicationContext<Context>()
    db = Room.inMemoryDatabaseBuilder(
      ctx, AppDatabase::class.java)
      .build()
    
    dao = db.carDao()
  }
  
  @Test
  fun insertDeleteCars_assertCount() {
    dao.insertCars(*carsSample)
    assertEquals(3, dao.countCars())
    
    dao.deleteCars(*carsSample)
    assertEquals(0, dao.countCars())
  }
  
  @Test
  fun insertCarsAndUpdateOne() {
    dao.insertCars(*carsSample)
    
    val updCar = carsSample[0].apply {
      creationYear = 1990
      carClass = "B"
    }
    
    dao.updateCars(updCar)
    assertEquals(updCar, dao.getCar(updCar.uuid))
    
    dao.deleteCars(*carsSample, updCar)
  }
  
  @Test
  fun deleteCarById() {
    dao.insertCars(carsSample[0])
    assertEquals(1 , dao.countCars())
  
    dao.deleteCar(carsSample[0].uuid)
    assertEquals(0 , dao.countCars())
  }
  
}