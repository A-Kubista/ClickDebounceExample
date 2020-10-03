package com.example.clickdebouncer

import android.view.View
import android.widget.Button
import androidx.databinding.BindingAdapter
import kotlinx.coroutines.CoroutineScope

@BindingAdapter("android:onClick", "scope")
fun setDebounceListener(
    view: Button, onClickListener: View.OnClickListener,
    coroutineScope: CoroutineScope
) {
    val clickWithDebounce: (view: View) -> Unit =
        debounce(scope = coroutineScope) {
            onClickListener.onClick(it)
        }

    view.setOnClickListener(clickWithDebounce)
}