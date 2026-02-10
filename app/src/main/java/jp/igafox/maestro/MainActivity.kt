package jp.igafox.maestro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.igafox.maestro.ui.theme.MaestroTheme

private const val MAX_NAME_LENGTH = 15

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaestroTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GreetingScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var greeting by remember { mutableStateOf("") }
    val isError = name.length > MAX_NAME_LENGTH

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .semantics { testTagsAsResourceId = true }, // テストタグをリソースIDとして扱う(必須)
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("名前") },
            isError = isError,
            supportingText = {
                if (isError) {
                    Text("名前は${MAX_NAME_LENGTH}文字以内です")
                }
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .testTag("nameTextField"),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { greeting = "Hello, $name" },
            enabled = name.isNotBlank() && !isError,
            modifier = Modifier.testTag("greetButton"),
        ) {
            Text("挨拶する")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = greeting,
            fontSize = 24.sp,
            modifier = Modifier.testTag("greetingText"),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingScreenPreview() {
    MaestroTheme {
        GreetingScreen()
    }
}