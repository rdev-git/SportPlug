package com.rdevl.sportplug2.ui.live

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rdevl.sportplug2.R
import com.rdevl.sportplug2.data.entries.Event
import com.rdevl.sportplug2.data.remote.RetrofitBuilder
import com.rdevl.sportplug2.databinding.FragmentLiveMatchesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LiveMatchesFragment : Fragment(R.layout.fragment_live_matches) {
    private val binding by viewBinding(FragmentLiveMatchesBinding::bind)
    private val liveMatchesAdapter = LiveMatchesAdapter { onClickItem(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = liveMatchesAdapter
        }

        getLiveMatches()
    }

    private fun getLiveMatches() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val api = RetrofitBuilder.apiService.getLiveMatches()
                Log.e("TAG", api.events.size.toString())
                launch(Dispatchers.Main) {
                    binding.recycler.isVisible = true
                    binding.progress.isVisible = false
                    liveMatchesAdapter.submitList(api.events)
                }
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
                if (e.message.toString().contentEquals("java.lang.IllegalStateException: Expected BEGIN_OBJECT but was STRING at line 1 column 1 path \$")) {
                    getLiveMatches()
                } else {
                    launch(Dispatchers.Main) {
                        Toast.makeText(context, "Не удалось выполнить запрос. Попробуйте позднее", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun onClickItem(data: Event) {
        val bundle = Bundle().apply {
            putInt("id", data.id.toString().toInt())
        }
        findNavController().navigate(R.id.action_liveMatchesFragment_to_liveDetailMatchFragment, bundle)
    }
}