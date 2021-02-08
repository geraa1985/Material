package geraa1985.material.ui.picture

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import geraa1985.material.R
import geraa1985.material.ui.listoftasks.ListActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import kotlinx.android.synthetic.main.bottom_navigation_layout.*
import java.util.concurrent.TimeUnit

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_navigation_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigation_view.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_one -> {

                    //для отработки анимации нажатия на пункт меню
                    Completable
                        .timer(700,TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .andThen {
                            dismiss()
                            val intent = Intent(requireActivity(), ListActivity::class.java)
                            startActivity(intent)
                        }
                        .subscribe()

                }
                R.id.navigation_two -> Toast.makeText(context, "2", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }
}
