package geraa1985.material.ui.listoftasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import geraa1985.material.MyApp
import geraa1985.material.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialogfragment_new_task.*
import kotlinx.android.synthetic.main.fragment_chips.*
import kotlinx.android.synthetic.main.item_task.view.*

class ListOfTasksAdapter(private var tasks: MutableList<Task>) :
    RecyclerView.Adapter<ListOfTasksAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.rv_tasks_anim)
        holder.itemView.setOnClickListener {
            NewTaskDialog.newInstance(
                tasks[position].id,
                tasks[position].title,
                tasks[position].text
            ).show((it.context as ListActivity).supportFragmentManager, "EDIT_TASK_DIALOG")
        }
    }

    override fun getItemCount(): Int = tasks.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            itemView.task_title.text = task.title
            itemView.task_text.text = task.text
            itemView.icon_delete.setOnClickListener {
                Single.fromCallable {
                    MyApp.instance.db.taskDAO.delete(task.id)
                    return@fromCallable MyApp.instance.db.taskDAO.getAll()
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ newList ->
                        updateList(newList)
                    }, {
                        Toast.makeText(itemView.context, it.message, Toast.LENGTH_SHORT).show()
                    })
            }
        }

        fun onItemSelected() {
            itemView.animate()
                .alpha(0.7f)
                .scaleX(1.1f)
                .scaleY(1.1f)
        }

        fun onItemClear() {
            itemView.animate()
                .alpha(1.0f)
                .scaleX(1.0f)
                .scaleY(1.0f)
        }
    }

    fun updateList(newList: List<Task>) {
        val taskDiffUtilCallback = TasksDiffUtilCallback(tasks, newList)
        val taskDiffResult = DiffUtil.calculateDiff(taskDiffUtilCallback)
        tasks.clear()
        tasks.addAll(newList)
        taskDiffResult.dispatchUpdatesTo(this)
    }

    fun onItemMove(fromPosition: Int, toPosition: Int) {
        tasks.removeAt(fromPosition).apply {
            tasks.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    fun onItemDismiss(position: Int) {
        tasks.removeAt(position)
        notifyItemRemoved(position)
    }
}