package co.edu.udea.compumovil.gr07_20261.lab1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20261.lab1.R
import co.edu.udea.compumovil.gr07_20261.lab1.viewmodel.ContactViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactDataScreen(
    viewModel: ContactViewModel,
    onSiguiente: () -> Unit
){
    val paises = listOf(
        "Colombia", "Argentina", "Estados Unidos"
    )

    val ciudadesColombia = listOf(
        "Medellin", "Bogota", "Cali", "Medellin"
    )

    var telefono by remember { mutableStateOf(viewModel.contact.value.contactData.telefono) }
    var direccion by remember { mutableStateOf(viewModel.contact.value.contactData.direccion) }
    var email by remember { mutableStateOf(viewModel.contact.value.contactData.email) }
    var pais by remember { mutableStateOf(viewModel.contact.value.contactData.pais) }
    var ciudad by remember { mutableStateOf(viewModel.contact.value.contactData.ciudad) }

    var telefonoError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var emailFormatoError by remember { mutableStateOf(false) }
    var paisError by remember { mutableStateOf(false) }

    var paisExpanded by remember { mutableStateOf(false) }
    var ciudadExpanded by remember { mutableStateOf(false) }

    val paisesFiltrados = paises.filter {
        it.contains(pais, ignoreCase = true) && pais.isNotBlank()
    }
    val ciudadesFiltradas = ciudadesColombia.filter {
        it.contains(ciudad, ignoreCase = true) && ciudad.isNotBlank()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        //--Titulo--//
        Text(
            text = stringResource(R.string.title_contact_data),
            style = MaterialTheme.typography.headlineSmall
        )

        //--Telefono--//
        OutlinedTextField(
            value = telefono,
            onValueChange = {
                nuevoValor -> telefono = nuevoValor
                viewModel.updateTelefono(nuevoValor)
                telefonoError = false
            },
            label = { Text(stringResource(R.string.hint_telefono)) },
            isError = telefonoError,
            supportingText = {
                if (telefonoError) Text(stringResource(R.string.error_campo_obligatorio))
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        //--Email--//
        OutlinedTextField(
            value = email,
            onValueChange = {
                nuevoValor -> email = nuevoValor
                viewModel.updateEmail(nuevoValor)
                emailError = false
                emailFormatoError = false
            },
            label = { Text(stringResource(R.string.hint_email)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        //--Pais--//
        ExposedDropdownMenuBox(
            expanded = paisExpanded && paisesFiltrados.isNotEmpty(),
            onExpandedChange = { paisExpanded = it }
        ) {
            OutlinedTextField(
                value = pais,
                onValueChange = {
                    nuevoValor -> pais = nuevoValor
                    viewModel.updatePais(nuevoValor)
                    paisError = false
                    paisExpanded = true
                },
                label = { Text(stringResource(R.string.hint_pais)) },
                isError = paisError,
                supportingText = {
                    if (paisError) Text(stringResource(R.string.error_campo_obligatorio))
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    autoCorrectEnabled = false,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable),
                singleLine = true
            )

            if (paisesFiltrados.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = paisExpanded,
                    onDismissRequest = { paisExpanded = false }
                ) {
                    paisesFiltrados.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                pais = opcion
                                viewModel.updatePais(opcion)
                                paisExpanded = false
                            }
                        )
                    }
                }
            }
        }

        //--Ciudad--//
        ExposedDropdownMenuBox(
            expanded = ciudadExpanded && ciudadesFiltradas.isNotEmpty(),
            onExpandedChange = { ciudadExpanded = it }
        ) {
            OutlinedTextField(
                value = ciudad,
                onValueChange = {
                    nuevoValor -> ciudad = nuevoValor
                    viewModel.updateCiudad(nuevoValor)
                    ciudadExpanded = true
                },
                label = { Text(stringResource(R.string.hint_ciudad)) },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    keyboardType = KeyboardType.Text,
                    autoCorrectEnabled = false,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryEditable),
                singleLine = true
            )
            if (ciudadesFiltradas.isNotEmpty()) {
                ExposedDropdownMenu(
                    expanded = ciudadExpanded,
                    onDismissRequest = { ciudadExpanded = false }
                ) {
                    ciudadesFiltradas.forEach { opcion ->
                        DropdownMenuItem(
                            text = { Text(opcion) },
                            onClick = {
                                ciudad = opcion
                                viewModel.updateCiudad(opcion)
                                ciudadExpanded = false
                            }
                        )
                    }
                }
            }
        }

        //--Direccion--//
        OutlinedTextField(
            value = direccion,
            onValueChange = { nuevoValor ->
                direccion = nuevoValor
                viewModel.updateDireccion(nuevoValor)
            },
            label = { Text(stringResource(R.string.hint_direccion)) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                telefonoError = telefono.isBlank()
                paisError = pais.isBlank()

                if (viewModel.isContactDataValid()) {
                    viewModel.logContactData()
                    onSiguiente()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(R.string.btn_siguiente))
        }

    }

}