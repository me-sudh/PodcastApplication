package com.example.androidexternshipproject.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.COMMAND_PLAY_PAUSE
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.example.androidexternshipproject.apiInteractions.Result
import com.example.androidexternshipproject.stripHtml
import com.example.androidexternshipproject.ui.theme.Purple80

@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun PodcastPlayer(podcast: Result, context: Context){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Purple80),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        val context = LocalContext.current

        Text(
//                text = data.name,
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
        val player = ExoPlayer.Builder(context).build()

        val playerView = PlayerView(context)
        playerView.player = player
        playerView.setShowNextButton(false)
        playerView.setShowPreviousButton(false)

        //customising playerview
        val mediaItem = MediaItem.fromUri(podcast.audio)
        player.setMediaItem(mediaItem)
        player.prepare()
        
        AndroidView(factory = {
//            PlayerView(it).apply {
//                this.player = player
//                this.defaultArtwork = Draw
//            }
            playerView
        },
        update = {

        })
//        Button(onClick = { Log.d("TEST", player.playbackState.toString())
//            playerView.onPause()
//        }) {
//            Text(text = "TEST")
//        }
//        player.play()
//        BackHandler(enabled = true) {
//            Log.d("TEST", "inside back handler")
//            playerView.onPause()
////            if(player.isCommandAvailable(COMMAND_PLAY_PAUSE)){
////                Log.d("TEST", "pausing")
////                player.playWhenReady = false
////            }else{
////                Log.d("TEST", "unavailable")
////            }
//        }
    }
}