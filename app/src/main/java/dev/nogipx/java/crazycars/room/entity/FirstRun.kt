package dev.nogipx.java.crazycars.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FirstRun(
  @PrimaryKey var record: Int = 0,
  @ColumnInfo(name = "isFirstRun") var isFirstRun: Boolean = true
)