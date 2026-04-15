package com.salat.gbinder.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DrawableImage(
    icon: Any?,
    modifier: Modifier = Modifier,
    sizeDp: Int? = null,
    contentDescription: String? = null
) {
    icon?.let {
        val context = LocalContext.current
        AsyncImage(
            model = remember(it, sizeDp) {
                val requester = ImageRequest.Builder(context).data(it)

                sizeDp?.let { size ->
                    requester.size(sizeDp.dp.toPxInt)
                }
                requester.build()
            },
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = modifier
        )
    }
}
