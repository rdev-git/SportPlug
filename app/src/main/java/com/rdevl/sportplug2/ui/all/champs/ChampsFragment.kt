package com.rdevl.sportplug2.ui.all.champs

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rdevl.sportplug2.R
import com.rdevl.sportplug2.data.entries.Data
import com.rdevl.sportplug2.data.entries.PostChampsBody
import com.rdevl.sportplug2.data.remote.RetrofitBuilder
import com.rdevl.sportplug2.databinding.FragmentChampsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChampsFragment : Fragment(R.layout.fragment_champs) {
    private val binding by viewBinding(FragmentChampsBinding::bind)
    private val champsAdapter = ChampsAdapter { onClickItem(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = champsAdapter
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val api = RetrofitBuilder.apiService.getChamps(
                    PostChampsBody(
                        1,
                        0,
                        0,
                        "SITE_CUPIS"
                    )
                )
                launch(Dispatchers.Main) {
                    binding.recycler.isVisible = true
                    binding.progress.isVisible = false
                    champsAdapter.submitList(api.data)
                }
            } catch (e: Exception) {
                Log.e("TAG", e.message.toString())
                launch(Dispatchers.Main) {
                    Toast.makeText(context, "Не удалось выполнить запрос. Попробуйте позднее", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onClickItem(data: Data) {
        val bundle = Bundle().apply {
            putInt("id", data.cid)
        }
        findNavController().navigate(R.id.action_champsFragment_to_matchesFragment, bundle)
    }
}