package org.sopt.and.presentation.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import org.sopt.and.R
import org.sopt.and.ui.theme.WavveBlue

@Composable
fun SearchScreen(

) {

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = stringResource(R.string.bottom_search_title),
            style = TextStyle(fontSize = 16.sp, color = WavveBlue)
        )

    }

}