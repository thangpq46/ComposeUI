package com.qt46.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.qt46.demo.ui.theme.DemoTheme


val Pretendard = FontFamily(
    Font(R.font.pretendardsemibold, FontWeight.SemiBold),
    Font(R.font.pretendardbold, FontWeight.Bold),
    Font(R.font.pretendardregular, FontWeight.Normal)
)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val screens = listOf(Screen.Gallery, Screen.Home, Screen.Profile)
            DemoTheme {
                val systemUiController = rememberSystemUiController()
                SideEffect {
                    systemUiController.setStatusBarColor(
                        color = Color.Black,
                        darkIcons = false
                    )
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()


                    Scaffold(bottomBar = {
                        BottomNavigation(
                            modifier = Modifier.height(87.dp),
                            backgroundColor = Color(0xff080E0E)
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            screens.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        Icon(modifier = Modifier
                                            .requiredSize(if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) 64.dp else 40.dp)
                                            ,
                                            painter = painterResource(id = screen.iconID),
                                            contentDescription = null,
                                            tint = Color(if (currentDestination?.hierarchy?.any { it.route == screen.route } == true) 0xffF4F4F4 else 0xffC6C6C6)
                                        )
                                    },
                                    label = {
                                        if (currentDestination?.hierarchy?.any { it.route == screen.route } == false){
                                            Text(
                                                stringResource(screen.resourceId), style = TextStyle(
                                                    fontSize = 9.sp,
                                                    fontFamily = Pretendard,
                                                    fontWeight = FontWeight.SemiBold

                                                ), color = Color(0xffC6C6C6)
                                            )
                                        }

                                    },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }) { innerPaddings ->
                        NavHost(
                            navController = navController,
                            startDestination = "home",
                            modifier = Modifier.padding(innerPaddings)
                        ) {
                            composable("home") { MainUi() }
                            composable("profile") { Profile(/*...*/) }
                            composable("gallery") { Profile(/*...*/) }
                            /*...*/
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Profile() {

}

@Composable
fun ButtonTalk(
    modifier: Modifier = Modifier,
    title: String,
    details: String,
    leadingIconID: Int,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 75.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = if (isPressed) Color(0xff1ba3a1) else Color(0xff111c1c))
            .clickable(interactionSource = interactionSource, null) {

            }
            .padding(
                horizontal = 16.dp,
                vertical = 9.dp
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(9.dp, Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = leadingIconID),
                contentDescription = "Frame 51",
                modifier = Modifier
                    .requiredSize(size = 48.dp)
                    .clip(shape = RoundedCornerShape(22.dp))
                    .padding(all = 8.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top)
            ) {
                Text(
                    text = title,
                    color = Color(0xfff4f4f4),
                    lineHeight = 8.33.em,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Text(
                    text = details,
                    color = Color(0xfff4f4f4),
                    lineHeight = 12.5.em,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = Pretendard,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        }
        Image(
            painter = painterResource(id = R.drawable.ic_forward),
            contentDescription = "CaretRight",
            modifier = Modifier
                .requiredSize(size = 30.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MainUi() {
    val sources = listOf(
        Source(R.drawable.ic_play, "랜덤 닉네임", "1 분 전"),
        Source(R.drawable.ic_play, "날카로운 오징어", "1 분 전"),
        Source(R.drawable.ic_play, "랜덤 닉네임", "1 분 전"),
        Source(R.drawable.ic_play, "랜덤 닉네임", "1 분 전"),
        Source(R.drawable.ic_play, "랜덤 닉네임", "1 분 전")
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xfff080E0E))
            .padding(horizontal = 22.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(9.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            ButtonAds(Modifier, "광고 +1")
            ButtonAds(Modifier, "175")
        }
        Spacer(modifier = Modifier.height(28.dp))
        TitleCanWeTalk()
        TextTutorials()
        Spacer(modifier = Modifier.height(25.dp))
        ButtonTalk(Modifier, "자유대화", "당신의 목소리를 들려주세요.", R.drawable.ic_micro, {})
        Spacer(modifier = Modifier.height(14.dp))
        ButtonTalk(Modifier, "목소리 소개팅", "매칭이 끝날 때까지 한 사람에게 집중하세요.", R.drawable.ic_heart, {})
        Spacer(modifier = Modifier.height(25.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextSource()
            ButtonFilter()
        }
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(11.dp)) {
            items(items = sources) {
                SoureProvider(source = it)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 22.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {

    }
}

@Composable
fun SoureProvider(modifier: Modifier = Modifier, source: Source) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff111c1c)),
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(height = 123.dp)
    ) {

        Image(
            painter = painterResource(id = source.iconID),
            contentDescription = "play",
            modifier = modifier
                .requiredWidth(width = 63.dp)
                .requiredHeight(height = 62.dp)
        )
        Spacer(modifier = modifier.width(17.dp))
        Column(modifier = modifier.fillMaxWidth()) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = source.title,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        lineHeight = 11.54.em,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = Pretendard,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = modifier
                    )
                    Text(
                        text = source.details,
                        color = Color(0xffc6c6c6),
                        textAlign = TextAlign.Center,
                        lineHeight = 12.5.em,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = Pretendard,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = modifier
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "Frame 12588",
                    modifier = modifier
                        .requiredWidth(width = 32.dp)
                        .requiredHeight(height = 31.dp)
                )

            }
            Spacer(modifier = modifier.height(18.dp))
            Button(
                onClick = { },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xff1e2f2f)),
                modifier = modifier
                    .fillMaxWidth()
                    .requiredHeight(height = 41.dp)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.ic_smallmic),
                    contentDescription = "icon Micro",
                    modifier = modifier
                        .requiredSize(size = 15.dp)
                )
                Spacer(modifier = modifier.width(11.dp))
                Text(
                    text = "메시지 보내기",
                    color = Color.White,
                    lineHeight = 11.54.em,
                    style = TextStyle(
                        fontSize = 13.sp
                    ),
                    modifier = modifier
                )
            }
        }
    }
}


@Composable
fun ButtonFilter(modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = { },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        border = BorderStroke(0.8129823207855225.dp, Color(0xff555555)),
        modifier = modifier
            .requiredWidth(width = 97.dp)
            .requiredHeight(height = 36.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.ic_filter), contentDescription = "filter")
        Text(text = "필터", fontFamily = Pretendard, fontWeight = FontWeight.Normal, fontSize = 12.sp)
    }
}

@Composable
fun TextSource(modifier: Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = "자유대화 피드",
            color = Color(0xfff4f4f4),
            textAlign = TextAlign.Center,
            lineHeight = 11.54.em,
            style = TextStyle(
                fontSize = 13.sp,
                fontFamily = Pretendard,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = modifier
        )
    }

}

@Composable
fun TitleCanWeTalk(modifier: Modifier = Modifier) {
    Text(
        text = "우리 대화 할까요?",
        color = Color(0xfff4f4f4),
        style = TextStyle(
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold
        ), fontFamily = Pretendard, fontWeight = FontWeight.Bold,
        modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Start
    )
}

@Composable
fun TextTutorials(modifier: Modifier = Modifier) {
    Text(
        text = "아래의 버튼을 눌러 사람들과 대화를 시작하세요.당신의 이야기를 들어줄거에요.",
        color = Color(0xffdfdfdf),
        style = TextStyle(
            fontSize = 15.sp,
            fontFamily = Pretendard,
            fontWeight = FontWeight.Normal
        ),
        modifier = modifier.fillMaxWidth(), textAlign = TextAlign.Start
    )
}

@Composable
fun ButtonAds(modifier: Modifier = Modifier, text: String) {
    Button(
        onClick = { },
        shape = RoundedCornerShape(17.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xff111c1c)),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 7.dp),
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                color = Color(0xfff4f4f4),
                textAlign = TextAlign.Center,
                lineHeight = 11.54.em,
                style = TextStyle(
                    fontSize = 13.sp
                ), fontFamily = Pretendard, fontWeight = FontWeight.SemiBold
            )
            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "image 174",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .requiredSize(size = 16.dp)
            )
        }
    }
}
