package com.redrock.halloffame.network.api

import com.redrock.halloffame.bean.DepartmentBean
import retrofit2.Call
import retrofit2.http.GET

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/3
 * @time 21:04
 */
interface MainApiService {

    @GET("/departments")
    fun getDepartmentBean(): Call<DepartmentBean>
}