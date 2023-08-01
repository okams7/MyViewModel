package com.example.myviewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myviewmodel.ui.theme.MyViewModelTheme

class MainActivity : ComponentActivity() {
    private val viewModel = MyViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyViewModelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainContent(viewModel: MyViewModel) {
    val title by viewModel.titleInputFlow.collectAsState()
    Content(title = title, onTitleChange = {
        viewModel.setTitle(it)
    }, onResetClicked = { viewModel.resetTitle() })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(
    title: String,
    onTitleChange: (String) -> Unit,
    onResetClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = "Title") },
            textStyle = MaterialTheme.typography.bodyMedium,
            singleLine = true
        )
        OutlinedButton(onClick = { onResetClicked() },
            modifier = Modifier.padding(16.dp)) {
            Text("Reset")
        }
    }
}