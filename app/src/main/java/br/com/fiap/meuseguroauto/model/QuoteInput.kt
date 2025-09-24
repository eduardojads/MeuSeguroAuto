package br.com.fiap.meuseguroauto.model

data class QuoteInput(
    val name: String = "",
    val email: String = "",
    val telefone: String = "",
    val vehiclePrice: String = "",
    val carInsurancePlan: CarInsurancePlan = CarInsurancePlan.STANDARD,
    val vehicleTypes: VehicleTypes = VehicleTypes.CAR,
    val age: Int = 30,
    val discount: Int = 0,
    val hasGarage: Boolean = true,
    val isCommercialUseVehicle: Boolean = false,
    val wantsLifeInsurance: Boolean = false,
    val acceptsMarketingCommunication: Boolean = false

)
