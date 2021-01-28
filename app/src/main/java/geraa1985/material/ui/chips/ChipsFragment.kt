package geraa1985.material.ui.chips

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import geraa1985.material.R
import kotlinx.android.synthetic.main.fragment_chips.*

class ChipsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chips, container, false)
    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireContext().getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        if (sharedPreferences.getBoolean("Theme A", false)) {
            theme_A.isChecked = true
        } else {
            theme_B.isChecked = true
        }

        ValueAnimator.ofFloat(0f, 360f).apply {
            duration = 4000
            addUpdateListener { animation ->
                view.rotation = animation.animatedValue as Float
            }
            repeatCount = 2
        }.start()

        ValueAnimator.ofFloat(0f, -360f).apply {
            duration = 4000
            addUpdateListener { animation ->
                theme_A.rotation = animation.animatedValue as Float
                theme_B.rotation = animation.animatedValue as Float
            }
            repeatCount = 3
        }.start()

        ValueAnimator.ofFloat(0.8f, 1.0f).apply {
            duration = 4000
            addUpdateListener { animation ->
                theme_A.scaleX = animation.animatedValue as Float
                theme_A.scaleY = animation.animatedValue as Float
                theme_B.scaleX = animation.animatedValue as Float
                theme_B.scaleY = animation.animatedValue as Float
            }
            repeatCount = 3
        }.start()

        chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
                when(it.text) {
                    "Theme A" -> {
                        editor.putBoolean("Theme A", true)
                        editor.putBoolean("Theme B", false)
                        editor.apply()
                        activity?.recreate()
                    }
                    "Theme B" -> {
                        editor.putBoolean("Theme B", true)
                        editor.putBoolean("Theme A", false)
                        editor.apply()
                        activity?.recreate()
                    }
                }
            }
        }

        chip_close.setOnCloseIconClickListener {
            Toast.makeText(
                context,
                "Close is Clicked",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
