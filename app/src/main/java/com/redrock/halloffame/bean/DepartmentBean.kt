package com.redrock.halloffame.bean

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DepartmentBean(
    @SerializedName("data")
    val `data`: List<Data>
) : Serializable {
    data class Data(
        @SerializedName("grades")
        val grades: List<Grade>,
        @SerializedName("name")
        val name: String
    ) : Serializable {
        data class Grade(
            @SerializedName("name")
            val name: String,
            @SerializedName("students")
            val students: List<Student>
        ) : Serializable {
            data class Student(
                @SerializedName("avatar")
                val avatar: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("stickers")
                val stickers: List<Sticker>
            ) : Serializable {
                data class Sticker(
                    @SerializedName("desc")
                    val desc: String,
                    @SerializedName("url")
                    val url: String
                ) : Serializable
            }
        }
    }
}