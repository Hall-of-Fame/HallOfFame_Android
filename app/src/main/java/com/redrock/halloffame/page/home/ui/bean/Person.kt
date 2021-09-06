package com.redrock.halloffame.page.home.ui.bean

import com.redrock.halloffame.bean.DepartmentBean.Data.Grade.Student.Sticker

data class Person(
    val name: String,
    val avatar: String,
    val grade: String,
    val department: String,
    val stickers: List<Sticker>,
)