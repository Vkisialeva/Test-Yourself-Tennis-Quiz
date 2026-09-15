package com.example.testyourselftennisquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class ResultActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val score =
            intent.getIntExtra("score", 0)

        val correctAnswers =
            intent.getIntExtra("correctAnswers", 0)

        val incorrectAnswers =
            intent.getIntExtra("incorrectAnswers", 0)

        val percentage =
            intent.getIntExtra("percentage", 0)

        val totalQuestions =
            intent.getIntExtra("totalQuestions", 10)

        val selectedAnswers =
            intent.getIntegerArrayListExtra("selectedAnswers")
                ?: arrayListOf()

        setContent {

            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F7F4)
                ) {

                    ResultScreen(
                        score = score,
                        correctAnswers = correctAnswers,
                        incorrectAnswers = incorrectAnswers,
                        percentage = percentage,
                        totalQuestions = totalQuestions,
                        selectedAnswers = selectedAnswers
                    )
                }
            }
        }
    }
}


@Composable
fun ResultScreen(
    score: Int,
    correctAnswers: Int,
    incorrectAnswers: Int,
    percentage: Int,
    totalQuestions: Int,
    selectedAnswers: ArrayList<Int>
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 24.dp,
                vertical = 25.dp
            ),

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // ------------------------------------------------
        // HEADER
        // ------------------------------------------------

        Text(
            text = "🎾",
            fontSize = 48.sp
        )

        Spacer(
            modifier = Modifier.height(5.dp)
        )

        Text(
            text = "Quiz Complete!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20),
            textAlign = TextAlign.Center
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Here are your tennis quiz results",
            fontSize = 16.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        // ------------------------------------------------
        // SCORE CARD
        // ------------------------------------------------

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(22.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(
                    text = "Final Score",
                    fontSize = 19.sp,
                    color = Color.DarkGray
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "$score / 100",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2E7D32)
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "$percentage%",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = when {

                        percentage == 100 ->
                            "Perfect score! Excellent work!"

                        percentage >= 80 ->
                            "Great job!"

                        percentage >= 60 ->
                            "Good effort!"

                        else ->
                            "Keep practising!"
                    },

                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    color = Color.DarkGray
                )
            }
        }


        Spacer(
            modifier = Modifier.height(18.dp)
        )


        // ------------------------------------------------
        // CORRECT / INCORRECT CARD
        // ------------------------------------------------

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),

                horizontalArrangement =
                    Arrangement.SpaceEvenly
            ) {

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Correct",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = "$correctAnswers",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2E7D32)
                    )

                    Text(
                        text = "of $totalQuestions",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }


                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Incorrect",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = "$incorrectAnswers",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC62828)
                    )

                    Text(
                        text = "of $totalQuestions",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        // ------------------------------------------------
        // REVIEW ANSWERS
        // ------------------------------------------------

        Button(
            onClick = {

                val intent = Intent(
                    context,
                    ReviewActivity::class.java
                )

                intent.putIntegerArrayListExtra(
                    "selectedAnswers",
                    selectedAnswers
                )

                context.startActivity(intent)
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            shape = RoundedCornerShape(15.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32),
                contentColor = Color.White
            )
        ) {

            Text(
                text = "Review Answers",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // ------------------------------------------------
        // RETRY INCORRECT QUESTIONS
        // ------------------------------------------------

        Button(
            onClick = {

                val correctAnswerIndexes =
                    arrayListOf(
                        0,
                        1,
                        1,
                        0,
                        2,
                        1,
                        1,
                        1,
                        2,
                        0
                    )

                val incorrectQuestionIndexes =
                    arrayListOf<Int>()


                selectedAnswers.forEachIndexed {
                        index,
                        answer ->

                    if (
                        index < correctAnswerIndexes.size &&
                        answer != correctAnswerIndexes[index]
                    ) {

                        incorrectQuestionIndexes.add(index)
                    }
                }


                if (incorrectQuestionIndexes.isEmpty()) {

                    Toast.makeText(
                        context,
                        "Great job! You have no incorrect questions to retry.",
                        Toast.LENGTH_LONG
                    ).show()

                } else {

                    val intent = Intent(
                        context,
                        RetryActivity::class.java
                    )

                    intent.putIntegerArrayListExtra(
                        "incorrectQuestionIndexes",
                        incorrectQuestionIndexes
                    )

                    context.startActivity(intent)
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            shape = RoundedCornerShape(15.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF1B5E20)
            ),

            border = BorderStroke(
                2.dp,
                Color(0xFF2E7D32)
            )
        ) {

            Text(
                text = "Retry Incorrect Questions",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // ------------------------------------------------
        // RETURN HOME
        // ------------------------------------------------

        Button(
            onClick = {

                val intent = Intent(
                    context,
                    MainActivity::class.java
                )

                intent.flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or
                            Intent.FLAG_ACTIVITY_SINGLE_TOP

                context.startActivity(intent)
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),

            shape = RoundedCornerShape(15.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.DarkGray
            ),

            border = BorderStroke(
                1.dp,
                Color.LightGray
            )
        ) {

            Text(
                text = "Return Home",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}