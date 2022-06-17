package com.heroslender.takeawat.ui.menulist

import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.heroslender.takeawat.R
import com.heroslender.takeawat.adapter.MenuDateListAdapter
import com.heroslender.takeawat.adapter.MenuListAdapter
import com.heroslender.takeawat.databinding.ActivityMenuBinding
import java.util.*

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    private val viewModel: MenuViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupView()
    }

    private fun setupView() {
        val menuListAdapter = MenuListAdapter()
        val menuDateListAdapter = MenuDateListAdapter { date, _ ->
            viewModel.fetchMenus(date)
            binding.rvMenuList.apply {
                startAnimation(
                    AnimationUtils.loadAnimation(
                        this@MenuActivity,
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
                        this@MenuActivity,
                        R.anim.menu_list_leave
                    )
                )
                visibility = View.VISIBLE
            }
        }

        viewModel.fetchDates()
        viewModel.fetchMenus(Date())
    }
}