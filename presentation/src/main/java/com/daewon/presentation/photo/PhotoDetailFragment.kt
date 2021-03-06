package com.daewon.presentation.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.daewon.presentation.databinding.FragmentPhotoDetailBinding
import com.daewon.presentation.viewmodels.PhotoDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoDetailFragment : Fragment() {

    private var _binding: FragmentPhotoDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PhotoDetailViewModel by viewModels()
    private val args: PhotoDetailFragmentArgs by navArgs()
    private var isFirst: Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoDetailBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        context ?: return binding.root

        viewModel.photoDetailData.observe(viewLifecycleOwner) {
            binding.card = it.card
            binding.user = it.user
        }

        if (isFirst) {
            isFirst = false
            getData(args.id)
        }

        return binding.root
    }

    private fun getData(cardId: Int) {
        viewModel.getPhotoDetail(cardId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding!!.refreshLayout.isEnabled = false
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        _binding!!.refreshLayout.isEnabled = true
    }

}