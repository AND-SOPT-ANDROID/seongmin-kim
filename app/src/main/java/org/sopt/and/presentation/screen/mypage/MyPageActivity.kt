package org.sopt.and.presentation.screen.mypage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.ANDANDROIDTheme
import org.sopt.and.ui.theme.Black
import org.sopt.and.ui.theme.Gray
import org.sopt.and.ui.theme.PurpleGrey40
import org.sopt.and.ui.theme.White

class MyPageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                MyViewScreen()
            }
        }
    }
}

@Composable
fun MyViewScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .statusBarsPadding()
            .navigationBarsPadding()
            .imePadding(),
        ) {

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(color = PurpleGrey40)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = White,
                    modifier = Modifier
                        .size(48.dp)
                )
                Text(
                    text = stringResource(R.string.signup_id_placeholder),
                    style = TextStyle(
                        color = White,
                        fontSize = 18.sp
                    )
                )
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_notifications_white_24),
                        contentDescription = null,
                        tint = White
                    )
                }
                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_settings_white_24),
                        contentDescription = null,
                        tint = White
                    )
                }
            }

            Text(
                text = "첫 결제 시 첫 달 100원!",
                color = White,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
            Text(
                text = "구매하기 >",
                color = White,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp) // 높이 설정
                    .background(Black) // 기본 배경색(검정색)
            )

            Text(
                text = "현재 보유하신 이용권이 없습니다.",
                color = Gray,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
            Text(
                text = "구매하기 >",
                color = White,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp, bottom = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "전체 시청내역",
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_error_gray_24),
                        contentDescription = "No Watch History",
                        tint = Gray,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(text = "시청내역이 없어요.", color = Gray, fontSize = 14.sp)
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "관심 프로그램",
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_error_gray_24),
                        contentDescription = "No Interest Program",
                        tint = Gray,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(text = "관심 프로그램이 없어요.", color = Gray, fontSize = 14.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    ANDANDROIDTheme {
        MyViewScreen()
    }
}