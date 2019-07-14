package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    this.currentFocus?.let {
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    currentFocus?.rootView?.getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = currentFocus?.height?.minus(visibleBounds.height())
    if (heightDiff != null) {
        return heightDiff > 0
    }

    return false
}

fun Activity.isKeyboardClosed(): Boolean {
    return !isKeyboardOpen()
}
