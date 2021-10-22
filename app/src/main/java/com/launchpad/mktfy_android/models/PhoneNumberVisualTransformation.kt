package com.launchpad.mktfy_android.models

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneNumberVisualTransformation: VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        // Make the string +1 (XXX) XXX - XXXX
        val trimmed = if (text.text.length >= 10) text.text.substring(0..9) else text.text
        var output = ""
        for (i in trimmed.indices) {
            if (i == 0) output += "+1 ("
            output += trimmed[i]
            if (i == 2) output += ") "
            if (i == 5) output += " - "
        }

        val phoneNumberTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset == 0) return offset
                if (offset <= 2) return offset + 4
                if (offset <= 5) return offset + 6
                if (offset <= 9) return offset + 9
                return 19
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset == 0) return offset
                if (offset <= 6) return offset - 4
                if (offset <= 11) return offset - 6
                if (offset <= 18) return offset - 9
                return 10

            }
        }
        return TransformedText(AnnotatedString(output), phoneNumberTranslator)
    }
}