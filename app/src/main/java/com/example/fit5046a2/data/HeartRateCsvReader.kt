package com.example.fit5046a2.data

import android.content.Context

class HeartRateCsvReader(
    private val context: Context,
    private val fileName: String = "heart_rate_samples.csv"
) {
    fun readSamples(): List<HeartRateSample> {
        return context.assets.open(fileName).bufferedReader().useLines { lines ->
            lines.drop(1)
                .mapNotNull { line -> line.toHeartRateSample() }
                .toList()
        }
    }

    private fun String.toHeartRateSample(): HeartRateSample? {
        val columns = split(",")
        if (columns.size < 3) return null

        val bpm = columns[1].trim().toIntOrNull() ?: return null
        return HeartRateSample(
            time = columns[0].trim(),
            bpm = bpm,
            activity = columns[2].trim()
        )
    }
}
