package com.redrock.halloffame.base

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/5
 * @time 22:08
 */
object BaseClient {

    const val baseUrl = "http://zhongtai521.wang:996"

    fun <T> api(clazz: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(clazz)
    }
}