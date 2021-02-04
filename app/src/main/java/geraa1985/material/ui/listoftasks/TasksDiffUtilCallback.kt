package geraa1985.material.ui.listoftasks

import androidx.recyclerview.widget.DiffUtil

class TasksDiffUtilCallback(
    private val oldList: List<Task>,
    private val newList: List<Task>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldUser = oldList[oldItemPosition]
        val newUser = newList[newItemPosition]
        return oldUser.title == newUser.title && oldUser.text == newUser.text
    }
}