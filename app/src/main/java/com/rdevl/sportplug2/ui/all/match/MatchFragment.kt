package com.rdevl.sportplug2.ui.all.match

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.rdevl.sportplug2.R
import com.rdevl.sportplug2.data.entries.DetailMatch
import com.rdevl.sportplug2.data.entries.Matches
import com.rdevl.sportplug2.data.remote.RetrofitBuilder
import com.rdevl.sportplug2.databinding.FragmentMatchBinding
import com.rdevl.sportplug2.utils.getTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class ViewPagerAdapter(fragment: Fragment, private val detailMatch: DetailMatch) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> {
                    val fragment = DetailMatchFragment()
                    fragment.arguments = Bundle().apply {
                        putParcelable("detail", detailMatch)
                    }
                    return fragment
                }
                1 -> {
                    val fragment = LineUpFragment()
                    fragment.arguments = Bundle().apply {
                        putParcelable("detail", detailMatch)
                    }
                    return fragment
                }
                else -> {
                    val fragment = LineUpFragment()
                    fragment.arguments = Bundle().apply {
                        putParcelable("detail", detailMatch)
                    }
                    return fragment
                }
            }
    }
}


class MatchFragment : Fragment(R.layout.fragment_match) {
    private val binding by viewBinding(FragmentMatchBinding::bind)
    private lateinit var model: DetailMatch
    private lateinit var timer: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            launch(Dispatchers.IO) {
                try {
                    model = RetrofitBuilder.apiService.getDetailMatch(arguments?.getInt("id")!!)
                    launch(Dispatchers.Main) {
                        binding.container.isVisible = true
                        binding.progress.isVisible = false
                        binding.firstTime.text = model.name.substringBefore("-").trim()
                        binding.SecondTeam.text = model.name.substringAfter("-").trim()
                        binding.startTime.text = getTime(model.start, "HH:mm")
                        val dateFormatter = SimpleDateFormat("HH:mm:ss")
                        timer = object : CountDownTimer(model.start * 1000L, 1000L) {
                            override fun onTick(millisUntilFinished: Long) {
                                binding.timer.text = dateFormatter.format(millisUntilFinished)
                            }
                            override fun onFinish() {
                            }
                        }
                        (timer as CountDownTimer).start()
                        binding.pager.adapter = ViewPagerAdapter(this@MatchFragment, model)
                        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
                            when(position) {
                                0 -> tab.text = "Обзор"
                                1 -> tab.text = "Состав"
                            }
                        }.attach()
                    }
                } catch (e: Exception) {
                    Log.e("TAG", e.message.toString())
                    launch(Dispatchers.Main) {
                        Toast.makeText(context, "Не удалось выполнить запрос. Попробуйте позднее", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        timer.cancel()
        timer.onFinish()
    }
}
