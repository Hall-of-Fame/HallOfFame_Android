package com.redrock.halloffame.network

import com.redrock.halloffame.base.BaseClient
import com.redrock.halloffame.network.api.MainApiService
import retrofit2.await

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/3
 * @time 21:55
 */
object MainClient {
    suspend fun getDepartmentBean() = BaseClient.api(MainApiService::class.java).getDepartmentBean().await()
}