package dev.nogipx.java.crazycars.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.nogipx.java.crazycars.room.dao.CarDao
import dev.nogipx.java.crazycars.room.entity.Car

class CarEditViewModel: ViewModel() {
  
  private val _car: MutableLiveData<Car> = MutableLiveData()
  val car: LiveData<Car> get() = _car
  
  fun selectCar(dao: CarDao, uuid: String) {
    _car.value = dao.getCar(uuid)
  }
}