package com.apptive.braini.presentation.view

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.apptive.braini.R
import com.apptive.braini.presentation.navigation.Screen
import com.apptive.braini.presentation.popNavigate
import com.apptive.braini.presentation.requireContext
import com.apptive.braini.presentation.viewmodel.interfaces.ILoginViewModel
import com.apptive.braini.presentation.viewmodel.mock.LoginViewModelMock
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(
    viewModel: ILoginViewModel = LoginViewModelMock(),
    navController: NavController
){
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

    ModalBottomSheet(
        viewModel = viewModel,
        navController = navController,
        sheetState = sheetState
    ) {
        LoginBackground {
            header()
            Clouds()
            Description()
            Buttons(
                onClick = {
                    coroutineScope.launch {
                        sheetState.show()
                    }
                }
            )
        }
    }
}

@Composable
private fun LoginBackground(
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        content = content
    )
}

@Composable
private fun ColumnScope.header()
{
    Text(
        modifier = Modifier
            .width(230.dp)
            .weight(0.9f)
            .padding(20.dp)
            .padding(top = 40.dp),
        text = "HELLO!",
        fontSize = 43.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ColumnScope.Clouds(){
    Box(
        modifier = Modifier
            .weight(2f)
            .padding(top = 50.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.people),
            contentDescription = null,
            modifier = Modifier.size(350.dp)
        )
    }
}

@Composable
private fun ColumnScope.Description(){
    Box(modifier = Modifier.weight(1f))
    {
        Column(modifier = Modifier.padding(top = 60.dp))
        {
            Text(
                text = stringResource(R.string.login_screen_main_1),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp,),
                color = Color.Black,
                fontSize = 23.sp,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp,),
                color = Color.Black,
                fontSize = 23.sp,
                text = stringResource(R.string.login_screen_main_2)
            )

        }
    }
}


@Composable
public fun ColumnScope.Buttons(onClick: () -> Unit){
    Box(modifier = Modifier.weight(1f))
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onClick,
                modifier = Modifier.width(320.dp),
                colors = ButtonDefaults.buttonColors(Color(0xffc9e4fd)),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
                    text = stringResource(R.string.login_button),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.width(320.dp),
                colors = ButtonDefaults.buttonColors(Color(0xffc9e4fd)),
                shape = RoundedCornerShape(15.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
                    text = stringResource(R.string.new_user_button),
                    textAlign = TextAlign.Center,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun ModalBottomSheet(
    viewModel: ILoginViewModel,
    navController: NavController,
    sheetState: ModalBottomSheetState,
    content: @Composable () -> Unit
){
    ModalBottomSheetLayout(
        sheetContent = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(430.dp)
                .background(Color.White)){
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    ModalSheetTopHandle()
                    SocialLoginText()
                    SocialLoginButtons(viewModel, navController)
                    OrText()
                    IdTextField(viewModel)
                    PwTextField(viewModel)
                    LoginButton()
                    FindIdPw()
                }
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.White,
        sheetShape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
        content = content
    )
}

@Composable
private fun ColumnScope.ModalSheetTopHandle(){
    Box(modifier = Modifier
        .width(200.dp)
        .height(8.dp)
        .clip(RoundedCornerShape(50))
        .background(Color(0xffC4C4C4)))
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun ColumnScope.SocialLoginText(){
    Text(text = stringResource(R.string.modal_sns_text),
        fontSize = 13.sp)
    Spacer(modifier = Modifier.height(20.dp))
}

@Composable
private fun ColumnScope.OrText(){
    Text(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
        text = stringResource(R.string.modal_or),
        fontSize = 13.sp
    )
}

@Composable
private fun SocialLoginButton(
    icon: Int,
    text: String,
    onClick: () -> Unit,
    color: Color,
    borderDp: Dp,
    borderColor: Color,
    textLocation : Dp
) {
    Button(modifier = Modifier
        .width(350.dp),
        border = BorderStroke(borderDp, color = borderColor),
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(color),
        onClick = onClick) {
        Image(modifier = Modifier
            .size(30.dp)
            .weight(2f),
            painter = painterResource(id = icon),
            contentDescription = null)
        Text(modifier = Modifier
            .weight(10f)
            .padding(start = textLocation),
            text =  text)
    }
}

@Composable
private fun ColumnScope.SocialLoginButtons(
    viewModel: ILoginViewModel,
    navController: NavController
) {
    val context = requireContext()
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            viewModel.googleResultListener(
                result = result,
                navigate = {
                    navController.popNavigate(Screen.RoomCreate.route)
                }
            )
        }
    )

    SocialLoginButton(
        icon = R.drawable.kakao,
        text = stringResource(R.string.modal_kakao_btn),
        onClick = {
            viewModel.loginWithKakao(context) {
                navController.popNavigate(Screen.RoomCreate.route)
            }
        },
        color = Color(0xfffbe300),
        borderDp = 0.dp,
        borderColor = Color.White,
        textLocation = 35.dp
    )
    Spacer(modifier = Modifier.height(8.dp))
    SocialLoginButton(
        icon = R.drawable.google,
        text = stringResource(R.string.modal_google_btn),
        onClick = {
            viewModel.googleSignIn(launcher)
        },
        color = Color.White,
        borderDp = 1.dp,
        borderColor = Color.Black,
        textLocation = 53.dp
    )
}


@Composable
private fun ColumnScope.IdTextField(
    viewModel: ILoginViewModel
){
    var idtext by remember { mutableStateOf("")}

    Box(
        modifier = Modifier
            .width(350.dp)
            .border(
                width = 1.dp,
                color = Color(0xff345BC0),
                shape = RoundedCornerShape(30)
            )
            .clip(RoundedCornerShape(30))
            .height(45.dp),
        contentAlignment = Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(2f),
                text = stringResource(R.string.modal_textfileld_id),
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            )
            Spacer(
                modifier = Modifier
                    .width(2.5.dp)
                    .height(35.dp)
                    .background(Color(0xff345BC0))
            )
            var textId by viewModel.accountId
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .padding(start = 10.dp),
                value = textId,
                onValueChange = {newText ->
                    textId = newText
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
private fun ColumnScope.PwTextField(
    viewModel: ILoginViewModel
){
    var pwtext by remember { mutableStateOf("")}
    Box(
        modifier = Modifier
            .width(350.dp)
            .border(
                width = 1.dp,
                color = Color(0xff345BC0),
                shape = RoundedCornerShape(30)
            )
            .clip(RoundedCornerShape(30))
            .height(45.dp),
        Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(2f),
                text = stringResource(R.string.modal_textfield_pw),
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            )
            Spacer(
                modifier = Modifier
                    .width(2.5.dp)
                    .height(35.dp)
                    .background(Color(0xff345BC0))
            )
            var textPw by viewModel.accountPw
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
                    .padding(start = 10.dp),
                value = textPw,
                onValueChange = {newText ->
                    textPw = newText},
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                )
            )
        }
    }
    Spacer(modifier = Modifier.height(7.dp))
}

@Composable
private fun ColumnScope.LoginButton(){
    Button(
        modifier = Modifier
            .width(350.dp),
        colors = ButtonDefaults.buttonColors(Color(0xff345BC0)),
        shape = RoundedCornerShape(30),
        onClick = { /*TODO*/ }
    ) {
        Text(modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
            color = Color.White,
            text = stringResource(R.string.modal_login_btn))
    }
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
private fun ColumnScope.FindIdPw(){
    ClickableText(
        text = AnnotatedString(text = stringResource(R.string.modal_find_idpw)),
        style = TextStyle(textDecoration = TextDecoration.Underline),
        onClick ={}
    )
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview
@Composable
fun BPreview(){
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}