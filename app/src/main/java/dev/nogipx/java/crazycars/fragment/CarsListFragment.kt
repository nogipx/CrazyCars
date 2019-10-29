package dev.nogipx.java.crazycars.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import dev.nogipx.java.crazycars.R
import dev.nogipx.java.crazycars.adapter.CarsListAdapter
import dev.nogipx.java.crazycars.preference.CarPreferenceFragment
import dev.nogipx.java.crazycars.room.AppDatabase
import dev.nogipx.java.crazycars.room.dao.CarDao
import dev.nogipx.java.crazycars.room.entity.Car
import dev.nogipx.java.crazycars.viewmodel.CarListViewModel
import kotlinx.android.synthetic.main.fragment_cars_list.view.*


class CarsListFragment(private val db: AppDatabase): Fragment() {
  
  private lateinit var dao: CarDao
  private lateinit var viewModel: CarListViewModel
  private lateinit var mAdapter: CarsListAdapter
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    dao = db.carDao()
    viewModel = ViewModelProviders.of(this)[CarListViewModel::class.java]
  }
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_cars_list, container, false)
    
    // First retrieve data
    viewModel.updateCars(dao.getAllCars())
    
    mAdapter = CarsListAdapter(viewModel.cars.value!!).apply {
      
      onClickDelete = { view, car ->
        dao.deleteCar(car.uuid)
        viewModel.updateCars(dao.getAllCars())
      }
      
      onClickEdit = { view, car ->
        val carPreference = CarPreferenceFragment(car).apply {
          onPauseFun = { car ->
            dao.insertCars(car)
            viewModel.updateCars(dao.getAllCars())
          }
        }
        fragmentManager!!.beginTransaction()
          .replace(R.id.fragmentContainer, carPreference)
          .addToBackStack("editCar")
          .commit()
      }
    }
    
    // New record creation
    view.carNew.setOnClickListener {
      val carPreference = CarPreferenceFragment(Car()).apply {
        onPauseFun = { car ->
          dao.insertCars(car)
          viewModel.updateCars(dao.getAllCars())
        }
      }
      
      fragmentManager!!.beginTransaction()
        .replace(R.id.fragmentContainer, carPreference)
        .addToBackStack("editCar")
        .commit()
    }
    
    // Handle cars set changes
    viewModel.cars.observe(this, Observer { cars ->
      cars.forEach { dao.updateCars(it) }
      mAdapter.cars = cars
      mAdapter.notifyDataSetChanged()
    })
    
    // Setup recycler view
    view.carsList.apply {
      adapter = mAdapter
      layoutManager = LinearLayoutManager(activity)
    }
    
    return view
  }
}
