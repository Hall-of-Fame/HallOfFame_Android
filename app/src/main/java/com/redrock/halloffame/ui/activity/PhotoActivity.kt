package com.redrock.halloffame.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.github.chrisbanes.photoview.PhotoView
import com.ndhzs.slideshow.SlideShow
import com.redrock.halloffame.R
import com.redrock.halloffame.base.BaseActivity
import com.redrock.halloffame.utils.imgFromUr

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/2
 * @time 16:30
 */
class PhotoActivity : BaseActivity() {

    private lateinit var mImgUrls: ArrayList<String>

    companion object {

        private const val INTENT_IMG_URLS = "imgUrls"

        // 加载时或退出时图片显示的位置(如果使用 startActivityForResult(),
        // 则会在共享动画时因回调过慢在图片位置不对应时出现图片闪动问题)
        var SHOW_POSITION = 0
            private set

        fun activityStart(context: Context, imgUrls: ArrayList<String>, showPosition: Int, options: Bundle?) {
            SHOW_POSITION = showPosition
            val intent = Intent(context, PhotoActivity::class.java)
            intent.putStringArrayListExtra(INTENT_IMG_URLS, imgUrls)
            context.startActivity(intent, options)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 降低因使用共享动画进入 activity 后的闪眼情况
        window.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(R.layout.activity_photo)
        setTheme(R.style.Theme_MaterialComponents) // dialog 需要这个主题支持

        initData()
        initView()
    }

    private fun initData() {
        val imgUrls = intent.getStringArrayListExtra(INTENT_IMG_URLS)
        if (imgUrls != null ) {
            mImgUrls = imgUrls
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val tvPosition: TextView = findViewById(R.id.photo_tv_position)

        val slideShow: SlideShow = findViewById(R.id.photo_slideShow)
        slideShow
            .setStartItem(SHOW_POSITION)
            .setPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    //设置图片进度(1/X)
                    tvPosition.post { // TextView 有奇怪的 bug, 改变文字不用 post 就无法改变
                        tvPosition.text = "${position + 1}/${mImgUrls.size}"
                    }
                    SHOW_POSITION = position
                }
            })
            .setViewAdapter(
                getNewView = { context -> PhotoView(context) },
                getItemCount = { mImgUrls.size },
                create = { holder ->
                    holder.view.scaleType= ImageView.ScaleType.CENTER_INSIDE
                    holder.view.setOnPhotoTapListener { _, _, _ ->
                        finishAfterTransition()
                    }
                    holder.view.setOnOutsidePhotoTapListener {
                        finishAfterTransition()
                    }
                    holder.view.setOnLongClickListener {
                        val drawable = holder.view.drawable
                        if (drawable is BitmapDrawable) {
                            val bitmap = drawable.bitmap
                            savePhoto(bitmap, mImgUrls[holder.layoutPosition])
                        }
                        true
                    }
                },
                onBindViewHolder = { view, _, position, _ ->
                    view.imgFromUr(mImgUrls[position])
                }
            )
    }

    private fun savePhoto(bitmap: Bitmap, url: String) {
    }
}