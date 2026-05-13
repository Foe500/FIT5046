package com.example.fit5046a2.data

enum class RiskLevel {
    NORMAL,
    MODERATE,
    CRITICAL
}

data class RiskResult(
    val level: RiskLevel,
    val title: String,
    val message: String
)

object RiskAnalyzer {
    fun analyze(sample: HeartRateSample): RiskResult {
        return when {
            sample.activity.equals("Resting", ignoreCase = true) && sample.bpm >= 120 -> {
                RiskResult(
                    level = RiskLevel.CRITICAL,
                    title = "Critical Risk",
                    message = "High resting heart rate detected. Please rest and monitor symptoms."
                )
            }

            sample.bpm >= 105 -> {
                RiskResult(
                    level = RiskLevel.MODERATE,
                    title = "Moderate Risk",
                    message = "Heart rate is elevated for the current activity. Reduce intensity and keep monitoring."
                )
            }

            else -> {
                RiskResult(
                    level = RiskLevel.NORMAL,
                    title = "Normal",
                    message = "Heart rate is within the expected range."
                )
            }
        }
    }
}
