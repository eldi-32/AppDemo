package com.ulventech.appdemo

import android.content.Context
import android.util.AttributeSet
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.custom_dialog_layout.view.*

class CustomDialogLayout (
        context: Context,
        attrs: AttributeSet
) : ConstraintLayout(context, attrs) {
    init {
        inflate(context, R.layout.custom_dialog_layout, this)

        val customAttributesStyle = context.obtainStyledAttributes(attrs, R.styleable.CustomDialogLayout, 0, 0)

        val image = imageView
        val title = title
        val description = description
        val btnOk = btnOk

        try {
            image.background = customAttributesStyle.getDrawable(R.styleable.CustomDialogLayout_imageView)
            title.text = customAttributesStyle.getString(R.styleable.CustomDialogLayout_title)
            description.text = customAttributesStyle.getString(R.styleable.CustomDialogLayout_description)
        } finally {
            customAttributesStyle.recycle()
        }

        btnOk.setOnClickListener {
            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
        }


    }
}