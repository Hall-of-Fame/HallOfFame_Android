package com.redrock.halloffame.page.home.ui.item

import com.redrock.halloffame.R
import com.redrock.halloffame.base.SimpleRvAdapter
import com.redrock.halloffame.page.home.ui.bean.Person
import com.redrock.halloffame.databinding.RecyclerItemHomeRankListBinding

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/5
 * @time 17:59
 */
class RankListItem(
    map: Map<Int, Person>
) : SimpleRvAdapter.DBItem<RecyclerItemHomeRankListBinding, Person>(
    map, R.layout.recycler_item_home_rank_list
) {
    override fun onCreate(
        binding: RecyclerItemHomeRankListBinding,
        holder: SimpleRvAdapter.BindingVH,
        map: Map<Int, Person>
    ) {
    }

    override fun onRefactor(
        binding: RecyclerItemHomeRankListBinding,
        holder: SimpleRvAdapter.BindingVH,
        position: Int,
        value: Person
    ) {
        TODO("Not yet implemented")
    }
}