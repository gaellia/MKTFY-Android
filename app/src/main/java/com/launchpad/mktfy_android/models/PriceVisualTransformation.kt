package com.launchpad.mktfy_android.models

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PriceVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Make the string $ XXXX
        var output = ""
        for (i in text.indices) {
            if (i == 0) output += "\$ "
            output += text[i]
        }

        val priceTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 0) return offset
                return offset + 2
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset == 0) return offset
                return offset - 2
            }
        }
        return TransformedText(AnnotatedString(output), priceTranslator)
    }
}