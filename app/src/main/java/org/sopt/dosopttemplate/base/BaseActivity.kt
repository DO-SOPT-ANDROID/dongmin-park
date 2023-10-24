package org.sopt.dosopttemplate.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>(
    val bindingFactory: (LayoutInflater) -> T
) : AppCompatActivity() {
    private var _binding: T? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingFactory(layoutInflater)
        setContentView(binding.root)

        hideKeyboards()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    fun hideKeyboards() {
        binding.root.setOnClickListener { view ->
            // 키보드 내리기
            val controller = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            controller.hideSoftInputFromWindow(view.windowToken, 0)

            // 포커스 없애기
            view.clearFocus()
        }
    }
}