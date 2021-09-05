package com.redrock.halloffame.page.home.ui.bean

import com.redrock.halloffame.bean.DepartmentBean

data class Person(
    val name: String,
    val avatar: String,
    val grade: String,
    val department: String,
    val stickers: List<DepartmentBean.Data.Grade.Student.Sticker>,
)