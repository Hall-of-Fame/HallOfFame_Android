package com.redrock.halloffame.network

import com.redrock.halloffame.base.BaseClient
import com.redrock.halloffame.network.api.HomeApiService
import retrofit2.await

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/3
 * @time 23:02
 */
object HomeClient {
    suspend fun getBannerBean() = BaseClient.api(HomeApiService::class.java).getBannerBean().await()
}