package co.edu.udea.compumovil.gr07_20261.lab1.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
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
import co.edu.udea.compumovil.gr07_20261.lab1.model.PersonalData
import co.edu.udea.compumovil.gr07_20261.lab1.viewmodel.ContactViewModel
import java.util.Calendar

@Composable
@OptIn(ExperimentalMaterial3Api::class)
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

    val gradoEscikarudadOpciones = context.resources
        .getStringArray(R.array.escolaridad_array)
        .toList()



}