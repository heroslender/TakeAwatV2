package com.heroslender.takeawat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heroslender.takeawat.databinding.MenuListItemBinding
import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.utils.NumberUtils

class MenuListAdapter : RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder>() {
    private val menuList: MutableList<Menu> = mutableListOf()

    class MenuListViewHolder(
        private val binding: MenuListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(menu: Menu, position: Int) {
            binding.tvMenuName.text = menu.name
            binding.tvHalfDose.text = NumberUtils.format(menu.halfPrice) + " €"
            binding.tvFullDose.text = NumberUtils.format(menu.price) + " €"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuListViewHolder {
        return MenuListViewHolder(
            MenuListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuListViewHolder, position: Int) {
        holder.bind(menuList[position], position)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setMenuList(menuList: List<Menu>) {
        this.menuList.clear()
        this.menuList.addAll(menuList)
        notifyDataSetChanged()
    }
}