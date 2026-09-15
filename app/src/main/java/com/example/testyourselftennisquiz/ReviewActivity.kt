package com.example.testyourselftennisquiz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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


class ReviewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val selectedAnswers =
            intent.getIntegerArrayListExtra("selectedAnswers")
                ?: arrayListOf()

        setContent {

            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F7F4)
                ) {

                    ReviewScreen(
                        selectedAnswers = selectedAnswers
                    )
                }
            }
        }
    }
}


@Composable
fun ReviewScreen(
    selectedAnswers: ArrayList<Int>
) {

    val context = LocalContext.current


    // ------------------------------------------------
    // TENNIS QUESTIONS
    // ------------------------------------------------

    val questions = listOf(

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
    // CURRENT REVIEW QUESTION
    // ------------------------------------------------

    var currentQuestion by remember {
        mutableIntStateOf(0)
    }


    val question =
        questions[currentQuestion]


    // Get the answer selected during the quiz
    val selectedAnswer =

        if (
            currentQuestion <
            selectedAnswers.size
        ) {

            selectedAnswers[currentQuestion]

        } else {

            -1
        }


    val isCorrect =
        selectedAnswer ==
                question.correctAnswer


    val progress =
        (currentQuestion + 1).toFloat() /
                questions.size.toFloat()


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
            modifier = Modifier.height(15.dp)
        )


        // ------------------------------------------------
        // TITLE
        // ------------------------------------------------

        Text(
            text = "🎾 Review Answers",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1B5E20),
            textAlign = TextAlign.Center
        )


        Spacer(
            modifier = Modifier.height(18.dp)
        )


        Text(
            text =
                "Question ${currentQuestion + 1} of ${questions.size}",

            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // ------------------------------------------------
        // PROGRESS
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

            shape = RoundedCornerShape(18.dp),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {

            Text(
                text = question.question,

                modifier = Modifier.padding(22.dp),

                fontSize = 21.sp,
                lineHeight = 29.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        // ------------------------------------------------
        // USER ANSWER CARD
        // ------------------------------------------------

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(16.dp),

            border = BorderStroke(
                2.dp,

                if (isCorrect) {
                    Color(0xFF2E7D32)
                } else {
                    Color(0xFFC62828)
                }
            ),

            colors = CardDefaults.cardColors(

                containerColor =

                    if (isCorrect) {
                        Color(0xFFE8F5E9)
                    } else {
                        Color(0xFFFFEBEE)
                    }
            )
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {


                Text(
                    text = "Your Answer",
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )


                Spacer(
                    modifier = Modifier.height(8.dp)
                )


                Text(
                    text =

                        if (
                            selectedAnswer >= 0 &&
                            selectedAnswer <
                            question.answers.size
                        ) {

                            question.answers[
                                selectedAnswer
                            ]

                        } else {

                            "No answer"
                        },

                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Black
                )


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                Text(
                    text =

                        if (isCorrect) {
                            "✓ Correct!"
                        } else {
                            "✗ Incorrect"
                        },

                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,

                    color =

                        if (isCorrect) {
                            Color(0xFF2E7D32)
                        } else {
                            Color(0xFFC62828)
                        }
                )
            }
        }


        // ------------------------------------------------
        // CORRECT ANSWER WHEN WRONG
        // ------------------------------------------------

        if (!isCorrect) {

            Spacer(
                modifier = Modifier.height(15.dp)
            )


            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(16.dp),

                colors = CardDefaults.cardColors(
                    containerColor =
                        Color(0xFFE8F5E9)
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {


                    Text(
                        text = "Correct Answer",
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    )


                    Spacer(
                        modifier = Modifier.height(7.dp)
                    )


                    Text(
                        text =
                            question.answers[
                                question.correctAnswer
                            ],

                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B5E20),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }


        Spacer(
            modifier = Modifier.weight(1f)
        )


        // ------------------------------------------------
        // PREVIOUS
        // ------------------------------------------------

        if (currentQuestion > 0) {

            Button(
                onClick = {

                    currentQuestion--
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape =
                    RoundedCornerShape(15.dp),

                colors =
                    ButtonDefaults.buttonColors(
                        containerColor =
                            Color.White,

                        contentColor =
                            Color(0xFF1B5E20)
                    ),

                border = BorderStroke(
                    2.dp,
                    Color(0xFF2E7D32)
                )
            ) {

                Text(
                    text = "Previous",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }


        // ------------------------------------------------
        // NEXT / FINISH
        // ------------------------------------------------

        Button(
            onClick = {

                if (
                    currentQuestion <
                    questions.size - 1
                ) {

                    currentQuestion++

                } else {

                    // Return to ResultActivity
                    (context as? ComponentActivity)
                        ?.finish()
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),

            shape = RoundedCornerShape(15.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2E7D32),
                contentColor = Color.White
            )
        ) {

            Text(
                text =

                    if (
                        currentQuestion ==
                        questions.size - 1
                    ) {

                        "Finish Review"

                    } else {

                        "Next"
                    },

                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(
            modifier = Modifier.height(10.dp)
        )
    }
}
