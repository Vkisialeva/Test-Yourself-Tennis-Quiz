package com.example.testyourselftennisquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F7F4)
                ) {

                    HomeScreen()
                }
            }
        }
    }
}


@Composable
fun HomeScreen() {

    val context = LocalContext.current

    // The user must read the rules before starting.
    var rulesRead by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7F4))
            .padding(horizontal = 24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(
            modifier = Modifier.height(55.dp)
        )


        // ------------------------------------------------
        // TENNIS ICON
        // ------------------------------------------------

        Text(
            text = "🎾",
            fontSize = 64.sp
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // ------------------------------------------------
        // APP TITLE
        // ------------------------------------------------

        Text(
            text = "Test Yourself!",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20),
            textAlign = TextAlign.Center
        )


        Text(
            text = "Tennis Quiz",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        Text(
            text = "How well do you know tennis?",
            fontSize = 17.sp,
            color = Color.DarkGray,
            textAlign = TextAlign.Center
        )


        Spacer(
            modifier = Modifier.height(32.dp)
        )


        // ------------------------------------------------
        // QUIZ INFORMATION CARD
        // ------------------------------------------------

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(20.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {


                Text(
                    text = "Quiz Information",
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )


                Spacer(
                    modifier = Modifier.height(14.dp)
                )


                HorizontalDivider()


                Spacer(
                    modifier = Modifier.height(18.dp)
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Questions",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )

                    Text(
                        text = "10",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Points per question",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )

                    Text(
                        text = "10",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Maximum score",
                        fontSize = 16.sp,
                        color = Color.DarkGray
                    )

                    Text(
                        text = "100",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier.height(28.dp)
        )


        // ------------------------------------------------
        // START QUIZ BUTTON
        // ------------------------------------------------

        Button(
            onClick = {

                if (rulesRead) {

                    val intent = Intent(
                        context,
                        QuizActivity::class.java
                    )

                    context.startActivity(intent)

                } else {

                    Toast.makeText(
                        context,
                        "Please read the Quiz Rules first.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(16.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32),
                contentColor = Color.White
            )
        ) {

            Text(
                text = "Start Quiz",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(
            modifier = Modifier.height(14.dp)
        )


        // ------------------------------------------------
        // QUIZ RULES BUTTON
        // ------------------------------------------------

        Button(
            onClick = {

                rulesRead = true

                Toast.makeText(
                    context,
                    "Quiz Rules:\n\n" +
                            "• Answer all 10 questions.\n" +
                            "• Each correct answer is worth 10 points.\n" +
                            "• Select one answer for each question.\n" +
                            "• Review your answers after the quiz.\n" +
                            "• Retry questions you answered incorrectly.",
                    Toast.LENGTH_LONG
                ).show()
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(16.dp),

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
                text =
                    if (rulesRead) {
                        "✓ Quiz Rules Read"
                    } else {
                        "Read Quiz Rules"
                    },

                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(
            modifier = Modifier.height(22.dp)
        )


        // ------------------------------------------------
        // INSTRUCTION
        // ------------------------------------------------

        Text(
            text =
                if (rulesRead) {
                    "You're ready! Press Start Quiz."
                } else {
                    "Please read the quiz rules before starting."
                },

            fontSize = 15.sp,
            color =
                if (rulesRead) {
                    Color(0xFF2E7D32)
                } else {
                    Color.DarkGray
                },

            textAlign = TextAlign.Center,
            fontWeight =
                if (rulesRead) {
                    FontWeight.Bold
                } else {
                    FontWeight.Normal
                }
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Text(
            text = "Good luck! 🎾",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        )
    }
}