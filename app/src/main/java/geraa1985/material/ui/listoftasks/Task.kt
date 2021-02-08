package geraa1985.material.ui.listoftasks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String = "",
    val text: String = ""
)