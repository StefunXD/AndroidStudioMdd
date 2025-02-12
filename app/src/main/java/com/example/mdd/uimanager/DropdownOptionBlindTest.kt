package com.example.mdd.uimanager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuBoxScope
import androidx.compose.material3.ExposedDropdownMenuDefaults.TrailingIcon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mdd.R
import com.example.mdd.viewmodel.ViewModelBlindTestOptionsDropdown
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
        /*Todo : Relocaliser ce code autre par */
        /*Todo : Optimiser les options de la liste */
        /*Todo : Optimiser les strings ne pas les laisser coder en dure */
fun DropDownOptionsBlindTest(viewModel: ViewModelBlindTestOptionsDropdown = viewModel(),
                             onOptionSelected: (String) -> Unit){
    ///Par genre
    ///Par artist
    ///Par niveau de difficulté
    ///Nombres de questions
    val context = LocalContext.current
    val options = listOf(context.getString(R.string.bt_general),context.getString(R.string.bt_genre),context.getString(R.string.bt_artiste))
    val selectedOptions by viewModel.selectedOption.collectAsState()
    var expanded by remember { mutableStateOf(false) }

    Column (modifier = Modifier.padding(16.dp)) {
        Text(text = "Options")
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {expanded = !expanded}
        ){
            androidx.compose.material3.OutlinedTextField(
                value = selectedOptions,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .fillMaxWidth()

            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = {expanded = false}) {
                options.forEach{ selectionOption -> DropdownMenuItem(
                    text = { Text(text = selectionOption) },
                    onClick = {
                        viewModel.updateSelectedOption(selectionOption)
                            //onValueChange(selectionOption)
                        onOptionSelected(selectionOption)
                        expanded = false
                    })
                }

            }
        }
        when(selectedOptions){
            context.getString(R.string.bt_general) -> {

            }
            context.getString(R.string.bt_genre) -> {

            }
            context.getString(R.string.bt_artiste) -> {

            }

        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
private fun ExposedDropdownMenuBoxScope.OutlinedTextField(
    value: String,
    onValueChange: () -> Unit,
    readOnly: Boolean,
    trailingIcon: @Composable () -> Unit,
    function: () -> Unit
) {
    TODO("Not yet implemented")
}
