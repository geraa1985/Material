package geraa1985.material.ui

import android.animation.Animator
import android.animation.TimeInterpolator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Interpolator
import geraa1985.material.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        image_splash.apply {
            scaleX = 0.1f
            scaleY = 0.1f
        }
        image_splash.animate()
            .scaleX(2.5f)
            .scaleY(2.5f)
            .rotation(720f)
            .setDuration(3000)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) {}

                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationRepeat(animation: Animator?) {}
            })
    }
}