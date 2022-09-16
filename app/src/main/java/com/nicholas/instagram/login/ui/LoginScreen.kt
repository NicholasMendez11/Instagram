package com.nicholas.instagram.login.ui
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nicholas.instagram.R
//Eso es el View. O la vista, la Interfaz pues. Se supone que no debemo tener logica aqui.
//Nos suscribimos al view model (que va en esta mismo directorio) y desde ahi se ejecuta la logica  que se necesite aqui.
// (manejo de estados, funciones, etc.) Aqui podemos crear variables suscritas al view model, que recojan esos datos.
@Composable
fun Login(loginViewModel: LoginViewModel) {

    Box(modifier = Modifier.fillMaxSize()) {

        Body(Modifier.align(Alignment.Center), loginViewModel)
        Footer(Modifier.align(Alignment.BottomCenter))

    }
}

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {

    val email: String by loginViewModel.email.observeAsState(initial = "") //Suscrito al live data
    val password: String by loginViewModel.password.observeAsState(initial = "")//Suscrito al live data del view model.
    val isLogin: Boolean by loginViewModel.isLogin.observeAsState(initial = false)

    Column(
        modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeadLogo()
        EntryUser(email) {
            loginViewModel.onLoginChanged(it, password)

        }
        EntryPassword(password) {
            loginViewModel.onLoginChanged(email, it)
        }
        PasswordReset()
        LogInButton(isLogin)
        DividerSection()
        FacebookConnect()


    }
}

@Composable
fun Footer(modifier: Modifier) {
    Column(modifier.padding(0.dp)) {
        Divider()
        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account?",
                style = MaterialTheme.typography.body1,
                color = Color.Gray
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .animateContentSize(
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    .clickable { },
                text = "Sign Up",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.primary
            )
        }
    }

}

@Composable
fun FacebookConnect() {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.fb),
            contentDescription = "Log in"
        )
        Text(
            modifier = Modifier
                .padding(16.dp)
                .clickable {},
            text = "Continue as Nicholas Mendez",
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun LogInButton(isLogin: Boolean) {
    Button(
        onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        enabled = isLogin,
        colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Color(0xFF78C8F9))
    ) {
        Text(
            text = "Log in",
            style = MaterialTheme.typography.body1,
            color = Color.White,
            textAlign = TextAlign.Center
        )

    }
}

@Composable
fun DividerSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp), horizontalArrangement = Arrangement.Center
    ) {
        Divider(modifier = Modifier.width(170.dp))
        Text(text = "OR")
        Divider(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun PasswordReset() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .clickable { },
        text = "Forgot password?",
        textAlign = TextAlign.End,
        style = MaterialTheme.typography.body1,
        color = MaterialTheme.colors.primary
    )
}

@Composable
fun EntryPassword(password: String, onTextChange: (String) -> Unit) {
    var passwordShow by rememberSaveable {
        mutableStateOf(false)

    }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        value = password,
        singleLine = true,
        maxLines = 1,
        onValueChange = { onTextChange(it) },
        label = { Text(text = "Password", color = Color.Gray) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordShow) {
                Icons.Filled.VisibilityOff
            } else {
                Icons.Filled.Visibility
            }
            IconButton(onClick = { passwordShow = !passwordShow }) {
                Icon(imageVector = image, contentDescription = "Show password")
            }
        },
        visualTransformation = if (passwordShow) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }
    )
}

@Composable
fun EntryUser(emailvalue: String, onTextChange: (String) -> Unit) {


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        value = emailvalue,
        singleLine = true,
        maxLines = 1,
        onValueChange = { onTextChange(it) },
        label = { Text(text = "Phone number, username or email", color = Color.Gray) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),


        )
}

@Composable
fun HeadLogo() {
    Text(
        text = "Instagram",
        fontFamily = FontFamily(Font(R.font.beutiful)),
        textAlign = TextAlign.Center,
        fontSize = 40.sp,

        fontWeight = FontWeight.Bold
    )
}


