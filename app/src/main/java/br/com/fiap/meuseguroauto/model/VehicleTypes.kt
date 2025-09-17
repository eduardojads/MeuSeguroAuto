package br.com.fiap.meuseguroauto.model

enum class VehicleTypes(
    var label: String,
    var fator: Double
){
    CAR ("Carro", 1.0),

    MOTO ("Moto", 2.0),

    TRUCK ("Caminhão", 1.5)
}