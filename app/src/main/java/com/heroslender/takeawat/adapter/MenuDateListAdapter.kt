package com.heroslender.takeawat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heroslender.takeawat.R
import com.heroslender.takeawat.databinding.MenuDateListItemBinding
import java.util.*

class MenuDateListAdapter(
    val onClick: (selected: Date, position: Int) -> Unit
) : RecyclerView.Adapter<MenuDateListAdapter.MenuDateListViewHolder>() {

    private var selected: MenuDateListAdapter.MenuDateListViewHolder? = null
    private val menuDateList: MutableList<Date> = mutableListOf()

    inner class MenuDateListViewHolder(
        private val binding: MenuDateListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(date: Date, position: Int) {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = date.time
            }
            binding.tvDateDay.text = calendar.get(Calendar.DAY_OF_MONTH).toString()
            binding.tvDateMonth.text = getMonthForNumber(calendar.get(Calendar.MONTH))

            binding.rvDateListItem.setOnClickListener {
                if (selected == this) {
                    return@setOnClickListener
                }

                onClick(date, position)
                setSelected()
            }
        }

        fun setSelected() {
            // Set the selected background to the current item
            binding.rvDateListItem.setBackgroundResource(R.drawable.menu_date_list_item_selected)
            // Remove the selected background from the previous selected item
            selected?.binding?.rvDateListItem?.setBackgroundResource(R.drawable.menu_date_list_item)

            selected = this
        }

        private fun getMonthForNumber(month: Int): String = when (month) {
            1 -> "Jan"
            2 -> "Fev"
            3 -> "Mar"
            4 -> "Abr"
            5 -> "Mai"
            6 -> "Jun"
            7 -> "Jul"
            8 -> "Ago"
            9 -> "Set"
            10 -> "Out"
            11 -> "Nov"
            12 -> "Dez"
            else -> "Erro"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuDateListViewHolder {
        return MenuDateListViewHolder(
            MenuDateListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuDateListViewHolder, position: Int) {
        holder.bind(menuDateList[position], position)

        if (selected == null && position == 0) {
            holder.setSelected()
        }
    }

    override fun getItemCount(): Int {
        return menuDateList.size
    }

    fun setDates(dates: List<Date>) {
        menuDateList.clear()
        menuDateList.addAll(dates)
        notifyDataSetChanged()
    }
}