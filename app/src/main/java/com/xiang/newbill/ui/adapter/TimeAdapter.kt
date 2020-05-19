package com.xiang.newbill.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseViewHolder
import com.xiang.newbill.R
import com.xiang.newbill.config.BillDaoManager
import com.xiang.newbill.entity.BillEntity
import com.xiang.newbill.entity.TimeEntity
import kotlinx.android.synthetic.main.item_time.view.*

/**
 * Created by Xiang on 2020/5/14 15:52
 *
 * @email Cymbidium@outlook.com
 */
class TimeAdapter(private val context: Context, private val list: MutableList<TimeEntity>) :
    RecyclerView.Adapter<TimeAdapter.ItemViewHolder?>() {

    private var billDaoManager: BillDaoManager? = null

    class ItemViewHolder(view: View) : BaseViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        billDaoManager = BillDaoManager.getInstance(context)

        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_time, parent, false)
        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val entity = list[position]

        val money: Double = billDaoManager!!.findSumMoney(entity.date)
        holder.itemView.text_money.text = "支出：$money"
        holder.itemView.text_time.text = entity.date

        val list: MutableList<BillEntity> = billDaoManager!!.findBillList(entity.date)
        val adapter = BillAdapter(context, list)

        holder.itemView.rv_content.adapter = adapter
        holder.itemView.rv_content.layoutManager = object : LinearLayoutManager(context) {}
    }
}