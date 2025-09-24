package br.com.fiap.meuseguroauto.ui.screens.quote

import br.com.fiap.meuseguroauto.model.QuoteInput

fun calculateQuote(input: QuoteInput): Double {
    // Preço base = 4% do valor do veículo
    val vehiclePrice =
        if (input.vehiclePrice.isEmpty()) 0.0 else
            (input.vehiclePrice.toDouble()) / 100
    // O preco base será 4% do valor do veiculo
    val basePrice = vehiclePrice * 0.04
    // Aplica o fator do plano
    var total = basePrice * input.carInsurancePlan.factor
    // Ajuste pelo tipo de veículo
    total *= input.vehicleTypes.fator
    // Ajuste por idade do motorista
    total += when {
        input.age < 25 -> 200.0
        input.age in 25..65 -> 0.0
        else -> 100.0
    }
    // Ajuste pelo bônus (desconto)
    // cada ponto de bônus reduz 10 reais
    total -= input.discount
    // Garagem
    if (!input.hasGarage) {
        total += 150.0
    }
    // Uso comercial
    if (input.isCommercialUseVehicle) {
        total += 300.0
    }
    // Seguro de vida
    if (input.wantsLifeInsurance) {
        total += 500.0
    }
    // Limite mínimo de valor
    if (total < 0) total = 0.0
    return total
}