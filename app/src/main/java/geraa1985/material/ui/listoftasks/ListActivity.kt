package geraa1985.material.ui.listoftasks

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import geraa1985.material.MyApp
import geraa1985.material.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity(R.layout.activity_list) {

    private lateinit var adapter: ListOfTasksAdapter

    override fun onStart() {
        super.onStart()

        val list = Single.fromCallable { MyApp.instance.db.taskDAO.getAll() }.subscribeOn(Schedulers.io())
        list.observeOn(AndroidSchedulers.mainThread()).subscribe(
            {
                adapter = ListOfTasksAdapter(it)
                list_of_tasks.layoutManager = LinearLayoutManager(this)
                list_of_tasks.adapter = adapter
                ItemTouchHelper(ItemTouchHelperCallback(adapter))
                    .attachToRecyclerView(list_of_tasks)
            },{
                Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
            }
        )

        fab_add_task.setOnClickListener {
            NewTaskDialog().show(supportFragmentManager, "NEW_TASK_DIALOG")
        }

        fab_back.setOnClickListener {
            onBackPressed()
        }
    }

    fun updateList(newList: List<Task>) {
        adapter.updateList(newList)
    }

}