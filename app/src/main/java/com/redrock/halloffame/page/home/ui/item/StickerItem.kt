package com.redrock.halloffame.page.home.ui.item

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.redrock.halloffame.R
import com.redrock.halloffame.base.SimpleRvAdapter
import com.redrock.halloffame.page.home.ui.bean.Person
import com.redrock.halloffame.utils.imgFromUr

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/5
 * @time 19:43
 */
class StickerItem(
    list: Collection<Person>
) : SimpleRvAdapter.VHItem<StickerItem.StickerVH, Person>(
    list.toList(), 0, R.layout.recycler_item_home_rank_list_sticker
) {
    class StickerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSticker: ImageView = itemView.findViewById(R.id.iv_recycler_item_home_rank_list_sticker)
    }

    override fun getNewViewHolder(itemView: View): StickerVH {
        return StickerVH(itemView)
    }

    override fun onCreate(holder: StickerVH, map: Map<Int, Person>) {
    }

    override fun onRefactor(holder: StickerVH, position: Int, value: Person) {
        holder.ivSticker.imgFromUr(value.stickers[position].url)
    }
}