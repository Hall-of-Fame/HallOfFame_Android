package com.redrock.halloffame.page.home.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.redrock.halloffame.R
import com.redrock.halloffame.bean.DepartmentBean.Data.Grade.Student.Sticker
import com.redrock.halloffame.utils.imgFromUr

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/5
 * @time 20:50
 */
class StickerRvAdapter(
    private var list: List<Sticker>
) : RecyclerView.Adapter<StickerRvAdapter.StickerVH>() {

    fun refresh(newList: List<Sticker>) {
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = list.size
            override fun getNewListSize(): Int = newList.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return list[oldItemPosition].url == newList[newItemPosition].url
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return list[oldItemPosition] == newList[newItemPosition]
            }
        }).dispatchUpdatesTo(this)
        list = newList
    }

    class StickerVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivSticker: ImageView = itemView.findViewById(R.id.iv_recycler_item_home_rank_list_sticker)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StickerVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_home_rank_list_sticker, parent, false)
        return StickerVH(view)
    }

    override fun onBindViewHolder(holder: StickerVH, position: Int) {
        holder.ivSticker.imgFromUr(list[position].url)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}