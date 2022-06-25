package com.heroslender.takeawat.ui.menulist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.heroslender.takeawat.R
import com.heroslender.takeawat.adapter.MenuDateListAdapter
import com.heroslender.takeawat.adapter.MenuListAdapter
import com.heroslender.takeawat.base.BaseFragment
import com.heroslender.takeawat.databinding.FragmentMenuBinding
import java.util.*

class MenuFragment : BaseFragment<FragmentMenuBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMenuBinding
        get() = FragmentMenuBinding::inflate

    private val viewModel: MenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Thread.sleep(1000)

        setupView()
    }

    private fun setupView() {
        val menuListAdapter = MenuListAdapter()
        val menuDateListAdapter = MenuDateListAdapter { date, _ ->
            viewModel.fetchMenus(date)
            binding.rvMenuList.apply {
                startAnimation(
                    AnimationUtils.loadAnimation(
                        this@MenuFragment.context,
                        R.anim.menu_list_enter
                    )
                )
                visibility = View.GONE
            }
        }

        binding.rvDateList.adapter = menuDateListAdapter
        binding.rvMenuList.adapter = menuListAdapter

        viewModel.dates.observeForever { dates ->
            menuDateListAdapter.setDates(dates)
        }

        viewModel.menus.observeForever { menus ->
            menuListAdapter.setMenuList(menus)
            binding.rvMenuList.apply {
                startAnimation(
                    AnimationUtils.loadAnimation(
                        this@MenuFragment.context,
                        R.anim.menu_list_leave
                    )
                )
                visibility = View.VISIBLE
            }
        }

        viewModel.failure.observeForever {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }

        viewModel.fetchDates()
        viewModel.fetchMenus(Date())
    }
}