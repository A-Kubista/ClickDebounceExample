package com.example.clickdebouncer

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope

@BindingAdapter("android:onClick")
fun setDebounceListener(view: Button, onClickListener: View.OnClickListener) {
    val scope = ViewTreeLifecycleOwner.get(view)!!.lifecycleScope
    val clickWithDebounce: (view: View) -> Unit =
        debounce(scope = scope) {
            onClickListener.onClick(it)
        }

    view.setOnClickListener(clickWithDebounce)
}