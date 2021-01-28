package geraa1985.material.ui.viewpagersample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import geraa1985.material.R
import kotlinx.android.synthetic.main.activity_view_pager.*

class ViewPagerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)
        view_pager.adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> {
                        first_item.setImageResource(R.drawable.swipe_indicator_active)
                        second_item.setImageResource(R.drawable.swipe_indicator_passive)
                        third_item.setImageResource(R.drawable.swipe_indicator_passive)
                    }
                    1 -> {
                        first_item.setImageResource(R.drawable.swipe_indicator_passive)
                        second_item.setImageResource(R.drawable.swipe_indicator_active)
                        third_item.setImageResource(R.drawable.swipe_indicator_passive)
                    }
                    2 -> {
                        first_item.setImageResource(R.drawable.swipe_indicator_passive)
                        second_item.setImageResource(R.drawable.swipe_indicator_passive)
                        third_item.setImageResource(R.drawable.swipe_indicator_active)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })
    }

}