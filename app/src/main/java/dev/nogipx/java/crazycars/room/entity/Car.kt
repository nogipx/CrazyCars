package dev.nogipx.java.crazycars.room.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.nogipx.java.crazycars.ExtraInfo
import java.util.UUID
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation


@Entity
data class Car (

  @ColumnInfo(name = "carReleaseYear")
  @ExtraInfo(title = "Год выпуска")
  var carReleaseYear: String = "",
  
  @ColumnInfo(name = "carModel")
  @ExtraInfo(title = "Модель")
  var carModel: String = "",
  
  @ColumnInfo(name = "carManufacturer")
  @ExtraInfo(title = "Производитель")
  var carManufacturer: String = "",
  
  @ColumnInfo(name = "carClass")
  @ExtraInfo(title = "Класс")
  var carClass: String = "",
  
  @ColumnInfo(name = "carBodyType")
  @ExtraInfo(title = "Тип кузова")
  var carBodyType: String = ""

) {
  @NonNull @PrimaryKey var uuid = UUID.randomUUID().toString()
  
  fun isEmpty() : Boolean {
    return this::class.declaredMemberProperties
      .filter { it.findAnnotation<ExtraInfo>() != null }
      .filter { (it as KProperty1<Car, String>).get(this).isNotEmpty() }
      .count() == 0
  }
  
  fun isNotEmpty() : Boolean = !isEmpty()
}