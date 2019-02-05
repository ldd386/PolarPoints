package ch.example.polarpoints.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = arrayOf(ActivityInDayEntity::class), version = 1)
@TypeConverters(Converters.LocalDateConverter::class)
abstract class PolarPointsDatabase : RoomDatabase() {
    abstract fun activityInDayDao(): ActivityInDayDao
}