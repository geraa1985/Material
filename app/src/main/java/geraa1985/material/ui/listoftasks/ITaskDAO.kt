package geraa1985.material.ui.listoftasks

import androidx.room.*

@Dao
interface ITaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Query("DELETE FROM Task WHERE id = :id")
    fun delete(id: Int)

    @Update
    fun update(task: Task)

    @Query("SELECT * FROM Task")
    fun getAll(): MutableList<Task>

}