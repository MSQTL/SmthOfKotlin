package com.example.jatpackcompose_begin


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jatpackcompose_begin.ui.theme.JatpackCompose_BeginTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JatpackCompose_BeginTheme {
                // A surface container using the 'background' color from the theme
                Column{
                    Surface(
                        modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth(1f),
                        color = MaterialTheme.colors.background
                    ) {
                        Registration()
                    }
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello, $name!")
}

@Composable
fun LogIn() {
    Column {
        val login = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        Text(text = "Логин")
        TextField(login.value, onValueChange = { login.value = it })
        Text(text = "Пароль")
        TextField(password.value, onValueChange = { password.value = it })
        Button(onClick = {
            println("${login.value}, ${password.value}")
            login.value = ""
            password.value = ""
        }) {
            Text(text = "Войти")
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyListItem(
    position: Int = 0,
    color: Color = Color.Gray,
) {
    val selected = remember { mutableStateOf(true) }
    val openDialog = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth(fraction = 0.9f)
            .padding(4.dp)
            .height(42.dp)
//            .selectable(
//                selected = selected.value,
//                onClick = { selected.value = !selected.value }
//            )
    )
    {
        Box(
            modifier = Modifier
                .aspectRatio(1.0f, matchHeightConstraintsFirst = true)
                .clip(CircleShape)
                .background(Color.Green)
        )
        {
            Text("Картинка", fontSize = 5.sp, modifier = Modifier.align(Alignment.Center))
        }


        Text("Какой-то текст", modifier = Modifier
            .align(Alignment.CenterVertically)
            .padding(10.dp, 0.dp)
            .selectable(
                selected = selected.value,
                onClick = {
                    selected.value = !selected.value
                    openDialog.value = true
                }
            ))
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = { Text(text = "ИНФО") },
                text = { Text("КАКАЯ-ТО ИНФОРМАЦИЯ") },
                buttons = {
                    Button(
                        onClick = { openDialog.value = false },
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(10.dp)
                    ) {
                        Text("ЗАКРЫТЬ", fontSize = 18.sp)
                    }
                }
            )
        }

    }
}


@Composable
fun ListOfElements() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        items(100) { position ->

            println("Build item at position $position")

            Row {
                MyListItem(
                    position = position,
                    color = Color.Gray,
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}


@Composable
fun Registration(){
    Column {
        val name = remember { mutableStateOf("") }
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val openDialog = remember { mutableStateOf(0) }
        Text(text = "Имя:")
        TextField(name.value, onValueChange = { name.value = it })
        Text(text = "Электронная почта:")
        TextField(email.value, onValueChange = { email.value = it })
        Text(text = "Пароль (больше 8 символов):")
        TextField(password.value, onValueChange = { password.value = it })
        Button(onClick = {
            if (password.value.length > 8 && name.value != "" && "@" in email.value){
                openDialog.value = 1
            }
            else{
                openDialog.value = 2
            }
            name.value = ""
            email.value = ""
            password.value = ""
        }) {
            Text(text = "Войти")
        }
        if (openDialog.value == 1) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = 0
                },
                title = { Text(text = "ИНФО") },
                text = { Text("Вы успешно зарегистрированы!") },
                buttons = {
                    Button(
                        onClick = { openDialog.value = 0 },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text("ЗАКРЫТЬ", fontSize = 18.sp)
                    }
                }
            )
        }
        if (openDialog.value == 2){
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = 0
                },
                title = { Text(text = "ИНФО") },
                text = { Text("Данные указаны неверно!") },
                buttons = {
                    Button(
                        onClick = { openDialog.value = 0 },
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        Text("ПОВТОРИТЬ", fontSize = 18.sp)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JatpackCompose_BeginTheme {
        Registration()
    }
}


