package io.github.aaaamirabbas.edge.ext.view

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.textString() =
    this.editText?.editableText.toString()


fun TextInputLayout.setString(text: String) =
    this.editText?.setText(text)
