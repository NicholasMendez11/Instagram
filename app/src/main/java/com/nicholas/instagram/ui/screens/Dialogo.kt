package com.nicholas.instagram.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.progressbartest.users.AllUsers
import com.example.progressbartest.users.User


@Composable
fun MainPart() {
    var showAlert by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { showAlert = true }) {
            Text(text = "Presioname y veras magia.")
        }
    }
    MySimpleCustomDialog(showAlert) { showAlert = false }
}


@Composable
fun NormalDialog(showAlert: Boolean, onClick: () -> Unit) {

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { onClick() }, //Este dismis es para cuando el usuario clickee fuera del dialogo
            title = { Text(text = "Titulo") },
            text = { Text(text = "Hola soy una descripcion super guay :)") },
            confirmButton = {
                TextButton(onClick = { onClick() }) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onClick() }) {
                    Text(text = "Decline")
                }
            },
        )

    }

}

//El simple Dialog es realmente un Dialag que puedes customizar tanto como quieras 
@Composable
fun MySimpleCustomDialog(showAlert: Boolean, onClick: () -> Unit) {
    if (showAlert) {
        Dialog(onDismissRequest = { onClick() }) {
            Card() {
                Column(
                    Modifier
                        .background(Color.White)
                        .padding(24.dp)
                        .fillMaxWidth()
                ) {
                    MyTitle("Set Backup account")
                    AllUsers.forEach { user ->
                        MyItems(user)
                    }
                    NewUser()
                }

            }
        }


    }
}

@Composable
fun MyTitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun NewUser() {
    Row(
        Modifier
            .padding(top = 20.dp)
            .clickable { }, verticalAlignment = CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = Color.White,
            contentDescription = "Add new user",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50))
                .background(Color.Gray)
                .size(50.dp)
        )
        Text(
            text = "Add a new user",
            modifier = Modifier.padding(horizontal = 16.dp),
            maxLines = 1,
        )

    }
}

@Composable
fun MyItems(user: User) {
    Row(
        Modifier
            .padding(top = 20.dp)
            .clickable { }, verticalAlignment = CenterVertically
    ) {
        Image(
            painter = painterResource(id = user.profilepicId),
            contentDescription = "Profile pic",
            modifier = Modifier
                .clip(shape = RoundedCornerShape(50))
                .size(50.dp)
        )
        Text(
            text = user.email,
            modifier = Modifier.padding(horizontal = 8.dp),
            maxLines = 1,
            color = Color.Gray
        ) //max line me ayuda definir la cantidad de lineas
    }
}

