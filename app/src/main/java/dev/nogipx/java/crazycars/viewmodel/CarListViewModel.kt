package dev.nogipx.java.crazycars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.nogipx.java.crazycars.room.dao.CarDao
import dev.nogipx.java.crazycars.room.entity.Car

class CarListViewModel() : ViewModel() {
  
  private val _cars: MutableLiveData<List<Car>> = MutableLiveData()
  val cars: LiveData<List<Car>> get() = _cars
  
  fun retrieveCars(dao: CarDao) {
    _cars.value = dao.getAllCars()
  }
  
}