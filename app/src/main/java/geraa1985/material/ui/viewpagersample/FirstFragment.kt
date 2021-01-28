package geraa1985.material.ui.viewpagersample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.api.load
import geraa1985.material.R
import geraa1985.material.ui.picture.PictureOfTheDayData
import geraa1985.material.ui.picture.PictureOfTheDayViewModel
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : Fragment() {

    val date = "2020-01-25"

    private val viewModel: PictureOfTheDayViewModel by lazy {
        PictureOfTheDayViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getData(date)
            .observe(viewLifecycleOwner, { renderData(it) })
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun renderData(data: PictureOfTheDayData) {
        when (data) {
            is PictureOfTheDayData.Success -> {
                val serverResponseData = data.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
                    toast("Link is empty")
                } else {
                    //showSuccess()
                    if (serverResponseData.mediaType == "image") {
                        image_view.apply {
                            visibility = View.VISIBLE
                            load(url) {
                                lifecycle(this@FirstFragment)
                                error(R.drawable.ic_load_error_vector)
                                placeholder(R.drawable.ic_no_photo_vector)
                            }
                        }
                    } else {
                        video_view.apply {
                            visibility = View.VISIBLE
                            clearHistory()
                            settings.javaScriptEnabled = true
                            settings.javaScriptCanOpenWindowsAutomatically = true
                            loadUrl(url)
                        }
                    }
                    tv_date.text = date
                    explanation.text = serverResponseData.explanation
                }
            }
            is PictureOfTheDayData.Loading -> {
                //showLoading()
            }
            is PictureOfTheDayData.Error -> {
                //showError(data.error.message)
                toast(data.error.message)
            }
        }
    }

    private fun Fragment.toast(string: String?) {
        Toast.makeText(context, string, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.BOTTOM, 0, 250)
            show()
        }
    }
}