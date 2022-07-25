package com.rdevl.sportplug2.ui.all.matches

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
import com.rdevl.sportplug2.databinding.FragmentMatchesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchesFragment : Fragment(R.layout.fragment_matches) {
    private val binding by viewBinding(FragmentMatchesBinding::bind)
    private val matchesAdapter = MatchesAdapter { onClickItem(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = matchesAdapter
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val api = RetrofitBuilder.apiService.getChampMatches(arguments?.getInt("id")!!)
                launch(Dispatchers.Main) {
                    matchesAdapter.submitList(api.events)
                    binding.recycler.isVisible = true
                    binding.progress.isVisible = false
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Не удалось выполнить запрос. Попробуйте позже", Toast.LENGTH_SHORT).show()
                }
                Log.e("TAG", e.message.toString())
            }
        }
    }

    private fun onClickItem(data: Event) {
        val bundle = Bundle().apply {
            putInt("id", data.id)
        }
        Log.e("TAG", data.id.toString())
        findNavController().navigate(R.id.action_matchesFragment_to_matchFragment, bundle)
    }
}