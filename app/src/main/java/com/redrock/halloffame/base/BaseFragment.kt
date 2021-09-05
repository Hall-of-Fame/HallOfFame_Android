package com.redrock.halloffame.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/5
 * @time 17:49
 */
open class BaseFragment : Fragment() {
    inline fun <T> LiveData<T>.observeNotNull(
        crossinline onChange: (T) -> Unit
    ) = observe(viewLifecycleOwner) {
        it ?: return@observe
        onChange(it)
    }
}