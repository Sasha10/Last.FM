package com.notyteam.last_fm.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.wifi.WifiManager
import android.os.Build
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target


fun setMobileDataEnabled(activity: Activity,context: Context, enabled: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val panelIntent = Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY)
        activity.startActivityForResult(panelIntent, 545)
    }
    else {
        (context?.getSystemService(Context.WIFI_SERVICE) as? WifiManager)?.apply { isWifiEnabled = enabled }
    }
}
fun ImageView.loadImage(
    url: String,
    progress: View? = null,
    onSuccess: (resource: Drawable) -> Unit = {},
    onError: () -> Unit = {}
) {
    if (url != "") {

        progress?.visible()
        val options = RequestOptions()
                .encodeQuality(70)
                .centerInside()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        Glide.with(this.context)
                .load(url)
                .apply(options)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        progress?.gone()
                        onError()
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        progress?.gone()
                        onSuccess(resource)
                        return false
                    }
                })
                .into(this)
    }
}

