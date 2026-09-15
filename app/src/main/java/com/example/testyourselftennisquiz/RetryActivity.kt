package com.example.testyourselftennisquiz

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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


// ----------------------------------------------------
// RETRY ACTIVITY
// ----------------------------------------------------

class RetryActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val incorrectQuestionIndexes =
            intent.getIntegerArrayListExtra(
                "incorrectQuestionIndexes"
            ) ?: arrayListOf()

        setContent {

            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F7F4)
                ) {

                    RetryScreen(
                        incorrectQuestionIndexes =
                            incorrectQuestionIndexes
                    )
                }
            }
        }
    }
}


// ----------------------------------------------------
// RETRY SCREEN
// ----------------------------------------------------

@Composable
fun RetryScreen(
    incorrectQuestionIndexes: ArrayList<Int>
) {

    val context = LocalContext.current


    // ------------------------------------------------
    // ALL TEN TENNIS QUESTIONS
    // ------------------------------------------------

    val allQuestions = listOf(

        Question(
            "What is the score after winning the first point in a tennis game?",
            listOf(
                "15",
                "30",
                "40",
                "Love"
            ),
            0
        ),

        Question(
            "What is the tennis score 40-40 called?",
            listOf(
                "Match Point",
                "Deuce",
                "Advantage",
                "Love"
            ),
            1
        ),

        Question(
            "How many players are on the court during a singles tennis match?",
            listOf(
                "1",
                "2",
                "3",
                "4"
            ),
            1
        ),

        Question(
            "Who has won the most women's Grand Slam singles titles?",
            listOf(
                "Margaret Court",
                "Serena Williams",
                "Steffi Graf",
                "Aryna Sabalenka"
            ),
            0
        ),

        Question(
            "What does the score 'Love' mean in tennis?",
            listOf(
                "10 points",
                "15 points",
                "Zero points",
                "Game point"
            ),
            2
        ),

        Question(
            "How many sets must a player usually win in a best-of-three-set match?",
            listOf(
                "1",
                "2",
                "3",
                "4"
            ),
            1
        ),

        Question(
            "Which surface is Wimbledon played on?",
            listOf(
                "Clay",
                "Grass",
                "Hard court",
                "Carpet"
            ),
            1
        ),

        Question(
            "What is it called when a player serves and the opponent cannot touch the ball?",
            listOf(
                "Volley",
                "Ace",
                "Deuce",
                "Break"
            ),
            1
        ),

        Question(
            "What is the minimum number of points normally needed to win a standard tie-break?",
            listOf(
                "5",
                "6",
                "7",
                "10"
            ),
            2
        ),

        Question(
            "Which of these is one of the four Grand Slam tournaments?",
            listOf(
                "Australian Open",
                "Miami Open",
                "Canadian Open",
                "Paris Masters"
            ),
            0
        )
    )


    // ------------------------------------------------
    // SAFELY CREATE RETRY QUESTIONS
    // ------------------------------------------------

    val retryQuestions =
        incorrectQuestionIndexes
            .filter {
                it in allQuestions.indices
            }
            .map {
                allQuestions[it]
            }


    // ------------------------------------------------
    // NO INCORRECT QUESTIONS
    // ------------------------------------------------

    if (retryQuestions.isEmpty()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),

            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(100.dp)
            )

            Text(
                text = "🎾",
                fontSize = 60.sp
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(
                text = "No Questions to Retry!",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20),
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "You answered every question correctly.",
                fontSize = 17.sp,
                color = Color.DarkGray,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(30.dp)
            )

            Button(
                onClick = {

                    (context as? ComponentActivity)
                        ?.finish()
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            Color(0xFF2E7D32),

                        contentColor =
                            Color.White
                    ),

                shape =
                    RoundedCornerShape(15.dp)
            ) {

                Text(
                    text = "Back to Results",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        return
    }


    // ------------------------------------------------
    // RETRY STATE
    // ------------------------------------------------

    var currentQuestion by remember {
        mutableIntStateOf(0)
    }


    val selectedAnswers = remember {

        mutableStateListOf<Int>().apply {

            repeat(retryQuestions.size) {

                add(-1)
            }
        }
    }


    val question =
        retryQuestions[currentQuestion]


    val progress =
        (currentQuestion + 1).toFloat() /
                retryQuestions.size.toFloat()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 22.dp,
                vertical = 24.dp
            )
    ) {


        Spacer(
            modifier = Modifier.height(15.dp)
        )


        // ------------------------------------------------
        // TITLE
        // ------------------------------------------------

        Text(
            text = "🎾 Retry Incorrect",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20)
        )


        Spacer(
            modifier = Modifier.height(8.dp)
        )


        Text(
            text = "Practise the questions you got wrong.",
            fontSize = 15.sp,
            color = Color.DarkGray
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // ------------------------------------------------
        // QUESTION NUMBER
        // ------------------------------------------------

        Text(
            text =
                "Retry Question ${currentQuestion + 1} of ${retryQuestions.size}",

            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // ------------------------------------------------
        // PROGRESS BAR
        // ------------------------------------------------

        LinearProgressIndicator(
            progress = {
                progress
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),

            color = Color(0xFF2E7D32),

            trackColor = Color(0xFFDDE5DC)
        )


        Spacer(
            modifier = Modifier.height(25.dp)
        )


        // ------------------------------------------------
        // QUESTION CARD
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

            Text(
                text = question.question,

                modifier = Modifier.padding(24.dp),

                fontSize = 21.sp,
                lineHeight = 29.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }


        Spacer(
            modifier = Modifier.height(22.dp)
        )


        // ------------------------------------------------
        // ANSWERS
        // ------------------------------------------------

        question.answers.forEachIndexed {
                index,
                answer ->


            val isSelected =
                selectedAnswers[currentQuestion] ==
                        index


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {

                        selectedAnswers[
                            currentQuestion
                        ] = index
                    },

                shape =
                    RoundedCornerShape(15.dp),

                border =

                    if (isSelected) {

                        BorderStroke(
                            2.dp,
                            Color(0xFF2E7D32)
                        )

                    } else {

                        BorderStroke(
                            1.dp,
                            Color(0xFFCCCCCC)
                        )
                    },

                colors =
                    CardDefaults.cardColors(

                        containerColor =

                            if (isSelected) {

                                Color(0xFFE8F5E9)

                            } else {

                                Color.White
                            }
                    )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 12.dp,
                            vertical = 8.dp
                        ),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {


                    RadioButton(
                        selected = isSelected,

                        onClick = {

                            selectedAnswers[
                                currentQuestion
                            ] = index
                        },

                        colors =
                            RadioButtonDefaults.colors(
                                selectedColor =
                                    Color(0xFF2E7D32)
                            )
                    )


                    Text(
                        text = answer,

                        modifier =
                            Modifier.padding(
                                start = 6.dp
                            ),

                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier.weight(1f)
        )


        // ------------------------------------------------
        // NEXT / FINISH RETRY
        // ------------------------------------------------

        Button(
            onClick = {

                val selectedAnswer =
                    selectedAnswers[
                        currentQuestion
                    ]


                // User must select an answer.
                if (selectedAnswer == -1) {

                    Toast.makeText(
                        context,
                        "Please select an answer.",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {


                    // ------------------------------------
                    // IMMEDIATE FEEDBACK
                    // ------------------------------------

                    if (
                        selectedAnswer ==
                        question.correctAnswer
                    ) {

                        Toast.makeText(
                            context,
                            "Correct! Great job.",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        val correctAnswer =
                            question.answers[
                                question.correctAnswer
                            ]

                        Toast.makeText(
                            context,
                            "Incorrect. Correct answer: $correctAnswer",
                            Toast.LENGTH_LONG
                        ).show()
                    }


                    // ------------------------------------
                    // NEXT QUESTION
                    // ------------------------------------

                    if (
                        currentQuestion <
                        retryQuestions.size - 1
                    ) {

                        currentQuestion++

                    } else {


                        // --------------------------------
                        // RETRY COMPLETE
                        // --------------------------------

                        Toast.makeText(
                            context,
                            "Retry complete!",
                            Toast.LENGTH_LONG
                        ).show()


                        // Return to ResultActivity.
                        (context as? ComponentActivity)
                            ?.finish()
                    }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape =
                RoundedCornerShape(15.dp),

            colors =
                ButtonDefaults.buttonColors(
                    containerColor =
                        Color(0xFF2E7D32),

                    contentColor =
                        Color.White
                )
        ) {

            Text(
                text =

                    if (
                        currentQuestion ==
                        retryQuestions.size - 1
                    ) {

                        "Finish Retry"

                    } else {

                        "Check & Continue"
                    },

                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(
            modifier = Modifier.height(10.dp)
        )


        Text(
            text =
                "You will receive feedback after each answer.",

            modifier =
                Modifier.fillMaxWidth(),

            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}