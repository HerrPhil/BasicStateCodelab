package com.phone.basicstatecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.phone.basicstatecodelab.ui.theme.BasicStateCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicStateCodelabTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WellnessScreen()
                }
            }
        }
    }
}

@Composable
fun WellnessScreen(
    modifier: Modifier = Modifier,
    wellnessViewModel: WellnessViewModel = viewModel()
) {
//    WaterCounter(modifier)
    Column(modifier = modifier) {

        StatefulCounter(modifier)

//        val list = remember { getWellnessTasks().toMutableStateList() }
//
//        // alternative list initialization
//        val listxxx = remember {
//
//            mutableStateListOf<WellnessTask>()
//                .apply {
//                    addAll(getWellnessTasks())
//                }
//
//        }

        WellnessTasksList(
            list = wellnessViewModel.tasks,
//            list = list,
            onCheckedTask = { task, checked ->
                wellnessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task -> wellnessViewModel.remove(task) }
//            onCloseTask = { task -> list.remove(task) }
        )

    }
}



@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 64.dp
        )
    ) {

        if (count > 0) {
//            var showTask by remember { mutableStateOf(true) }
//            if (showTask) {
//                WellnessTaskItem(
//                    onClose = { showTask = false },
//                    taskName = "Have you taken your 15 minute walk today?"
//                )
//            }
            // This text is present if the button has been clicked
            // at least once; absent otherwise
            Text(
                "You have had $count glasses.",
//            modifier = modifier.padding(16.dp)
            )
        }

//        Row(modifier = Modifier.padding(8.dp)) {

        Button(
            onClick = { count++ },
            enabled = count < 10
        ) {
            Text("Add One")
        }

//            Button(onClick = { count = 0}, modifier = Modifier.padding(start = 8.dp)) {
//                Text("Clear water count")
//            }

//        }

    }
}

// Split WaterCounter into StatefulCounter and StatelessCounter


@Composable
fun StatefulCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableStateOf(0) }
    StatelessCounter(count, onIncrement = { count++ }, modifier)
}

@Composable
fun StatelessCounter(
    count: Int,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(
            horizontal = 16.dp,
            vertical = 64.dp
        )
    ) {
        if (count > 0) {
            // This text is present if the button has been clicked
            // at least once; absent otherwise
            Text(
                "You have had $count glasses.",
            )
        }
        Button(
            onClick = onIncrement,
            enabled = count < 10
        ) {
            Text("Add One")
        }
    }


}

// This method is unnecessary with the ViewModel. CheckBox state will be hoisted to the List level.
// This is the Stateful Composable item, unique by method overloading
//@Composable
//fun WellnessTaskItem(taskName: String, onClose: () -> Unit, modifier: Modifier = Modifier) {
//    var checkedState by rememberSaveable { mutableStateOf(false) }
//    WellnessTaskItem(
//        taskName = taskName,
//        checked = checkedState,
//        onCheckedChange = { newValue -> checkedState = newValue },
//        onClose = onClose,
////        onClose = {}, // implement later!!!!!!!!!!
//        modifier = modifier
//    )
//}

// This is the Stateless Composable item
@Composable
fun WellnessTaskItem(
    taskName: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = taskName
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun WellnessScreenPreview() {
    BasicStateCodelabTheme {
        WellnessScreen(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
private fun WaterCounterPreview() {
    BasicStateCodelabTheme {
        WaterCounter(modifier = Modifier)
    }
}

@Preview(showBackground = true)
@Composable
private fun WellnessTaskItemPreview() {
    BasicStateCodelabTheme {
        WellnessTaskItem(
            taskName = "exercise",
            checked = true,
            onCheckedChange = {},
            onClose = {},
            modifier = Modifier)
    }
}