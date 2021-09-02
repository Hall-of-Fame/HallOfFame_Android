package com.redrock.halloffame.base

import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import java.lang.reflect.ParameterizedType

/**
 * .....
 * @author 985892345
 * @email 2767465918@qq.com
 * @data 2021/6/2
 */
abstract class BaseOnlyBindingActivity<DB: ViewDataBinding>(
    /**
     * 是否锁定竖屏
     */
    isPortraitScreen: Boolean = true,

    /**
     * 是否沉浸式状态栏
     */
    isCancelStatusBar: Boolean = true
) : BaseActivity(isPortraitScreen, isCancelStatusBar) {

    protected val binding: DB by lazy {
        val bindingClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<DB>
        val method = bindingClass.getMethod("inflate", LayoutInflater::class.java)
        val binding = method.invoke(null, layoutInflater) as DB
        binding.lifecycleOwner = this
        binding
    }
}