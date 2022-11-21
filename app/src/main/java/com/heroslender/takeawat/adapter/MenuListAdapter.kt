package com.heroslender.takeawat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*
import com.heroslender.takeawat.databinding.CollapsedMenuListItemBinding
import com.heroslender.takeawat.databinding.ExpandedMenuListItemBinding
import com.heroslender.takeawat.databinding.MenuListItemBinding
import com.heroslender.takeawat.domain.Menu
import com.heroslender.takeawat.utils.NumberUtils

class MenuListAdapter : RecyclerView.Adapter<MenuListAdapter.MenuListViewHolder>() {
    private val menuList: MutableList<Menu> = mutableListOf()

    inner class MenuListViewHolder(
        private val binding: MenuListItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        private val collapsedBinding: CollapsedMenuListItemBinding by lazy {
            CollapsedMenuListItemBinding.inflate(
                LayoutInflater.from(binding.root.context),
                binding.scene,
                false
            )
        }
        private val collapsedScene: Scene by lazy { Scene(binding.scene, collapsedBinding.root) }

        private val expandedBinding: ExpandedMenuListItemBinding by lazy {
            ExpandedMenuListItemBinding.inflate(
                LayoutInflater.from(binding.root.context),
                binding.scene,
                false
            )
        }
        private val expandedScene: Scene by lazy {
            Scene(binding.scene, expandedBinding.root)
        }

        fun bindCollapsed(menu: Menu, position: Int) {
            val transform = TransitionSet()
            transform.addTransition(ChangeTransform())
            transform.addTransition(ChangeBounds())
            TransitionManager.go(collapsedScene, transform)

            collapsedBinding.tvMenuName.text = menu.name
            collapsedBinding.tvMenuDescrition.text = menu.description
            collapsedBinding.tvHalfDose.text = NumberUtils.format(menu.halfPrice) + " €"
            collapsedBinding.tvFullDose.text = NumberUtils.format(menu.price) + " €"
            collapsedBinding.layout.setOnClickListener {
                bindExpanded(menu, position)
                notifyItemChanged(position, true)
            }
        }

        fun bindExpanded(menu: Menu, position: Int) {
            val transform = TransitionSet()
            transform.addTransition(ChangeTransform())
            transform.addTransition(ChangeBounds())
            TransitionManager.go(expandedScene, transform)

            expandedBinding.tvMenuName.text = menu.name
            expandedBinding.tvMenuDescrition.text = menu.description
            expandedBinding.tvHalfDose.text = NumberUtils.format(menu.halfPrice) + " €"
            expandedBinding.tvFullDose.text = NumberUtils.format(menu.price) + " €"
            expandedBinding.layout.setOnClickListener {
                bindCollapsed(menu, position)
                notifyItemChanged(position, false)
            }
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
        holder.bindCollapsed(menuList[position], position)
    }

    override fun onBindViewHolder(
        holder: MenuListViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        // Need this to show the animation of the other items moving
        // instead of just teleporting
        if (payloads.isEmpty() || payloads[0] !is Boolean) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setMenuList(menuList: List<Menu>) {
        val size = menuList.size
        this.menuList.clear()
        this.menuList.addAll(menuList)
        notifyItemRangeRemoved(0, size)
        notifyItemRangeInserted(0, menuList.size)
    }
}