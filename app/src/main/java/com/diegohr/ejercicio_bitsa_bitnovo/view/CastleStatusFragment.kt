package com.diegohr.ejercicio_bitsa_bitnovo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegohr.ejercicio_bitsa_bitnovo.CastleGameViewModel
import com.diegohr.ejercicio_bitsa_bitnovo.databinding.FragmentCastleStatusBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created by Diego Hernando on 10/6/21.
 */
@AndroidEntryPoint
class CastleStatusFragment : Fragment() {

    private var _binding: FragmentCastleStatusBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel by activityViewModels<CastleGameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCastleStatusBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerView.setLayoutManager(layoutManager)
        viewModel.isGameFinished.observe(viewLifecycleOwner, Observer {
            val adapter =  CastleStatusAdapter(viewModel.castleStatus)
            binding.recyclerView.adapter = adapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}