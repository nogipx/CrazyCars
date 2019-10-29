package dev.nogipx.java.crazycars.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import dev.nogipx.java.crazycars.R
import dev.nogipx.java.crazycars.room.entity.Car
import kotlinx.android.synthetic.main.viewholder_car_preview.view.*

class CarsListAdapter(var cars: List<Car>)
  : RecyclerView.Adapter<CarsListAdapter.CarViewHolder>() {
  
  lateinit var onClickDelete: (View, Car) -> Unit
  lateinit var onClickEdit: (View, Car) -> Unit
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
    val view = LayoutInflater
      .from(parent.context)
      .inflate(R.layout.viewholder_car_preview, parent, false)
    return CarViewHolder(view)
  }
  
  override fun getItemCount(): Int = cars.size
  
  override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
    val car = cars[position]
    
    holder.carDelete.setOnClickListener {
      onClickDelete(it, car)
      notifyDataSetChanged()
    }
    
    holder.carEdit.setOnClickListener {
      onClickEdit(it, car)
      notifyDataSetChanged()
    }
    
    holder.apply {
      carYear.text = car.creationYear.toString()
      carModel.text = car.model
      carManufacturer.text = car.manufacturer
      carClass.text = car.carClass
      carBodyType.text = car.bodyType
    }
  }
  
  class CarViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val carYear: TextView = view.carYear
    val carModel: TextView = view.carModel
    val carManufacturer: TextView = view.carManufacturer
    val carClass: TextView = view.carClass
    val carBodyType: TextView = view.carBodyType
    val carDelete: MaterialButton = view.carDelete
    val carEdit: MaterialButton = view.carEdit
  }
}