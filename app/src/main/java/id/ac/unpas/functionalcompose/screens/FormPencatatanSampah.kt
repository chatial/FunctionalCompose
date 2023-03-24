package id.ac.unpas.functionalcompose.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.benasher44.uuid.uuid4
import id.ac.unpas.functionalcompose.model.SetoranSampah
import id.ac.unpas.functionalcompose.persistences.SetoranSampahDao
import id.ac.unpas.functionalcompose.ui.theme.Purple700
import id.ac.unpas.functionalcompose.ui.theme.Teal200
import kotlinx.coroutines.launch
@Composable
fun FormPencatatanSampah(setoranSampahDao: SetoranSampahDao) {
    val Tanggal_beli = remember { mutableStateOf(TextFieldValue("")) }
    val Nama_Pembeli= remember { mutableStateOf(TextFieldValue("")) }
    val Merek_sepatu= remember { mutableStateOf(TextFieldValue("")) }
    val Notelephone = remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()) {
        OutlinedTextField(
            label = { Text(text = "Tanggal_beli") },
            value = Tanggal_beli.value,
            onValueChange = {
                Tanggal_beli.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            placeholder = { Text(text = "yyyy-mm-dd") }
        )
        OutlinedTextField(
            label = { Text(text = "Nama_Pembeli") },
            value = Nama_Pembeli.value,
            onValueChange = {
                Nama_Pembeli.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(capitalization =
            KeyboardCapitalization.Characters, keyboardType = KeyboardType.Text),
            placeholder = { Text(text = "Nama") }
        )
        OutlinedTextField(
            label = { Text(text = "Merek_sepatu") },
            value =  Merek_sepatu.value,
            onValueChange = {
                Merek_sepatu.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "Merek") }
        )
        OutlinedTextField(
            label = { Text(text = "Notelephone") },
            value = Notelephone.value,
            onValueChange = {
                Notelephone.value = it
            },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType =
            KeyboardType.Decimal),
            placeholder = { Text(text = "XXXX") }
        )
        val loginButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Purple700,
            contentColor = Teal200
        )
        val resetButtonColors = ButtonDefaults.buttonColors(
            backgroundColor = Teal200,
            contentColor = Purple700
        )
        Row (modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()) {
            Button(modifier = Modifier.weight(5f), onClick = {
                val id = uuid4().toString()
                val item = SetoranSampah(id, Tanggal_beli.value.text, Nama_Pembeli.value.text, Merek_sepatu.value.text, Notelephone.value.text)
                scope.launch {
                    setoranSampahDao.insertAll(item)
                }
                Tanggal_beli.value = TextFieldValue("")
                Nama_Pembeli.value = TextFieldValue("")
                Merek_sepatu.value = TextFieldValue("")
                Notelephone.value = TextFieldValue("")
            }, colors = loginButtonColors) {
                Text(
                    text = "Simpan",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
            Button(modifier = Modifier.weight(5f), onClick = {
                Tanggal_beli.value = TextFieldValue("")
                Nama_Pembeli.value = TextFieldValue("")
                Merek_sepatu.value = TextFieldValue("")
                Notelephone.value = TextFieldValue("")
            }, colors = resetButtonColors) {
                Text(
                    text = "Reset",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 18.sp
                    ), modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}