package com.heroslender.takeawat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heroslender.takeawat.R
import com.heroslender.takeawat.databinding.MenuDateListItemBinding
import java.util.*

class MenuDateListAdapter(
    private var selectedDate: Date? = null,
    val onClick: (selected: Date, position: Int) -> Unit
) : RecyclerView.Adapter<MenuDateListAdapter.MenuDateListViewHolder>() {
    private lateinit var recyclerView: RecyclerView
    private var selected: MenuDateListAdapter.MenuDateListViewHolder? = null
    private val menuDateList: MutableList<Date> = mutableListOf()

    inner class MenuDateListViewHolder(
        private val binding: MenuDateListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(date: Date, position: Int) {
            val calendar = Calendar.getInstance().apply {
                timeInMillis = date.time
            }
            binding.tvDateDay.text = calendar.get(Calendar.DAY_OF_MONTH)
                .toString() + " " + getMonthForNumber(calendar.get(Calendar.MONTH))
            binding.tvDateMonth.text = getWeekDay(calendar.get(Calendar.DAY_OF_WEEK))

            binding.rvDateListItem.setOnClickListener {
                if (selected == this) {
                    return@setOnClickListener
                }

                selectedDate = date
                onClick(date, position)
                setSelected()
            }

            if (date == selectedDate) {
                setSelected()
            }
        }

        fun setSelected() {
            // Set the selected background to the current item
            binding.root.post {
                binding.rvDateListItem.setBackgroundResource(R.drawable.menu_date_list_item_selected)
            }
            // Remove the selected background from the previous selected item
            selected?.binding?.rvDateListItem?.setBackgroundResource(R.drawable.menu_date_list_item)

            selected = this
        }

        private fun getMonthForNumber(month: Int): String = when (month) {
            0 -> "Jan"
            1 -> "Fev"
            2 -> "Mar"
            3 -> "Abr"
            4 -> "Mai"
            5 -> "Jun"
            6 -> "Jul"
            7 -> "Ago"
            8 -> "Set"
            9 -> "Out"
            10 -> "Nov"
            11 -> "Dez"
            else -> "Erro"
        }

        private fun getWeekDay(day: Int): String = when (day) {
            1 -> "Domingo"
            2 -> "Segunda"
            3 -> "Terça"
            4 -> "Quarta"
            5 -> "Quinta"
            6 -> "Sexta"
            7 -> "Sábado"
            else -> "Erro"
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.recyclerView = recyclerView
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
    }

    override fun getItemCount(): Int {
        return menuDateList.size
    }

    fun setDates(dates: List<Date>) {
        menuDateList.clear()
        menuDateList.addAll(dates)
        notifyDataSetChanged()
    }

    fun setSelectedDate(date: Date) {
        val index = menuDateList.indexOf(date)
        if (index < 0) {
            return
        }

        this.selectedDate = date

        val holder = recyclerView.findViewHolderForAdapterPosition(index) ?: return
        (holder as? MenuDateListViewHolder)?.setSelected()
    }
}