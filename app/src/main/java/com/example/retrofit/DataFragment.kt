package com.example.retrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.retrofit.databinding.FragmentDataBinding
import com.example.retrofit.databinding.FragmentMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DataFragment : Fragment() {
    private val disposableBag = CompositeDisposable()
    private var _binding: FragmentDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        private const val IS_FETCH_DATE = "IS_FETCH_DATE"

        fun newInstance(isFetchDate: Boolean) = DataFragment().apply {
            arguments = bundleOf(IS_FETCH_DATE to isFetchDate)
        }
    }

    private fun fetchDate(){
        val disposable = SuperApp
            .getClient()
            .getDate()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                binding.dataText.text = it.toString()
            }, Throwable::printStackTrace)

        disposableBag.add(disposable)
    }

    private fun fetchIp() {
        val disposable = SuperApp
            .getClient()
            .getIp()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                binding.dataText.text = it.toString()
            }, Throwable::printStackTrace)

        disposableBag.add(disposable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposableBag.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireArguments().getBoolean(IS_FETCH_DATE)) {
            fetchDate()
        } else {
            fetchIp()
        }
    }
}