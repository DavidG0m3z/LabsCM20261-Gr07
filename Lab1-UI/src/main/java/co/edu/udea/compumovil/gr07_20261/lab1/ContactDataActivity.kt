package co.edu.udea.compumovil.gr07_20261.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import co.edu.udea.compumovil.gr07_20261.lab1.ui.ContactDataScreen
import co.edu.udea.compumovil.gr07_20261.lab1.ui.theme.Labs20261Gr07Theme
import co.edu.udea.compumovil.gr07_20261.lab1.viewmodel.ContactViewModel


class ContactDataActivity : ComponentActivity() {
    private  val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.extras?.let { extras ->
            viewModel.updateNombres(extras.getString("nombres", ""))
            viewModel.updateApellido(extras.getString("apellidos", ""))
            viewModel.updateSexo(extras.getString("sexo", ""))
            viewModel.updateFechaNacimiento(extras.getString("fechaNacimiento", ""))
            viewModel.updateEscolaridad(extras.getString("escolaridad", ""))
        }
        setContent {
            Labs20261Gr07Theme {
                ContactDataScreen(
                    viewModel = viewModel,
                    onSiguiente = {
                        finish()
                    }
                )
            }
        }
    }
}