package ch.example.polarpoints

import android.app.Application
import android.util.Log
import androidx.room.Room
import ch.example.polarpoints.api.PolarApi
import ch.example.polarpoints.persistence.PolarPointsDatabase

class PolarpointsApplication : Application() {

    var polarApi : PolarApi? = null
    var db : PolarPointsDatabase? = null

    override fun onCreate() {
        super.onCreate()
        Log.i("PolarpointsApplication", "=== Application created ===")
        db = Room.databaseBuilder(
            applicationContext,
            PolarPointsDatabase::class.java, "database-polar_points"
        ).build()
        Log.i("PolarpointsApplication", "PolarPointsDatabase initializated")

    }

}