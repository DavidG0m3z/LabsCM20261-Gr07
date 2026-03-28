package co.edu.udea.compumovil.gr07_20261.lab1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import co.edu.udea.compumovil.gr07_20261.lab1.ui.PersonalDataScreen
import co.edu.udea.compumovil.gr07_20261.lab1.ui.theme.Labs20261Gr07Theme
import co.edu.udea.compumovil.gr07_20261.lab1.viewmodel.ContactViewModel


class PersonalDataActivity : ComponentActivity ()  {
     private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Labs20261Gr07Theme() {
                PersonalDataScreen(
                    viewModel = viewModel,
                    onSiguiente = {
                        val personal = viewModel.contact.value.personalData

                        val intent = Intent(this, ContactDataActivity::class.java).apply {
                            putExtra("nombres", personal.nombre)
                            putExtra("apellidos", personal.apellido)
                            putExtra("sexo", personal.sexo)
                            putExtra("fechaNacimiento", personal.fechaNacimiento)
                            putExtra("escolaridad", personal.gradoEscolaridad)
                        }
                        startActivity(intent)
                    }
                )
            }
        }
    }
}