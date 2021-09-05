package com.redrock.halloffame.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/3
 * @time 22:56
 */
data class BannerBean(
    @SerializedName("data")
    val `data`: List<Data>
) : Serializable {
    data class Data(
        @SerializedName("author")
        val author: String,
        @SerializedName("desc")
        val desc: String,
        @SerializedName("url")
        val url: String
    ) : Serializable
}
