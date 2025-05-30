# Who Wants to Be a Millionaire – Android App (Kotlin + Jetpack Compose)

This Android application is a quiz game modeled after the television show *Who Wants to Be a Millionaire*. Developed using Kotlin and Jetpack Compose, the app challenges users to answer a series of multiple-choice questions that increase in difficulty. The goal is to reach the final question without answering incorrectly.

## Features

- Splash screen with a branded fullscreen image
- 15 multiple-choice trivia questions
- Dynamic UI using Jetpack Compose
- Toast messages to confirm correct and incorrect answers
- Game end screen showing win or loss status
- Modern styling using Material 3 themes
- Prize progression logic managed in ViewModel
- Fully commented code for readability and evaluation

## Technologies Used

- Kotlin
- Jetpack Compose
- MVVM Architecture
- Android Studio
- Material 3 (Compose Theme)

## How to Run the App

1. Download or clone the repository.
2. Open the project in Android Studio.
3. Run the app on an Android emulator or physical device (API level 21+).
4. Play the game by answering the questions—correct answers progress you forward, incorrect answers end the game.

## Gameplay Summary

- The game starts with a splash screen.
- Each question has four answer choices.
- Correct answers trigger a toast and increase the prize.
- The game ends when a wrong answer is selected or the user wins.
- The total prize earned is displayed at the end of the game.
