package com.heroslender.takeawat.ui.menulist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.heroslender.takeawat.adapter.MenuDateListAdapter
import com.heroslender.takeawat.adapter.MenuListAdapter
import com.heroslender.takeawat.base.BaseFragment
import com.heroslender.takeawat.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMenuBinding
        get() = FragmentMenuBinding::inflate

    private val viewModel: MenuViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        val menuListAdapter = MenuListAdapter()
        val menuDateListAdapter = MenuDateListAdapter { date, _ ->
            viewModel.setDate(date)
        }

        binding.rvDateList.adapter = menuDateListAdapter
        binding.rvMenuList.adapter = menuListAdapter
        binding.rvMenuList.layoutManager = object : LinearLayoutManager(context) {
            override fun supportsPredictiveItemAnimations(): Boolean {
                // Fix an AIOOB Exception
                return false
            }
        }
        binding.refreshMenuList.setOnRefreshListener {
            viewModel.refresh()
        }

        viewModel.dates.observeForever { dates ->
            menuDateListAdapter.setDates(dates)
        }

        viewModel.menus.observeForever { menus ->
            binding.refreshMenuList.isRefreshing = false
            menuListAdapter.setMenuList(menus)
        }

        viewModel.date.observeForever {
            menuDateListAdapter.setSelectedDate(it)
        }

        viewModel.failure.observeForever {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }

        viewModel.loadData()
    }
}