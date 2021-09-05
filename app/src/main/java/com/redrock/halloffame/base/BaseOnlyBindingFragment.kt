package com.redrock.halloffame.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import java.lang.RuntimeException
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * .....
 * @author 985892345
 * @email 2767465918@qq.com
 * @data 2021/6/2
 */
abstract class BaseOnlyBindingFragment<DB: ViewDataBinding> : Fragment() {

    abstract override fun onViewCreated(view: View, savedInstanceState: Bundle?)

    protected lateinit var binding: DB

    @Deprecated("不要重写该方法，请使用 onViewCreated() 代替", ReplaceWith("onViewCreated(view, savedInstanceState)"))
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bindingClass = getBindingClass()
        val method = bindingClass.getMethod("inflate", LayoutInflater::class.java)
        binding = method.invoke(null, requireActivity().layoutInflater) as DB
        binding.lifecycleOwner = this
        return binding.root
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
}