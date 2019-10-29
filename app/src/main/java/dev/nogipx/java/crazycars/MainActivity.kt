package dev.nogipx.java.crazycars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import dev.nogipx.java.crazycars.fragment.CarsListFragment
import dev.nogipx.java.crazycars.room.AppDatabase
import dev.nogipx.java.crazycars.room.dao.CarDao
import dev.nogipx.java.crazycars.room.entity.Car
import dev.nogipx.java.crazycars.room.entity.FirstRun

class MainActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
  
    val db = Room.databaseBuilder(
      applicationContext,
      AppDatabase::class.java, "crazy_cars_5")
      .allowMainThreadQueries()
      .build()
  
    db.firstRunDao().processFirstRun(FirstRun(0, true))
    if (db.firstRunDao().isFirstRun()) {
      fillSamples(db.carDao())
      if (db.carDao().countCars() > 0)
        db.firstRunDao().processFirstRun(FirstRun(0, false))
    }
  
    val listFragment = CarsListFragment(db)
    supportFragmentManager.beginTransaction()
      .add(R.id.fragmentContainer, listFragment)
      .commit()
  }
  
  private fun fillSamples(dao: CarDao) {
    val sample = arrayOf(
      Car("2009", "CR-V", "Honda", "C","crossover"),
      Car("2005", "RAV4", "Toyota", "C", "crossover"),
      Car("2017", "Model 3", "Tesla", "D", "sedan")
    )
    sample.copyOfRange(dao.countCars(), 3).forEach { dao.insertCars(it) }
  }
}
