 package br.com.fiap.meuseguroauto

import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.meuseguroauto.components.CustomLabel
import br.com.fiap.meuseguroauto.components.CustomTextInput
import br.com.fiap.meuseguroauto.components.transformations.PhoneVisualTransformation
import br.com.fiap.meuseguroauto.model.QuoteInput
import br.com.fiap.meuseguroauto.ui.theme.MeuSeguroAutoTheme
import br.com.fiap.meuseguroauto.utils.Validators

 class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MeuSeguroAutoTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Row {
                                    Image(
                                        painter = painterResource(R.drawable.ic_top_app_bar),
                                        contentDescription = "Logo do App",
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(CircleShape),
                                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        getString(R.string.app_name),
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                            }
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    QuoteScreen(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun QuoteScreen(modifier: Modifier = Modifier) {
    var input by rememberSaveable(
        stateSaver = Saver(
            save = {
                listOf(
                    it.name,
                    it.email
                )
            },
            restore = {
                QuoteInput(
                    name = it[0],
                    email = it[1],
                    telefone = it[2]
                )
            })
    ) { mutableStateOf(QuoteInput()) }
    QuoteForm(
        state = input,
        onChange = { input = it },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun QuoteScreenPreview(modifier: Modifier = Modifier) {
    MeuSeguroAutoTheme { QuoteScreen() }
}


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
    }
}

