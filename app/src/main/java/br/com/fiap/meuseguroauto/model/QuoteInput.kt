package br.com.fiap.meuseguroauto.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import br.com.fiap.meuseguroauto.QuoteForm

data class QuoteInput(
    val name: String = "",
    val email: String = "",
    val telefone: String = "",
    val carInsuransePlan: CarInsurancePlan = CarInsurancePlan.STANDARD,
    val vehicleTypes: VehicleTypes = VehicleTypes.CAR
)
