package geraa1985.material.ui.listoftasks

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class AppDB: RoomDatabase() {

    abstract val taskDAO: ITaskDAO

    companion object {
        const val NAME_DB = "database.db"
    }

}