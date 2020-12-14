package com.example.retrofit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofit.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        
        super.onViewCreated(view, savedInstanceState)
        binding.getDataButton.setOnClickListener{_ ->
            when(binding.radioGroup.checkedRadioButtonId){
                R.id.ip -> requireFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, DataFragment.newInstance(false))
                    .addToBackStack(null)
                    .commit()
                R.id.date -> requireFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_container, DataFragment.newInstance(true))
                    .addToBackStack(null)
                    .commit()
            }
        }

    }
    



    companion object {
        fun newInstance() = MainFragment()
    }

}