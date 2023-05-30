package com.abhiram.thorak.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abhiram.thorak.ui.theme.IconShapes

@Entity(tableName = "icon_shape")
data class ShapeIcon(
    @PrimaryKey(autoGenerate = false)val id : Int=1
)
