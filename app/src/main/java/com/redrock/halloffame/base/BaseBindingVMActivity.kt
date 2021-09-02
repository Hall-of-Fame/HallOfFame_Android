package com.redrock.halloffame.base

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * .....
 * @author 985892345
 * @email 2767465918@qq.com
 * @data 2021/6/2
 */
abstract class BaseBindingVMActivity<VM: ViewModel, DB: ViewDataBinding>(
    @LayoutRes
    private val layoutId: Int,
    /**
     * 是否锁定竖屏
     */
    isPortraitScreen: Boolean = true,

    /**
     * 是否沉浸式状态栏
     */
    isCancelStatusBar: Boolean = true
) : BaseOnlyBindingActivity<DB>(layoutId, isPortraitScreen, isCancelStatusBar) {
    protected val viewModel by lazy {
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        ViewModelProvider(this).get(viewModelClass)
    }
}