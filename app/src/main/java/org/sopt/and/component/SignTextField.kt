package org.sopt.and.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sopt.and.ui.theme.DoubleDarkGray
import org.sopt.and.ui.theme.Gray
import org.sopt.and.ui.theme.LittleGray
import org.sopt.and.ui.theme.White

@Composable
fun SignTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier,
    isPwdVisible: Boolean = false
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = DoubleDarkGray,
                shape = RoundedCornerShape(10.dp)
            ),
        textStyle = TextStyle(color = White, fontSize = 16.sp),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp)
            ) {
                Box(

                ) {
                    if(value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = TextStyle(color = LittleGray, fontSize = 16.sp),
                        )
                    }
                    innerTextField()
                }
            }
        }
    )

}