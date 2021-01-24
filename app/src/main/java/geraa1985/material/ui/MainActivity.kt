package geraa1985.material.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import geraa1985.material.R
import geraa1985.material.ui.picture.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkTheme()
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }

    private fun checkTheme() {
        val sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("Theme A", false)) {
            setTheme(R.style.AppTheme)
        } else {
            setTheme(R.style.AppTheme_Second)
        }
    }
}
