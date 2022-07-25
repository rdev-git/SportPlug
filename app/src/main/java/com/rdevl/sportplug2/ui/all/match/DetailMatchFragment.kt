package com.rdevl.sportplug2.ui.all.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rdevl.sportplug2.R
import com.rdevl.sportplug2.data.entries.DetailMatch
import com.rdevl.sportplug2.databinding.FragmentDetailMatchBinding
import com.rdevl.sportplug2.utils.getTime

class DetailMatchFragment : Fragment(R.layout.fragment_detail_match) {
    private val binding by viewBinding(FragmentDetailMatchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments?.getParcelable<DetailMatch>("detail")

        binding.champ.text = args?.champName
        binding.time.text = getTime(args?.start!!, "E d MMM. в HH:mm")
    }
}