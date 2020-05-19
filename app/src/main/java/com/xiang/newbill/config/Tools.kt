package com.xiang.newbill.config

import android.content.Context
import android.graphics.Color
import android.widget.Toast
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.xiang.newbill.R
import com.xiang.newbill.config.App.mContext
import com.xiang.newbill.entity.TypeEntity
import java.math.BigDecimal
import java.util.*

/**
 * Created by Xiang on 2020/5/7 10:43
 *
 * @email Cymbidium@outlook.com
 */
class Tools {
    companion object {

        /**
         * 简化的Toast
         */
        fun myToast(charSequence: CharSequence) {
            Toast.makeText(mContext, charSequence, Toast.LENGTH_SHORT).show()
        }

        /**
         * 获取星期几
         */
        private fun getWeekDay(time: Long): String {
            val weekDays =
                arrayOf("星期天", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
            val calendar = Calendar.getInstance()
            calendar.time = Date(time)
            var week = calendar[Calendar.DAY_OF_WEEK] - 1
            if (week < 0) {
                week = 0
            }
            return weekDays[week]
        }

        /**
         * 获取年月日星期
         * E: 2020年03月20日 星期一
         *
         * @return
         */
        fun getDateString(time: Long): String {
            val year: String = DateFormat.dateToYear(time)
            val month: String = DateFormat.dateToMonth(time)
            val day: String = DateFormat.dateToDay(time)
            val weekDay: String = getWeekDay(time)
            return year + "年" + month + "月" + day + "日" + " " + weekDay
        }

        /**
         * 高精度double加法
         */
        fun doubleAdd(v1: Double, v2: Double): Double {
            val b1 = BigDecimal(v1)
            val b2 = BigDecimal(v2)
            return b1.add(b2).toDouble()
        }

        /**
         * 判断是否同一天
         */
        fun isSameDay(calendar: Calendar): Boolean {
            val today: Calendar = Calendar.getInstance()

            val dayChoose = calendar.get(Calendar.DAY_OF_YEAR)
            val yearChoose = calendar.get(Calendar.YEAR)
            val dayNow = today.get(Calendar.DAY_OF_YEAR)
            val yearNow = today.get(Calendar.YEAR)

            return dayNow == dayChoose && yearNow == yearChoose
        }

        /**
         * 获取年
         */
        fun getYear(calendar: Calendar): String {
            return calendar.get(Calendar.YEAR).toString()
        }

        /**
         * 获取月
         */
        fun getMonth(calendar: Calendar): String {
            return if (calendar.get(Calendar.MONTH) + 1 < 10) {
                "0${calendar.get(Calendar.MONTH) + 1}"
            } else (calendar.get(Calendar.MONTH) + 1).toString()
        }

        /**
         * 获取日
         */
        fun getDay(calendar: Calendar): String {
            return if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
                "0${calendar.get(Calendar.DAY_OF_MONTH)}"
            } else calendar.get(Calendar.DAY_OF_MONTH).toString()
        }

        /**
         * 设置时间选择器日期
         */
        fun setDate(year: Int, month: Int, date: Int): Calendar {
            val startDate: Calendar = Calendar.getInstance()
            startDate.set(year, month, date)
            return startDate
        }

        /**
         * 设置type种类
         */
        fun setTypeList(list: MutableList<TypeEntity>) {
            list.add(
                TypeEntity(
                    "家电",
                    R.drawable.ic_appliance,
                    "appliance"
                )
            )
            list.add(
                TypeEntity(
                    "餐饮",
                    R.drawable.ic_food,
                    "food"
                )
            )
            list.add(
                TypeEntity(
                    "书籍",
                    R.drawable.ic_book,
                    "book"
                )
            )
            list.add(
                TypeEntity(
                    "衣物",
                    R.drawable.ic_clothes,
                    "clothes"
                )
            )
            list.add(
                TypeEntity(
                    "化妆品",
                    R.drawable.ic_cosmetic,
                    "cosmetic"
                )
            )
            list.add(
                TypeEntity(
                    "日用品",
                    R.drawable.ic_daily,
                    "daily"
                )
            )
            list.add(
                TypeEntity(
                    "数码",
                    R.drawable.ic_digital,
                    "digital"
                )
            )
            list.add(
                TypeEntity(
                    "饮品",
                    R.drawable.ic_drink,
                    "drink"
                )
            )
            list.add(
                TypeEntity(
                    "教育",
                    R.drawable.ic_education,
                    "education"
                )
            )
            list.add(
                TypeEntity(
                    "娱乐",
                    R.drawable.ic_entertainment,
                    "entertainment"
                )
            )
            list.add(
                TypeEntity(
                    "水果",
                    R.drawable.ic_fruit,
                    "fruit"
                )
            )
            list.add(
                TypeEntity(
                    "家具",
                    R.drawable.ic_furniture,
                    "furniture"
                )
            )
            list.add(
                TypeEntity(
                    "游戏",
                    R.drawable.ic_game,
                    "game"
                )
            )
            list.add(
                TypeEntity(
                    "礼物",
                    R.drawable.ic_gift,
                    "gift"
                )
            )
            list.add(
                TypeEntity(
                    "育儿",
                    R.drawable.ic_kid,
                    "kid"
                )
            )
            list.add(
                TypeEntity(
                    "恋爱",
                    R.drawable.ic_love,
                    "love"
                )
            )
            list.add(
                TypeEntity(
                    "医药",
                    R.drawable.ic_medical,
                    "medical"
                )
            )
            list.add(
                TypeEntity(
                    "运动",
                    R.drawable.ic_motion,
                    "motion"
                )
            )
            list.add(
                TypeEntity(
                    "电影",
                    R.drawable.ic_movie,
                    "movie"
                )
            )
            list.add(
                TypeEntity(
                    "音乐",
                    R.drawable.ic_music,
                    "music"
                )
            )
            list.add(
                TypeEntity(
                    "宠物",
                    R.drawable.ic_pet,
                    "pet"
                )
            )
            list.add(
                TypeEntity(
                    "房租",
                    R.drawable.ic_rent,
                    "rent"
                )
            )
            list.add(
                TypeEntity(
                    "修理",
                    R.drawable.ic_repair,
                    "repair"
                )
            )
            list.add(
                TypeEntity(
                    "零食",
                    R.drawable.ic_snacks,
                    "snacks"
                )
            )
            list.add(
                TypeEntity(
                    "旅游",
                    R.drawable.ic_tourism,
                    "tourism"
                )
            )
            list.add(
                TypeEntity(
                    "交通",
                    R.drawable.ic_traffic,
                    "traffic"
                )
            )
        }

        fun createTimePicker(
            context: Context,
            title: String,
            startDate: Calendar,
            endDate: Calendar,
            onTimeSelectListener: OnTimeSelectListener
        ): TimePickerView {

            val timePickerView = object : TimePickerBuilder(context, onTimeSelectListener) {}
                .setType(booleanArrayOf(true, true, true, false, false, false))
                .setTitleText(title)
                .setContentTextSize(14)
                .setSubCalSize(14)
                .setTitleSize(14)
                .setRangDate(startDate, endDate)
                .setDate(Calendar.getInstance())
                .setSubmitColor(Color.parseColor("#131313"))
                .setCancelColor(Color.parseColor("#131313"))
                .setTitleColor(Color.parseColor("#131313"))
                .setBgColor(Color.parseColor("#f4f5f7"))
                .setTitleBgColor(Color.parseColor("#f4f5f7"))
                .setTextColorCenter(Color.parseColor("#F4606C"))
                .setOutSideCancelable(true)
                .build()

            timePickerView.show()
            return timePickerView
        }
    }
}