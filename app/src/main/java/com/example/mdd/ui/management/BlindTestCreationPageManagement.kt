package com.example.mdd.ui.management

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults.itemShape
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mdd.R
import com.example.mdd.ui.elements.SliderDiscrete
import com.example.mdd.uimanager.DropDownOptionsBlindTest
import com.example.mdd.viewmodel.SliderViewModel
import com.example.mdd.viewmodel.ViewModelBlindTestOptionsDropdown

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun Blindtestmanagerpage(viewModelslider: SliderViewModel) {
        ///Par genre
        ///Par artist
        ///Par niveau de difficulté
        ///Nombres de questions
        val context = LocalContext.current
        //actions
        //Dropdown
        val viewModel : ViewModelBlindTestOptionsDropdown = viewModel()
        var selectedValueFromDropdown by remember { mutableStateOf("") }


        //Difficulté
        var selecteddiff by remember { mutableStateOf(0) }
        val options_segmentedbutton = listOf(context.getString(R.string.bt_diff1),context.getString(
            R.string.bt_diff2))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Blind test musical")
                Text(text = "Choississez les options de votre blind test")
                DropDownOptionsBlindTest(viewModel = viewModel,
                    onOptionSelected = { selectedOption -> selectedValueFromDropdown = selectedOption })
                //$selectedOption
                Text(
                    text = context.getString(R.string.bt_diff),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                SingleChoiceSegmentedButtonRow(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                    options_segmentedbutton.forEachIndexed { index, label ->
                        SegmentedButton(
                            shape = itemShape(index = index, count = options_segmentedbutton.size),
                            onClick = { selecteddiff = index },
                            //valeur que je récupère
                            selected = index == selecteddiff
                        ) {
                            Text(label)
                        }
                    }
                }
                Text(
                    text = context.getString(R.string.dropdown_bt_many),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                SliderDiscrete(viewModelslider)


                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = context.getString(R.string.blind_start))
                }
            }
        }
