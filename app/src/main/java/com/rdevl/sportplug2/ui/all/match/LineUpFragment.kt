package com.rdevl.sportplug2.ui.all.match

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.rdevl.sportplug2.R
import com.rdevl.sportplug2.databinding.FragmentLineUpBinding

class LineUpFragment : Fragment(R.layout.fragment_line_up) {
    private val binding by viewBinding(FragmentLineUpBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}