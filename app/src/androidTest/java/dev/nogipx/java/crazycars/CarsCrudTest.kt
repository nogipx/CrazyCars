package dev.nogipx.java.crazycars

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import dev.nogipx.java.crazycars.room.dao.CarDao
import dev.nogipx.java.crazycars.room.db.AppDatabase
import dev.nogipx.java.crazycars.room.entity.Car
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CarsCrudTest {
  
  private val tag = "CardsCrudTest"
  private lateinit var dao: CarDao
  private lateinit var db: AppDatabase
  private val carsSample = arrayOf(
    Car(0, 2009, "CR-V", "Honda", "C","crossover"),
    Car(1, 2005, "RAV4", "Toyota", "C", "crossover"),
    Car(2, 2017, "Model 3", "Tesla", "D", "sedan")
  )
  
  
  @Before
  fun createDb() {
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
  fun insertCarsAndUpdateOne_assertion() {
    dao.insertCars(*carsSample)
    
    val updCar = carsSample[0].apply {
      creationYear = 1990
      carClass = "B"
      Log.d(tag, "[Before update]")
      Log.d(tag, "Local: $this")
      Log.d(tag, "Room: ${dao.getCar(this.carId)}")
    }
    
    dao.updateCars(updCar)
    Log.d(tag, "[After update]")
    Log.d(tag, "Room: ${dao.getCar(updCar.carId)}")
    assertEquals(updCar, dao.getCar(updCar.carId))
  }
  
}