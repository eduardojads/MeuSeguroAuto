package br.com.fiap.meuseguroauto.components.transformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.NumberFormat
import java.util.Locale

class CurrencyVisualTransformation :
    VisualTransformation {
    override fun filter(text: AnnotatedString):
            TransformedText {
        // Remove tudo que não é número
        val digits = text.text.filter { it.isDigit() }
        // Converte para número em centavos
        val value = if (digits.isEmpty()) 0L else
            digits.toLong()
        // Formata com R$ e vírgula
        val formatted =
            NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                .format(value / 100.0)
        // Mapeamento do cursor
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset:
                                               Int): Int {
                // Cursor sempre no final
                return formatted.length
            }
            override fun transformedToOriginal(offset:
                                               Int): Int {
                return digits.length
            }
        }
        return TransformedText(AnnotatedString(formatted), offsetMapping)
    }
}