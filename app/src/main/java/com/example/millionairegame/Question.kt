package com.example.millionairegame

data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)
