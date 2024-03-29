package org.sopt.dosopttemplate.presentation.home.doandroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.sopt.dosopttemplate.databinding.FragmentDoAndroidBinding
import org.sopt.dosopttemplate.util.binding.BaseFragment

class DoAndroidFragment : BaseFragment<FragmentDoAndroidBinding>() {
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentDoAndroidBinding = FragmentDoAndroidBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 해당 프라그먼트에서 실행하고자 하는 대부분의 내용이 담겨용
    }
}
