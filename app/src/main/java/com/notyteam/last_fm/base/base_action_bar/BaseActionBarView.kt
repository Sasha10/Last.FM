package noty_team.com.masterovik.base.base_action_bar

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.annotation.ColorRes
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.SwitchCompat
import com.jakewharton.rxbinding2.view.RxView
import com.notyteam.last_fm.R
import io.reactivex.Observable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.ab_switch.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.toolbar.*


class BaseActionBarView( override val containerView: View?) : LayoutContainer, ActionBarContract.View {

    override fun showActionBar(show: Boolean) {
        containerView?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showLeftButton(show: Boolean) {
        left_container.visibility = if (show) View.VISIBLE else View.INVISIBLE
        left_container.isEnabled = show

    }


    override fun showRightButton(show: Boolean) {
        right_container.visibility = if (show) View.VISIBLE else View.INVISIBLE
        right_container.isEnabled = show
    }


    override fun getRightContainer(): ViewGroup = right_container

    override fun setupLeftButton(view: View) {
        addViewSafe(left_container, view)
    }

    override fun setupRightButton(view: View) {
        addViewSafe(right_container, view)
    }

    override fun showCenterText(show: Boolean) {
        center_text.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showSpinnerCountry(show: Boolean) {
        spinner_country.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun setupCenterText(res: Int) {
        center_text.setText(res)
    }


    override fun setupColorToollbar(res: Int) {
        ll_toolbar.setBackgroundResource(res)
    }

    override fun setupSpinnerCountry(countryArtist: ArrayList<String>) {
        spinner_country.adapter = ArrayAdapter(containerView!!.context, R.layout.simple_spinner_item, countryArtist)
    }

    override fun getOnSpinnerCountryListener() : AppCompatSpinner = spinner_country


    override fun setupCenterText(string: String) {
        center_text.text = string
    }


    override fun leftButtonAction(): Observable<Any> {
        return RxView.clicks(left_container)
    }


    override fun getLeftButton(): View = left_container

    override fun getRightButton(): SwitchCompat = switch_mode

    override fun rightButtonAction(): Observable<Any> {
        return RxView.clicks(right_container)
    }

    private fun addViewSafe(@Nullable parentNew: ViewGroup?, @Nullable view: View?) {
        parentNew?.let {
            it.removeAllViews()
            parentNew.addView(view)
        }
    }

    override fun resetView(boolean: Boolean) {
        if (boolean) {
            left_container.removeView(left_container.rootView)
            right_container.removeView(right_container.rootView)
        }
    }

    override fun setBackButton(inflater: LayoutInflater): View {
        setupLeftButton(inflater.inflate(R.layout.ab_back, null))
        return getLeftButton()
    }
}