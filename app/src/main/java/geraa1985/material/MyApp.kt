package geraa1985.material

import android.app.Application
import androidx.room.Room
import geraa1985.material.ui.listoftasks.AppDB

class MyApp: Application() {

    companion object{
        lateinit var instance: MyApp
    }

    lateinit var db: AppDB

    override fun onCreate() {
        super.onCreate()
        instance = this

        db = Room.databaseBuilder(this, AppDB::class.java, AppDB.NAME_DB).build()
    }


}