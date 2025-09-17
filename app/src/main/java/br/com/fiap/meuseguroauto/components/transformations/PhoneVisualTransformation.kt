package br.com.fiap.meuseguroauto.components.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PhoneVisualTransformation :
    VisualTransformation {
    override fun filter(text: AnnotatedString):
            TransformedText {
        // Remove qualquer caractere que não seja número
        val digits = text.text.filter { it.isDigit() }
        // Formata no padrão (XX) XXXX-XXXX ou (XX) XXXXX-XXXX
        val formatted = buildString {
            for (i in digits.indices) {
                when (i) {
                    0 -> append("(")
                    2 -> append(") ")
                    6 -> append("-")
                }
                append(digits[i])
            }
        }
        // Mapeamento do cursor
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                // O cursor vai sempre para o final do que já foi digitado
                return formatted.length
            }

            override fun transformedToOriginal(offset: Int): Int {
                // Cursor no final → volta para o tamanho real dos dígitos
                return digits.length
            }
        }
        return TransformedText(
            AnnotatedString(formatted),
            offsetMapping
        )
    }
}