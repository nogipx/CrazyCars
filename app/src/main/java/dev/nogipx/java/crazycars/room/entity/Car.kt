package dev.nogipx.java.crazycars.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Car (
  @PrimaryKey val carId: Int,
  @ColumnInfo(name = "creation_year") var creationYear: Int,
  @ColumnInfo(name = "model") var model: String,
  @ColumnInfo(name = "manufacturer") var manufacturer: String,
  @ColumnInfo(name = "car_class") var carClass: String,
  @ColumnInfo(name = "body_type") var bodyType: String
)