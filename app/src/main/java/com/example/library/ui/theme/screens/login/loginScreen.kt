package com.example.library.ui.theme.screens.login

import android.content.Intent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.library.R
import com.example.library.data.AuthViewModel
import com.example.library.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen (navController: NavController){
    val authViewModel:AuthViewModel = viewModel()
    var email by remember { mutableStateOf(value = "") }
    var password by remember{ mutableStateOf(value = "")  }
    var context = LocalContext.current
    val passwordVisible by remember { mutableStateOf(false) }
    Column  (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
        .padding(50.dp).fillMaxHeight().fillMaxWidth(), verticalArrangement = Arrangement.Center){
        Text(text = "Log in",
            fontSize = 40.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(40.dp))
        Image(painter = painterResource(R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(value = email,
            onValueChange = {newEmail -> email = newEmail},
            label = {Text(text = "Enter Email")},
            placeholder = {Text(text = "Please enter your email")},
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(16.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Email,
                contentDescription = "Email icon") })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = password,
            onValueChange = {newPassword ->password = newPassword},
            label = {Text(text = "Enter Password")},
            placeholder = {Text(text = "Please enter your password")},
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(16.dp),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock,
                contentDescription = "Lock icon") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation())
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            authViewModel.login(email,password,navController,context)
        },
            modifier = Modifier.wrapContentWidth().align(Alignment.CenterHorizontally).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Magenta)) {
            Text(text = "Login")


        }
        Text(text = buildAnnotatedString { append("Don't have an Account? Sign up") },
            color = Color.White,
            modifier = Modifier.wrapContentWidth()
                .align(Alignment.CenterHorizontally)
                .clickable{
                    navController.navigate(ROUTE_REGISTER)

                })
    }
}
@Composable
fun AnimatedLogo() {
    val scale by animateFloatAsState(
        targetValue = 1.2f,
        animationSpec = tween(durationMillis = 1000)
    )
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "logo",
        modifier = Modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .height(200.dp)
            .fillMaxWidth()
            .padding(16.dp)
    )
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun loginScreenPreview(){
    LoginScreen(rememberNavController())
}