package com.redrock.halloffame.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.RuntimeException
import java.lang.reflect.ParameterizedType

/**
 * .....
 * @author 985892345
 * @email 2767465918@qq.com
 * @data 2021/6/1
 */
abstract class BaseBindingVmFragment<VM: ViewModel, DB: ViewDataBinding>: BaseOnlyBindingFragment<DB>() {
    protected val viewModel by lazy {
        val viewModelClass = getViewModelClass()
        ViewModelProvider(this).get(viewModelClass)
    }

    private fun getViewModelClass(): Class<VM> {
        val typeArguments = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        for (type in typeArguments) {
            if (type is Class<*> && ViewModel::class.java.isAssignableFrom(type)) {
                return type as Class<VM>
            }
        }
        throw RuntimeException()
    }

    inline fun <T> LiveData<T>.observeNotNull(
        crossinline onChange: (T) -> Unit
    ) = observe(viewLifecycleOwner) {
        it ?: return@observe
        onChange(it)
    }
}