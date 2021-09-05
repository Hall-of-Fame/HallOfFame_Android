package com.redrock.halloffame.base

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import java.lang.RuntimeException
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

    /**
     * 用于在调用 [setContentView] 之前的方法, 可用于设置一些主题或窗口的东西, 放这里不会报错
     */
    open fun onSetContentViewBefore() {}

    protected val binding: DB by lazy {
        val bindingClass = getBindingClass()
        val method = bindingClass.getMethod("inflate", LayoutInflater::class.java)
        val binding = method.invoke(null, layoutInflater) as DB
        binding.lifecycleOwner = this
        binding
    }

    private fun getBindingClass(): Class<DB> {
        val typeArguments = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        for (type in typeArguments) {
            if (type is Class<*> && ViewDataBinding::class.java.isAssignableFrom(type)) {
                return type as Class<DB>
            }
        }
        throw RuntimeException()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onSetContentViewBefore()
        setContentView(binding.root)
    }
}