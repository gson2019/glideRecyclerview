package com.example.bubble.gliderecyclerview.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.bubble.gliderecyclerview.LOG_TAG
import com.example.bubble.gliderecyclerview.viewmodel.MainViewModel
import com.example.bubble.gliderecyclerview.R
import com.example.bubble.gliderecyclerview.model.Monster
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), MonsterAdapter.MonsterItemListener {
    private lateinit var viewModel: MainViewModel
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
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.monsterData.observe(this, Observer {
            val monsterNames = StringBuilder()
            for(monster in it){
                monsterNames.append(monster.monsterName).append("\n")
            }
            val adapter = MonsterAdapter(requireContext(), it, this)
            monsterRv.adapter = adapter
            swipeLayout.isRefreshing = false
        })
    }

    override fun onMonsterItemClick(monster: Monster) {
//        navController.navigate(R.id.nav_to_detail)
//        findNavController()
        navController.navigate(MainFragmentDirections.navToDetail(monster))
    }
}