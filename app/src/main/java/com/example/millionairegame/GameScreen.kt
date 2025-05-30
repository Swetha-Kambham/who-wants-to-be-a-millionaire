package com.example.millionairegame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay
import androidx.compose.ui.platform.LocalContext
import android.widget.Toast
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(viewModel: GameViewModel) {
    val question = viewModel.currentQuestion
    var selectedAnswerIndex by remember { mutableStateOf<Int?>(null) }
    val context = LocalContext.current

    LaunchedEffect(viewModel.currentQuestionIndex) {
        selectedAnswerIndex = null
    }

    LaunchedEffect(viewModel.showCorrectAnswer) {
        if (viewModel.showCorrectAnswer) {
            Toast.makeText(
                context,
                "Correct! You earned: \$${viewModel.currentPrizeForCurrentQuestion}",
                Toast.LENGTH_SHORT
            ).show()
            delay(1500)
            if (!viewModel.gameOver) {
                viewModel.currentQuestionIndex++

            }
            viewModel.showCorrectAnswer = false
        } else if (viewModel.gameOver) {
            selectedAnswerIndex = null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4A148C))
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "You Earned:",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "\$${viewModel.currentPrize}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Divider(color = Color.Gray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = question.question,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        question.options.forEachIndexed { index, option ->
            val isCorrect = index == question.correctAnswerIndex
            val isSelected = selectedAnswerIndex == index
            val buttonColor = when {
                isSelected && isCorrect -> Color.Green.copy(alpha = 0.7f)
                isSelected && !isCorrect -> Color.Red.copy(alpha = 0.7f)
                else -> Color(0xFF6A1B9A)
            }
            val textColor = Color.White
            val isEnabled = selectedAnswerIndex == null

            Button(
                onClick = {
                    if (isEnabled) {
                        selectedAnswerIndex = index
                        viewModel.checkAnswer(index)
                        //  viewModel.currentPrizeForCurrentQuestion = viewModel.currentPrize //  This is now handled in the ViewModel
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor, contentColor = textColor, disabledContainerColor = buttonColor.copy(alpha = 0.5f), disabledContentColor = textColor.copy(alpha = 0.7f)),
                enabled = isEnabled
            ) {
                Text(option)
            }
        }

        if (viewModel.gameOver) {
            val allQuestionsAnswered = viewModel.currentQuestionIndex >= viewModel.totalQuestions
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0x804A148C))
                    .padding(16.dp)
                    .border(BorderStroke(2.dp, Color.Gray)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Game Over",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = if (allQuestionsAnswered) Color.Green else Color.Red
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(color = Color.Gray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (allQuestionsAnswered) "You Won the Game!" else "You Lost the Game!",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = if (allQuestionsAnswered) Color.Green else Color.Red
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "You earned: \$${viewModel.currentPrize}",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = { viewModel.restartGame() }) {
                    Text("Try Again", fontSize = 18.sp, color = Color.White)
                }
            }
        }
    }
}