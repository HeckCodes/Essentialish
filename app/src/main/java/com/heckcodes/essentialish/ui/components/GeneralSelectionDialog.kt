package com.heckcodes.essentialish.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.heckcodes.essentialish.ui.theme.ColorPickerOptions
import kotlin.math.round


sealed class SelectionType<T> {
    class EnumType<T : Enum<T>> : SelectionType<T>()
    class DoubleType(
        val min: Double = 0.0,
        val max: Double = 1.0,
        val step: Double = 0.1
    ) : SelectionType<Double>()
    class ColorType : SelectionType<String>()
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
inline fun <reified T> GeneralSelectionDialog(
    title: String,
    description: String,
    current: T,
    type: SelectionType<T>,
    noinline label: (T) -> String = { it.toString() },
    crossinline onConfirm: (T) -> Unit,
    noinline onDismiss: () -> Unit
) {
    when (type) {
        is SelectionType.EnumType -> {
            val (selected, setSelected) = remember { mutableStateOf(current) }
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text(title) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(description)
                        (T::class.java.enumConstants as? Array<T>)?.forEach { value ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = selected == value,
                                    onClick = { setSelected(value) }
                                )
                                Text(label(value), modifier = Modifier.clickable { setSelected(value) })
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { onConfirm(selected) }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            )
        }
        is SelectionType.DoubleType -> {
            val (selected, setSelected) = remember { mutableDoubleStateOf(current as? Double ?: type.min) }
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text(title) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text(description)
                        Slider(
                            value = selected.toFloat(),
                            onValueChange = { setSelected(it.toDouble()) },
                            valueRange = type.min.toFloat()..type.max.toFloat(),
                            steps = ((type.max - type.min) / type.step).toInt() - 1
                        )
                        Text(label(round(selected*10) as T))
                    }
                },
                confirmButton = {
                    Button(onClick = { onConfirm(selected as T) }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            )
        }
        is SelectionType.ColorType -> {
            val (selected, setSelected) = remember { mutableStateOf((current as String).substring(1)) }
            AlertDialog(
                onDismissRequest = onDismiss,
                title = { Text(title) },
                text = {
                    Column {
                        Text(description, modifier = Modifier.padding(bottom = 16.dp))
                        val allColors = ColorPickerOptions.getAllSpectrumColors(numHuesPerColor = 8)

                        Row (
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.fillMaxWidth()
                        ){
                            allColors.forEach { (_, shades) ->
                                Column (horizontalAlignment = Alignment.CenterHorizontally) {
                                    shades.forEach { color ->
                                        Spacer(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .padding(
                                                    if (color.toArgb().toHexString().equals(selected, true)) 6.dp else 0.dp
                                                )
                                                .background(color)
                                                .clickable {
                                                    setSelected(color.toArgb().toHexString())
                                                }
                                        )
                                    }
                                }
                            }
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        onConfirm("#$selected" as T)
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}
