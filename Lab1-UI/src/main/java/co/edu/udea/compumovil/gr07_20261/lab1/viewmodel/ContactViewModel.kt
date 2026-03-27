package co.edu.udea.compumovil.gr07_20261.lab1.viewmodel

import androidx.lifecycle.ViewModel
import co.edu.udea.compumovil.gr07_20261.lab1.model.Contact
import co.edu.udea.compumovil.gr07_20261.lab1.model.ContactData
import co.edu.udea.compumovil.gr07_20261.lab1.model.PersonalData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class ContactViewModel: ViewModel() {
    private val _contact = MutableStateFlow(Contact())
    val contact: StateFlow<Contact> = _contact.asStateFlow()

    fun updateNombres(value: String){
        _contact.value = _contact.value.copy(
            personalData = _contact.value.personalData.copy(nombre = value)
        )
    }

    fun updateApellido(value: String){
        _contact.value = _contact.value.copy(
            personalData = _contact.value.personalData.copy(apellido = value)
        )
    }

    fun updateSexo(value: String) {
        _contact.value = _contact.value.copy(
            personalData = _contact.value.personalData.copy(sexo = value)
        )
    }

    fun updateFechaNacimiento(value: String) {
        _contact.value = _contact.value.copy(
            personalData = _contact.value.personalData.copy(fechaNacimiento = value)
        )
    }

    fun updateEscolaridad(value: String) {
        _contact.value = _contact.value.copy(
            personalData = _contact.value.personalData.copy(gradoEscolaridad = value)
        )
    }

    fun updateTelefono(value: String) {
        _contact.value = _contact.value.copy(
            contactData = _contact.value.contactData.copy(telefono = value)
        )
    }

    fun updateDireccion(value: String) {
        _contact.value = _contact.value.copy(
            contactData = _contact.value.contactData.copy(direccion = value)
        )
    }

    fun updateEmail(value: String) {
        _contact.value = _contact.value.copy(
            contactData = _contact.value.contactData.copy(email = value)
        )
    }

    fun updatePais(value: String) {
        _contact.value = _contact.value.copy(
            contactData = _contact.value.contactData.copy(pais = value)
        )
    }

    fun updateCiudad(value: String) {
        _contact.value = _contact.value.copy(
            contactData = _contact.value.contactData.copy(ciudad = value)
        )
    }

    fun isPersonalDataValid(): Boolean {
        val personalData = _contact.value.personalData

        return personalData.nombre.isNotBlank() &&
                personalData.apellido.isNotBlank() &&
                personalData.fechaNacimiento.isNotBlank()
    }

    fun isContactDataValid(): Boolean {
        val contactData = _contact.value.contactData

        return contactData.telefono.isNotBlank() &&
                contactData.email.isNotBlank() &&
                contactData.pais.isNotBlank()

    }

    fun logContactData() {
        val personalData = _contact.value.personalData
        val contactData = _contact.value.contactData

        android.util.Log.i("ContactLog", "══════════════════════════════")
        android.util.Log.i("ContactLog", "Información personal:")
        android.util.Log.i("ContactLog", "${personalData.nombre} ${personalData.apellido}")
        android.util.Log.i("ContactLog", personalData.sexo.ifBlank { "Sexo no especificado" })
        android.util.Log.i("ContactLog", "Nació el ${personalData.fechaNacimiento}")
        android.util.Log.i("ContactLog", personalData.gradoEscolaridad.ifBlank { "Escolaridad no especificada" })
        android.util.Log.i("ContactLog", "──────────────────────────────")
        android.util.Log.i("ContactLog", "Información de contacto:")
        android.util.Log.i("ContactLog", "Teléfono: ${contactData.telefono}")
        android.util.Log.i("ContactLog", "Dirección: ${contactData.direccion.ifBlank { "No especificada" }}")
        android.util.Log.i("ContactLog", "Email: ${contactData.email}")
        android.util.Log.i("ContactLog", "País: ${contactData.pais}")
        android.util.Log.i("ContactLog", "Ciudad: ${contactData.ciudad.ifBlank { "No especificada" }}")
        android.util.Log.i("ContactLog", "══════════════════════════════")
    }
}