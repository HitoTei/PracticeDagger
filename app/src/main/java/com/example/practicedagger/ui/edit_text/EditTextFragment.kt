package com.example.practicedagger.ui.edit_text

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practicedagger.MyApplication
import com.example.practicedagger.databinding.FragmentEditTextAndButtonBinding

class EditTextFragment : Fragment() {
    lateinit var viewModel: EditTextViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel =
            (this.requireActivity().application as MyApplication).appComponent.editTextViewModel()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditTextAndButtonBinding.inflate(inflater, container, false).apply {
            viewModel = this@EditTextFragment.viewModel
            lifecycleOwner = requireActivity()
        }
        return binding.root
    }
}