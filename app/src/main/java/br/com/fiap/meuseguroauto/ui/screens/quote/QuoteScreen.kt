package br.com.fiap.meuseguroauto.ui.screens.quote

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.fiap.meuseguroauto.components.QuoteForm
import br.com.fiap.meuseguroauto.model.CarInsurancePlan
import br.com.fiap.meuseguroauto.model.QuoteInput
import br.com.fiap.meuseguroauto.model.VehicleTypes
import br.com.fiap.meuseguroauto.ui.theme.MeuSeguroAutoTheme

@Composable
fun QuoteScreen(modifier: Modifier = Modifier) {

    var input by rememberSaveable(
        stateSaver = Saver(
            save = {
                listOf(
                    it.name,
                    it.email,
                    it.telefone,
                    it.vehiclePrice,
                    it.carInsurancePlan,
                    it.vehicleTypes,
                    it.age,
                    it.discount,
                    it.hasGarage,
                    it.isCommercialUseVehicle,
                    it.wantsLifeInsurance,
                    it.acceptsMarketingCommunication
                )
            },
            restore = {
                QuoteInput(
                    name = it[0] as String,
                    email = it[1] as String,
                    telefone = it[2] as String,
                    vehiclePrice = it[3] as String,
                    carInsurancePlan = CarInsurancePlan.entries[it[4] as Int],
                    vehicleTypes = VehicleTypes.entries[it[5] as Int],
                    age = it[6] as Int,
                    discount = it[7] as Int,
                    hasGarage = it[8] as Boolean,
                    isCommercialUseVehicle = it[9] as Boolean,
                    wantsLifeInsurance = it[10] as Boolean,
                    acceptsMarketingCommunication = it[11] as Boolean

                )
            })
    ) { mutableStateOf(QuoteInput()) }
    QuoteForm(
        state = input,
        onChange = { input = it },
        modifier = modifier
    )

    val price = rememberSaveable(input) {
        calculateQuote(input)
    }
}

@Preview(showBackground = true)
@Composable
fun QuoteScreenPreview(modifier: Modifier = Modifier) {
    MeuSeguroAutoTheme { br.com.fiap.meuseguroauto.ui.screens.quote.QuoteScreen() }
}