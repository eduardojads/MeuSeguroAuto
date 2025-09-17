package br.com.fiap.meuseguroauto.model

enum class CarInsurancePlan(
    val label: String,
    val factor: Double
) {

    ESSENTIAL ("Essencial", 1.0),

    STANDARD ("Padrão",1.2),

    FULL ("Completo", 1.5)

}