package io.bitrise.androidtodokotlinandcomposesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.bitrise.androidtodokotlinandcomposesample.ui.theme.AndroidToDoKotlinAndComposeSampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidToDoKotlinAndComposeSampleTheme {
                TodoApp()
            }
        }
    }
}

@Composable
fun TodoApp() {
    val tasks = remember { mutableStateListOf<String>() }
    val newTask = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            color = MaterialTheme.colors.background
        ) {
            TextField(
                value = newTask.value,
                onValueChange = { newTask.value = it },
                label = { Text("New Task") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Button(
            onClick = {
                if (newTask.value.isNotBlank()) {
                    tasks.add(newTask.value)
                    newTask.value = ""
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Text("Add Task")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(tasks.size) { index ->
                TaskItem(task = tasks[index])
            }
        }
    }
}

@Composable
fun TaskItem(task: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = false,
            onCheckedChange = { /* Mark task as completed */ },
            modifier = Modifier.padding(end = 16.dp)
        )
        Text(text = task)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidToDoKotlinAndComposeSampleTheme {
        TodoApp()
    }
}
