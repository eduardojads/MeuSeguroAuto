package br.com.fiap.meuseguroauto.utils

object Validators {
    fun emailValidator(email: String): String? {
        return if (email.isBlank()) {
            "E-mail obrigatório"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            "E-mail inválido"
        } else {
            null // válido
        }
    }

    fun phoneValidator(phone: String): String? {
        val digits = phone.filter { it.isDigit() }
        return if (digits.length in 10..11) null else
            "Telefone inválido"
    }
}