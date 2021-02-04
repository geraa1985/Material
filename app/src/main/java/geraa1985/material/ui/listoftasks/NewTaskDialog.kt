package geraa1985.material.ui.listoftasks

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import geraa1985.material.MyApp
import geraa1985.material.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialogfragment_new_task.*

class NewTaskDialog: DialogFragment(), View.OnClickListener {

    companion object{
        private const val ID_KEY = "id"
        private const val TITLE_KEY = "title"
        private const val TEXT_KEY = "text"

        fun newInstance(id: Int, title: String, text: String) = NewTaskDialog().apply {
            arguments = Bundle().apply {
                putInt(ID_KEY, id)
                putString(TITLE_KEY, title)
                putString(TEXT_KEY, text)
            }
        }
    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialogfragment_new_task, container)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()

        arguments?.let {
            task_title.setText(it.getString(TITLE_KEY))
            task_text.setText(it.getString(TEXT_KEY))
            new_task_button.text = "save task"
        }

        new_task_button.setOnClickListener(this)
        cancel_button.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            new_task_button.id -> {
                Single.fromCallable {
                    MyApp.instance.db.taskDAO.insert(
                        arguments?.let {
                            Task(
                                id = it.getInt(ID_KEY),
                                title = task_title.text.toString(),
                                text = task_text.text.toString()
                            )
                        } ?: Task(
                            title = task_title.text.toString(),
                            text = task_text.text.toString()
                        )
                    )
                    return@fromCallable MyApp.instance.db.taskDAO.getAll()
                }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({newList ->
                        (requireActivity() as ListActivity).updateList(newList)
                        dismiss()
                    },{
                        Toast.makeText(requireActivity(),it.message,Toast.LENGTH_SHORT).show()
                        dismiss()
                    })
            }
            cancel_button.id -> dismiss()
        }
    }
}