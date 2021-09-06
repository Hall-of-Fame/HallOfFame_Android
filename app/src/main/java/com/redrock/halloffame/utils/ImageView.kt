package com.redrock.halloffame.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.redrock.halloffame.R
import com.redrock.halloffame.base.BaseClient

/**
 *@author 985892345
 *@email 2767465918@qq.com
 *@date 2021/5/25
 *@description
 */
@BindingAdapter("imgFromUrl")
fun ImageView.imgFromUr(url: String?) {
    val url2 = if (url?.startsWith("http") == false)  BaseClient.baseUrl + url else url
    Glide
        .with(this)
        .load(url2)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.ic_default_img)
                .error(R.drawable.ic_default_img)
        )
        .into(this)
}
