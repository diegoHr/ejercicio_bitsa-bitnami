package com.diegohr.ejercicio_bitsa_bitnovo.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.diegohr.ejercicio_bitsa_bitnovo.CastleGameViewModel
import com.diegohr.ejercicio_bitsa_bitnovo.R
import com.diegohr.ejercicio_bitsa_bitnovo.databinding.FragmentGameStatusBinding

/**
 * Created by Diego Hernando on 10/6/21.
 */
class GameStatusFragment : Fragment() {

    private var _binding: FragmentGameStatusBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val viewModel by activityViewModels<CastleGameViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameStatusBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.isGameFinished.observe(viewLifecycleOwner, {
            if(it){
                binding.textviewNumberOpenedWindows.text = getString(R.string.number_opened_windows, viewModel.numWindowsOpened)
                binding.textviewNumberClosedWindows.text = getString(R.string.number_closed_windows, viewModel.numWindowsClosed)
                binding.textviewNumberWindowsOpenedLeft.text = getString(R.string.number_windows_opened_left, viewModel.numWindowsLeftOpened)
                binding.textviewNumberWindowsOpenedRight.text = getString(R.string.number_windows_opened_right, viewModel.numWindowsRightOpened)

                binding.textviewListWinners.text = viewModel.printListWinners()
                binding.textviewListSecondWinners.text = viewModel.printListSecondWinners()
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}