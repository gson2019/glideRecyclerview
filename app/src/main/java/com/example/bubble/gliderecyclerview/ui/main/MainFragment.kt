package com.example.bubble.gliderecyclerview.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.bubble.gliderecyclerview.viewmodel.SharedViewModel
import com.example.bubble.gliderecyclerview.R
import com.example.bubble.gliderecyclerview.model.Monster
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(),
    MonsterAdapter.MonsterItemListener {
    private lateinit var viewModel: SharedViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as AppCompatActivity).run{
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
        navController = findNavController()
       return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeLayout.setOnRefreshListener {
            viewModel.refreshData()
        }
        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

        viewModel.monsterData.observe(this, Observer {
            val monsterNames = StringBuilder()
            for(monster in it){
                monsterNames.append(monster.monsterName).append("\n")
            }
            val adapter =
                MonsterAdapter(
                    requireContext(),
                    it,
                    this
                )
            monsterRv.adapter = adapter
            swipeLayout.isRefreshing = false
        })
    }

    override fun onMonsterItemClick(monster: Monster) {
//        viewModel.selectedMonster.value = monster
        viewModel.selectedMonster = monster
        navController.navigate(MainFragmentDirections.navToDetail())

    }
}