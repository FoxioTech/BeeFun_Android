package tech.foxio.beefun.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.foxio.beefun.R

/**
 *  author : Cjiio
 *  date : 2023/5/12 12:32
 *  description :
 */
@Composable
public fun HeadAppTopBar(Title : String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )
        Text(
            text = Title,
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Surface(
            modifier = Modifier
                .height(5.dp)
                .width(30.dp),
            color = MaterialTheme.colorScheme.primary,
            shape = MaterialTheme.shapes.small
        ){

        }
    }
}

@Preview
@Composable
public fun HeadAppTopBarPreview() {
    HeadAppTopBar(Title = "Title")
}

@Composable
public fun HeadAppTopBarBack(Title : String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
        )
        Text(
            text = Title,
            style = MaterialTheme.typography.headlineLarge,
        )
        Spacer(modifier = Modifier.height(5.dp))
        Surface(
            modifier = Modifier
                .height(5.dp)
                .width(30.dp),
            color = MaterialTheme.colorScheme.primary,
            shape = MaterialTheme.shapes.small
        ){

        }
    }
}

@Preview
@Composable
fun PreviewHeadAppTopBarBack() {
    HeadAppTopBarBack(Title = "Title")
}