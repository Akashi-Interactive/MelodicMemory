package cat.pedralbes.akashi.melodicmemory.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import android.os.IBinder

class SoundService : Service() {
    private lateinit var soundPool: SoundPool
    private val soundMap: MutableMap<Int, Int> = HashMap()

    override fun onCreate() {
        super.onCreate()
        initializeSoundPool()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        soundPool.release()
    }

    private fun initializeSoundPool() {
        val soundPoolBuilder: SoundPool.Builder =
            SoundPool.Builder()
                .setMaxStreams(MAX_STREAMS)
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .build()
                )

        soundPool = soundPoolBuilder.build()
    }

    fun loadSound(context: Context, rawResourceId: Int) {
        val soundId = soundPool.load(context, rawResourceId, 1)
        soundMap[rawResourceId] = soundId
    }

    fun playSound(rawResourceId: Int) {
        val soundId = soundMap[rawResourceId]
        soundId?.let {
            soundPool.play(it, 1.0f, 1.0f, 1, 0, 1.0f)
        }
    }

    companion object {
        private const val MAX_STREAMS = 5
    }
}