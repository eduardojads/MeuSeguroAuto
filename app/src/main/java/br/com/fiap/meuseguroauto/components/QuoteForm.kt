package br.com.fiap.meuseguroauto.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.fiap.meuseguroauto.R
import br.com.fiap.meuseguroauto.components.transformations.PhoneVisualTransformation
import br.com.fiap.meuseguroauto.model.CarInsurancePlan
import br.com.fiap.meuseguroauto.model.QuoteInput
import br.com.fiap.meuseguroauto.model.VehicleTypes
import br.com.fiap.meuseguroauto.utils.Validators

@Composable
fun QuoteForm(
    state: QuoteInput,
    onChange: (QuoteInput) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            . fillMaxWidth()
            . verticalScroll(rememberScrollState ())
            . padding(16.dp),
        verticalArrangement = Arrangement.spacedBy( 16.dp)
    ) {
        CustomLabel (
            stringResource (R.string.label_customer_data)
        )
        CustomTextInput(
            value = state.name,
            onValueChange = { onChange(state.copy(name = it))
            },
            leadingIcon = Icons.Default.PersonOutline,
            label = stringResource(R.string.label_name)
        )
        CustomTextInput(
            value = state.email,
            onValueChange = { onChange(state.copy(email = it))
            },
            leadingIcon = Icons.Default.AlternateEmail,
            label = stringResource(R.string.label_email),
            keyboardType = KeyboardType.Email,
            validator = Validators::emailValidator

        )

        CustomTextInput(
            value = state.telefone,
            onValueChange = { onChange(state.copy(telefone = it)) },
            label = stringResource(R.string.label_phone),
            validator = Validators::phoneValidator,
            leadingIcon = Icons.Default.Phone,
            visualTransformationCustom = PhoneVisualTransformation()
        )

        CustomDropdown(
            label = "Plano",
            options = CarInsurancePlan.entries,
            selected = state.carInsurancePlan,
            onSelect = {onChange(state.copy(carInsurancePlan = it))},
            optionLabel = {it.label}
        )

        CustomDropdown(
            label = "Veículo",
            options = VehicleTypes.entries,
            selected = state.vehicleTypes,
            onSelect = {onChange(state.copy(vehicleTypes = it))},
            optionLabel = {it.label}
        )

        CustomSlider(
            label = stringResource(R.string.label_age),
            value = state.age,
            valueRange = 18..80,
            onValueChange = { onChange(state.copy(age = it)) }
        )

        CustomSlider(
            label = stringResource(R.string.label_discount),
            value = state.discount,
            valueRange = 0..10,
            onValueChange = { onChange(state.copy(discount = it)) }
        )

        CustomSwitch(
            label = stringResource(R.string.label_has_garage),
            checked = state.hasGarage,
            onCheckedChange = {
                onChange(
                    state.copy(hasGarage = it)
                )
            }
        )

        CustomSwitch(
            label = stringResource(R.string.label_comercial_use),
            checked = state.isCommercialUseVehicle,
            onCheckedChange = {
                onChange(
                    state.copy(isCommercialUseVehicle = it)
                )
            }
        )

        // Seguro de vida
        CustomCheckbox(
            label = stringResource(R.string.label_life_insurance),
            checked = state.wantsLifeInsurance,
            onCheckedChange = {
                onChange(
                    state.copy(wantsLifeInsurance = it)
                )
            }
        )

        CustomCheckbox(
            label = stringResource(R.string.label_marketing_communication),
            checked = state.acceptsMarketingCommunication,
            onCheckedChange = {
                onChange(
                    state.copy(acceptsMarketingCommunication = it)
                )
            }
        )
    }
}