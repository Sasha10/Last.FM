package noty_team.com.masterovik.base.base_action_bar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatSpinner
import androidx.appcompat.widget.SwitchCompat
import io.reactivex.Observable

interface ActionBarContract {
    interface View {
        fun showActionBar(show: Boolean)

        fun showLeftButton(show: Boolean)
        fun setupLeftButton(view: android.view.View)


        fun showRightButton(show: Boolean)
        fun setupRightButton(view: android.view.View)

        fun showCenterText(show: Boolean)
        fun setupCenterText(res: Int)
        fun setupColorToollbar(res: Int)
        fun setupCenterText(string: String)

        fun showSpinnerCountry(show: Boolean)
        fun setupSpinnerCountry (countryArtist: ArrayList<String>)
        fun getOnSpinnerCountryListener () : AppCompatSpinner


        fun leftButtonAction(): Observable<Any>
        fun rightButtonAction(): Observable<Any>
        fun getLeftButton(): android.view.View
        fun getRightButton(): SwitchCompat

        fun resetView(boolean: Boolean)
        fun getRightContainer(): ViewGroup

        fun setBackButton(inflater: LayoutInflater): android.view.View
    }

    interface Presenter {

        fun setupView()
        fun setupActions()
        fun leftButtonAction()
        fun rightButtonAction()
        fun dispose()
        fun start()
        fun stop()
    }
}