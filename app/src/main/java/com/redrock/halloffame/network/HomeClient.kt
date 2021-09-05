package com.redrock.halloffame.network

import com.redrock.halloffame.network.api.HomeApiService
import retrofit2.Retrofit
import retrofit2.await
import retrofit2.converter.gson.GsonConverterFactory

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/3
 * @time 23:02
 */
object HomeClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://cn.bing.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiService = retrofit.create(HomeApiService::class.java)
    suspend fun getDepartmentBean() = apiService.getBannerBean().await()
}