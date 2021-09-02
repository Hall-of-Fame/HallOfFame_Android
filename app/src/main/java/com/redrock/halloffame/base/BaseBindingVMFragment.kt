package com.redrock.halloffame.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 * .....
 * @author 985892345
 * @email 2767465918@qq.com
 * @data 2021/6/1
 */
abstract class BaseBindingVMFragment<VM: ViewModel, DB: ViewDataBinding>: BaseOnlyBindingFragment<DB>() {
    protected val viewModel by lazy {
        val viewModelClass = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<VM>
        ViewModelProvider(this).get(viewModelClass)
    }
}