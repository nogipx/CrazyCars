package dev.nogipx.java.crazycars.adapter

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import dev.nogipx.java.crazycars.ExtraInfo
import dev.nogipx.java.crazycars.R
import dev.nogipx.java.crazycars.room.entity.Car
import kotlinx.android.synthetic.main.viewholder_car_preview.view.*
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation

class CarsListAdapter(var cars: List<Car>)
  : RecyclerView.Adapter<CarsListAdapter.CarViewHolder>() {
  
  var onClickDelete: (View, Car) -> Unit = {_, _ -> }
  var onClickEdit: (View, Car) -> Unit = {_, _ -> }
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
    val view = LayoutInflater
      .from(parent.context)
      .inflate(R.layout.viewholder_car_preview, parent, false)
    return CarViewHolder(view, parent.context)
  }
  
  override fun getItemCount(): Int = cars.size
  
  override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
    val car = cars[position]
  
    holder.apply {
      this.infoContainer.removeAllViews()
      car::class.declaredMemberProperties.reversed().forEach { prop ->
        prop.findAnnotation<ExtraInfo>()?.let { ann ->
          createProperty(ann.title, (prop as KProperty1<Car, String>).get(car))
        }
      }
    }
    
    holder.carDelete.setOnClickListener {
      onClickDelete(it, car)
    }
    
    holder.carEdit.setOnClickListener {
      onClickEdit(it, car)
    }
  }
  
  class CarViewHolder(view: View, val ctx: Context) : RecyclerView.ViewHolder(view) {
    
    val carDelete: MaterialButton = view.carDelete
    val carEdit: MaterialButton = view.carEdit
    
    val infoContainer: TableLayout = view.carInfo
    
    fun createProperty(title: String, value: String) {

      val row = TableRow(ctx).apply {
        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
        gravity = Gravity.CENTER_HORIZONTAL
      }
      
      val titleView = TextView(ctx).apply { text = title }
      row.addView(titleView)
      
      val valueView = TextView(ctx).apply {
        text = value
        gravity = Gravity.END
      }
      row.addView(valueView)
      
      infoContainer.addView(row)
    }
  }
}