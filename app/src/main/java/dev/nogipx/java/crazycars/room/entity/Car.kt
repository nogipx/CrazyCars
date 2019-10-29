package dev.nogipx.java.crazycars.room.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.nogipx.java.crazycars.ExtraInfo
import java.util.UUID


@Entity
data class Car (

  @ColumnInfo(name = "carReleaseYear")
  @ExtraInfo(title = "Год выпуска")
  var carReleaseYear: String,
  
  @ColumnInfo(name = "carModel")
  @ExtraInfo(title = "Модель")
  var carModel: String,
  
  @ColumnInfo(name = "carManufacturer")
  @ExtraInfo(title = "Производитель")
  var carManufacturer: String,
  
  @ColumnInfo(name = "carClass")
  @ExtraInfo(title = "Класс")
  var carClass: String,
  
  @ColumnInfo(name = "carBodyType")
  @ExtraInfo(title = "Тип кузова")
  var carBodyType: String

) {
  @NonNull @PrimaryKey var uuid = UUID.randomUUID().toString()
}