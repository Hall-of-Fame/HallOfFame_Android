package com.redrock.halloffame.utils

import androidx.lifecycle.*

/**
 * 用协程请求网络数据
 * @author 985892345
 * @email 2767465918@qq.com
 * @date 2021/6/1
 */
fun <X, Y> switchMap(
    source: LiveData<X>,
    switchMapFunction: (source: X) -> Y
) = Transformations.switchMap(source) {
    MutableLiveData(switchMapFunction(it))
}

fun <X, Y> ViewModel.switchMapLiveDataLaunch(
    source: LiveData<X>,
    onError: (() -> Unit)? = null,
    block: suspend (source: X) -> Y
) = Transformations.switchMap(source) {
    liveData(viewModelScope.coroutineContext) {
        val result = runCatching { block(it) }.onFailure { onError?.invoke() }
        emit(result.getOrThrow())
    }
}

fun <T> ViewModel.liveDataLaunch(
    onError: (() -> Unit)? = null,
    block: suspend () -> T
) = liveData(viewModelScope.coroutineContext) {
    val result = runCatching { block() }.onFailure { onError?.invoke() }
    emit(result.getOrThrow())
}
