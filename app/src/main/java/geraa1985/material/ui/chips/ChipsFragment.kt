package geraa1985.material.ui.chips

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
