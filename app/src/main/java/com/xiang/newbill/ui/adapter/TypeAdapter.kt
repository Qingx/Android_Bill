package com.xiang.newbill.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseViewHolder
import com.xiang.newbill.R
import com.xiang.newbill.entity.TypeEntity
import kotlinx.android.synthetic.main.item_bill_type.view.*

/**
 * Created by Xiang on 2020/5/9 12:48
 *
 * @email Cymbidium@outlook.com
 */
class TypeAdapter(
    private var context: Context,
    private var list: MutableList<TypeEntity>,
    private val type: String
) : RecyclerView.Adapter<TypeAdapter.ItemViewHolder>() {

    private var mSelectIndex = -1;

    init {
        list.withIndex().forEach {
            if (type != "") {
                if (it.value.type == type) {
                    mSelectIndex = it.index
                }
            }
        }
    }

    inner class ItemViewHolder(view: View) : BaseViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_bill_type, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val entity = list[position]
        holder.itemView.text_type.text = entity.type
        holder.itemView.image_type.setImageResource(entity.imageId)
        holder.itemView.isSelected = mSelectIndex == position

        holder.itemView.setOnClickListener {
            if (position == mSelectIndex) {
                return@setOnClickListener
            }

            mOnItemListener?.onItemClick(entity.type)
            val lastIndex = mSelectIndex
            mSelectIndex = position
            it.isSelected = true

            if (lastIndex >= 0) {
                notifyItemChanged(lastIndex)
            }
        }
    }

    private var mOnItemListener: OnItemListener? = null

    fun setOnItemListener(onItemListener: OnItemListener) {
        mOnItemListener = onItemListener
    }

    interface OnItemListener {
        fun onItemClick(name: String)
    }
}