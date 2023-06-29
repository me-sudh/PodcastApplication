package com.example.androidexternshipproject.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.androidexternshipproject.apiInteractions.Result
import com.example.androidexternshipproject.stripHtml
import com.example.androidexternshipproject.ui.theme.Purple80

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun PodcastPlayer(podcast: Result){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Purple80),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        val context = LocalContext.current

        Text(
            text = stripHtml(podcast.titleOriginal),
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            color= Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.padding(5.dp))

        //exoplayer variables setup
        val exoPlayer = ExoPlayer.Builder(context).build()
            .also { exoPlayer ->
                val mediaItem = MediaItem.fromUri(podcast.audio)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.playWhenReady = false
            }
        DisposableEffect(
            //disposable effect to call the release when the composable function is exited
            key1 = AndroidView(
                factory = {
                    PlayerView(context).apply {
                        setShowPreviousButton(false)
                        setShowNextButton(false)
                        player = exoPlayer

                    }
                }
            ),
            effect = {
                onDispose { exoPlayer.release() }
            }
        )
    }
}