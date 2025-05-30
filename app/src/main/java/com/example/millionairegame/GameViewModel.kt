package com.example.millionairegame

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel : ViewModel() {

    private val _questions = listOf(
        Question("Who painted the Starry Night?", listOf("Claude Monet", "Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci"), 1),
        Question("What is the chemical symbol for Gold?", listOf("Au", "Ag", "Fe", "Hg"), 0),
        Question("Which planet in our solar system has the most moons?", listOf("Jupiter", "Saturn", "Uranus", "Neptune"), 1),
        Question("What is the title of the first book in the Harry Potter series?", listOf("Harry Potter and the Sorcerer's Stone", "Harry Potter and the Chamber of Secrets", "Harry Potter and the Prisoner of Azkaban", "Harry Potter and the Goblet of Fire"), 0),
        Question("What is the currency of Japan?", listOf("Yuan", "Won", "Yen", "Ringgit"), 2),
        Question("Which animal is known for its ability to regenerate limbs?", listOf("Starfish", "Jellyfish", "Seahorse", "Clownfish"), 0),
        Question("What is the largest desert in the world (by hot desert area)?", listOf("Sahara Desert", "Arabian Desert", "Gobi Desert", "Australian Desert"), 0),
        Question("Who developed the theory of relativity?", listOf("Isaac Newton", "Albert Einstein", "Galileo Galilei", "Stephen Hawking"), 1),
        Question("What is the main ingredient in guacamole?", listOf("Tomato", "Onion", "Avocado", "Lime"), 2),
        Question("Which country is home to the kangaroo?", listOf("South Africa", "New Zealand", "Australia", "Argentina"), 2),
        Question("What is the hardest mineral on the Mohs scale?", listOf("Quartz", "Topaz", "Corundum", "Diamond"), 3),
        Question("What is the powerhouse of the cell?", listOf("Nucleus", "Mitochondria", "Ribosome", "Endoplasmic Reticulum"), 1),
        Question("In what year did the first human land on the moon?", listOf("1959", "1969", "1979", "1989"), 1),
        Question("What is the capital city of Canada?", listOf("Toronto", "Montreal", "Ottawa", "Vancouver"), 2),
        Question("Which gas makes up the majority of the air we breathe?", listOf("Oxygen", "Carbon Dioxide", "Nitrogen", "Argon"), 2),
    )

    val totalQuestions: Int get() = _questions.size

    var currentQuestionIndex by mutableStateOf(0)
    var currentPrize by mutableStateOf(0) // Start at 0
    var gameOver by mutableStateOf(false)
    val currentQuestion: Question get() = _questions[currentQuestionIndex]
    var showCorrectAnswer by mutableStateOf(false)
    var currentPrizeForCurrentQuestion by mutableStateOf(0)

    fun checkAnswer(selectedIndex: Int) {
        if (selectedIndex == currentQuestion.correctAnswerIndex) {
            showCorrectAnswer = true
            currentPrizeForCurrentQuestion = if (currentQuestionIndex == 0) 100 else currentPrize * 2 // First question is 100, then doubles
            currentPrize = currentPrizeForCurrentQuestion // Update the total prize

            viewModelScope.launch {
                delay(1500)
                showCorrectAnswer = false
                if (currentQuestionIndex < _questions.size - 1) {
                    currentQuestionIndex++

                } else {
                    gameOver = true
                }
            }
        } else {
            gameOver = true
        }
    }

    fun restartGame() {
        currentQuestionIndex = 0
        currentPrize = 0
        gameOver = false
        showCorrectAnswer = false
        currentPrizeForCurrentQuestion = 0
    }
}