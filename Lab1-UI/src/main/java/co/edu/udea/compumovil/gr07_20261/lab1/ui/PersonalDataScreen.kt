package co.edu.udea.compumovil.gr07_20261.lab1.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr07_20261.lab1.R
import co.edu.udea.compumovil.gr07_20261.lab1.viewmodel.ContactViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataScreen(
    viewModel: ContactViewModel,
    onSiguiente: () -> Unit
){
    val context = LocalContext.current

    var nombre by remember { mutableStateOf(viewModel.contact.value.personalData.nombre) }
    var apellido by remember { mutableStateOf(viewModel.contact.value.personalData.apellido) }
    var sexo by remember { mutableStateOf(viewModel.contact.value.personalData.sexo) }
    var fechaNacimiento by remember { mutableStateOf(viewModel.contact.value.personalData.fechaNacimiento) }
    var gradoEscolaridad by remember { mutableStateOf(viewModel.contact.value.personalData.gradoEscolaridad) }

    var nombreError by remember { mutableStateOf(false) }
    var apellidoError by remember { mutableStateOf(false) }
    var fechaError by remember { mutableStateOf(false) }

    var spinnerExpanded by remember { mutableStateOf(false) }

    val gradoEscolaridadOpciones = context.resources
        .getStringArray(R.array.escolaridad_array)
        .toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        //-- titulo --//
        Text(
            text = stringResource(R.string.title_personal_data),
            style = MaterialTheme.typography.headlineSmall
        )

        //-- Nombre --//
        OutlinedTextField(
            value = nombre,
            onValueChange = {
                nuevoValor -> nombre = nuevoValor
                viewModel.updateNombres(nuevoValor)
                nombreError = false
            },

            label = { Text(stringResource(R.string.hint_nombres)) },
            isError = nombreError,
            supportingText = {
                if (nombreError) Text(stringResource(R.string.error_campo_obligatorio))
            },

            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Next
            ),

            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )


        //--Apellido--//
        OutlinedTextField(
            value = apellido,
            onValueChange = { nuevoValor ->
                apellido = nuevoValor
                viewModel.updateApellido(nuevoValor)
                apellidoError = false
            },
            label = { Text(stringResource(R.string.hint_apellidos)) },
            isError = apellidoError,
            supportingText = {
                if (apellidoError) Text(stringResource(R.string.error_campo_obligatorio))
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                keyboardType = KeyboardType.Text,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        //--Sexo--//
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(stringResource(R.string.label_sexo))

            RadioButton(
                selected = sexo == "Hombre",
                onClick = {
                    sexo = "Hombre"
                    viewModel.updateSexo("Hombre")
                }
            )
            Text(stringResource(R.string.radio_hombre))

            RadioButton(
                selected = sexo == "Mujer",
                onClick = {
                    sexo = "Mujer"
                    viewModel.updateSexo("Mujer")
                }
            )
            Text(stringResource(R.string.radio_mujer))
        }

        //--FechaNacimiento--//
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(stringResource(R.string.label_fecha_nacimiento))

            Text(
                text = fechaNacimiento.ifBlank {
                    stringResource(R.string.fecha_no_seleccionada)
                },
                color = if (fechaError) MaterialTheme.colorScheme.error
                else MaterialTheme.colorScheme.onSurface
            )

            Button(onClick = {
                val cal = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, day ->
                        val fecha = "%02d/%02d/%04d".format(day, month + 1, year)
                        fechaNacimiento = fecha
                        viewModel.updateFechaNacimiento(fecha)
                        fechaError = false
                    },
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Text(stringResource(R.string.btn_cambiar_fecha))
            }
        }

        if (fechaError) {
            Text(
                text = stringResource(R.string.error_campo_obligatorio),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        //--GradoEscolaridad--//
        ExposedDropdownMenuBox(
            expanded = spinnerExpanded,
            onExpandedChange = { spinnerExpanded = !spinnerExpanded }
        ) {
            OutlinedTextField(
                value = gradoEscolaridad.ifBlank { gradoEscolaridadOpciones.first() },
                onValueChange = {},
                readOnly = true,
                label = { Text(stringResource(R.string.label_escolaridad)) },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = spinnerExpanded)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor(MenuAnchorType.PrimaryNotEditable)
            )
            ExposedDropdownMenu(
                expanded = spinnerExpanded,
                onDismissRequest = { spinnerExpanded = false }
            ) {
                gradoEscolaridadOpciones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            gradoEscolaridad = opcion
                            viewModel.updateEscolaridad(opcion)
                            spinnerExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        //--BotonSiguiente--//
        Button(
            onClick = {
                nombreError = nombre.isBlank()
                apellidoError = apellido.isBlank()
                fechaError = fechaNacimiento.isBlank()

                if (viewModel.isPersonalDataValid()) {
                    onSiguiente()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(R.string.btn_siguiente))
        }

    }
}