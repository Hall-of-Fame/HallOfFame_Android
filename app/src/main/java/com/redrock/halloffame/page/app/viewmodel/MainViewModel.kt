package com.redrock.halloffame.page.app.viewmodel

import androidx.lifecycle.ViewModel
import com.redrock.halloffame.page.home.ui.bean.Person
import com.redrock.halloffame.network.MainClient
import com.redrock.halloffame.utils.liveDataLaunch
import com.redrock.halloffame.utils.switchMapLiveDataLaunch
import java.util.*

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/3
 * @time 20:59
 */
class MainViewModel : ViewModel() {

    var departmentBean = liveDataLaunch { MainClient.getDepartmentBean() }

    val stickersRankTreeSet = switchMapLiveDataLaunch(departmentBean) {
        val treeMap = TreeSet<Person>{ o1, o2 -> o1.stickers.size - o2.stickers.size }
        it.data.forEach { department ->
            department.grades.forEach { grade ->
                grade.students.forEach { student ->
                    treeMap.add(Person(student.name, student.avatar, grade.name, department.name, student.stickers))
                }
            }
        }
        treeMap
    }

    fun refreshDepartmentBean() {
        departmentBean = liveDataLaunch { MainClient.getDepartmentBean() }
    }
}