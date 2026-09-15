package com.example.testyourselftennisquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
// QUESTION DATA CLASS
// ----------------------------------------------------

data class Question(
    val question: String,
    val answers: List<String>,
    val correctAnswer: Int
)


// ----------------------------------------------------
// QUIZ ACTIVITY
// ----------------------------------------------------

class QuizActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            MaterialTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFF5F7F4)
                ) {

                    QuizScreen()
                }
            }
        }
    }
}


// ----------------------------------------------------
// QUIZ SCREEN
// ----------------------------------------------------

@Composable
fun QuizScreen() {

    val context = LocalContext.current


    // ------------------------------------------------
    // TEN TENNIS QUESTIONS
    // ------------------------------------------------

    val questions = listOf(

        Question(
            question =
                "What is the score after winning the first point in a tennis game?",

            answers = listOf(
                "15",
                "30",
                "40",
                "Love"
            ),

            correctAnswer = 0
        ),


        Question(
            question =
                "What is the tennis score 40-40 called?",

            answers = listOf(
                "Match Point",
                "Deuce",
                "Advantage",
                "Love"
            ),

            correctAnswer = 1
        ),


        Question(
            question =
                "How many players are on the court during a singles tennis match?",

            answers = listOf(
                "1",
                "2",
                "3",
                "4"
            ),

            correctAnswer = 1
        ),


        Question(
            question =
                "Who has won the most women's Grand Slam singles titles?",

            answers = listOf(
                "Margaret Court",
                "Serena Williams",
                "Steffi Graf",
                "Aryna Sabalenka"
            ),

            correctAnswer = 0
        ),


        Question(
            question =
                "What does the score 'Love' mean in tennis?",

            answers = listOf(
                "10 points",
                "15 points",
                "Zero points",
                "Game point"
            ),

            correctAnswer = 2
        ),


        Question(
            question =
                "How many sets must a player usually win in a best-of-three-set match?",

            answers = listOf(
                "1",
                "2",
                "3",
                "4"
            ),

            correctAnswer = 1
        ),


        Question(
            question =
                "Which surface is Wimbledon played on?",

            answers = listOf(
                "Clay",
                "Grass",
                "Hard court",
                "Carpet"
            ),

            correctAnswer = 1
        ),


        Question(
            question =
                "What is it called when a player serves and the opponent cannot touch the ball?",

            answers = listOf(
                "Volley",
                "Ace",
                "Deuce",
                "Break"
            ),

            correctAnswer = 1
        ),


        Question(
            question =
                "What is the minimum number of points normally needed to win a standard tie-break?",

            answers = listOf(
                "5",
                "6",
                "7",
                "10"
            ),

            correctAnswer = 2
        ),


        Question(
            question =
                "Which of these is one of the four Grand Slam tournaments?",

            answers = listOf(
                "Australian Open",
                "Miami Open",
                "Canadian Open",
                "Paris Masters"
            ),

            correctAnswer = 0
        )
    )


    // ------------------------------------------------
    // CURRENT QUESTION
    // ------------------------------------------------

    var currentQuestion by remember {
        mutableIntStateOf(0)
    }


    // ------------------------------------------------
    // STORE USER ANSWERS
    //
    // -1 means the question has not been answered yet.
    // ------------------------------------------------

    val selectedAnswers = remember {

        mutableStateListOf<Int>().apply {

            repeat(questions.size) {

                add(-1)
            }
        }
    }


    val question =
        questions[currentQuestion]


    // Progress from 0.1 to 1.0
    val progress =
        (currentQuestion + 1).toFloat() /
                questions.size.toFloat()


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
        // HEADER
        // ------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Text(
                text = "🎾 Tennis Quiz",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )


            Text(
                text =
                    "${currentQuestion + 1}/${questions.size}",

                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.DarkGray
            )
        }


        Spacer(
            modifier = Modifier.height(16.dp)
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
            modifier = Modifier.height(12.dp)
        )


        Text(
            text =
                "Question ${currentQuestion + 1} of ${questions.size}",

            fontSize = 15.sp,
            color = Color.DarkGray
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

                fontSize = 22.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }


        Spacer(
            modifier = Modifier.height(24.dp)
        )


        // ------------------------------------------------
        // ANSWER OPTIONS
        // ------------------------------------------------

        question.answers.forEachIndexed {
                index,
                answer ->


            val isSelected =
                selectedAnswers[currentQuestion] == index


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {

                        selectedAnswers[
                            currentQuestion
                        ] = index
                    },

                shape = RoundedCornerShape(15.dp),

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

                colors = CardDefaults.cardColors(
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
        // NAVIGATION BUTTONS
        // ------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {


            // ------------------------------------------------
            // PREVIOUS BUTTON
            // ------------------------------------------------

            if (currentQuestion > 0) {

                Button(
                    onClick = {

                        currentQuestion--
                    },

                    modifier = Modifier
                        .weight(1f)
                        .height(58.dp),

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
                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }


            // ------------------------------------------------
            // NEXT OR SUBMIT BUTTON
            // ------------------------------------------------

            Button(
                onClick = {


                    // User must select an answer
                    if (
                        selectedAnswers[
                            currentQuestion
                        ] == -1
                    ) {

                        Toast.makeText(
                            context,
                            "Please select an answer.",
                            Toast.LENGTH_SHORT
                        ).show()


                    } else if (
                        currentQuestion <
                        questions.size - 1
                    ) {


                        // Go to next question
                        currentQuestion++


                    } else {


                        // ------------------------------------
                        // SUBMIT QUIZ
                        // ------------------------------------

                        var correctAnswers = 0


                        questions.forEachIndexed {
                                index,
                                quizQuestion ->


                            if (
                                selectedAnswers[index] ==
                                quizQuestion.correctAnswer
                            ) {

                                correctAnswers++
                            }
                        }


                        // Each correct answer = 10 points
                        val score =
                            correctAnswers * 10


                        val incorrectAnswers =
                            questions.size -
                                    correctAnswers


                        val percentage =

                            if (questions.isNotEmpty()) {

                                (
                                        correctAnswers *
                                                100
                                        ) /
                                        questions.size

                            } else {

                                0
                            }


                        // ------------------------------------
                        // OPEN RESULT ACTIVITY
                        // ------------------------------------

                        val intent = Intent(
                            context,
                            ResultActivity::class.java
                        )


                        intent.putExtra(
                            "score",
                            score
                        )


                        intent.putExtra(
                            "correctAnswers",
                            correctAnswers
                        )


                        intent.putExtra(
                            "incorrectAnswers",
                            incorrectAnswers
                        )


                        intent.putExtra(
                            "percentage",
                            percentage
                        )


                        intent.putExtra(
                            "totalQuestions",
                            questions.size
                        )


                        // Send all selected answers so
                        // ResultActivity / ReviewActivity
                        // can use them.
                        intent.putIntegerArrayListExtra(
                            "selectedAnswers",
                            ArrayList(
                                selectedAnswers
                            )
                        )


                        context.startActivity(
                            intent
                        )
                    }
                },

                modifier = Modifier
                    .weight(1f)
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
                            questions.size - 1
                        ) {

                            "Submit Quiz"

                        } else {

                            "Next"
                        },

                    fontSize = 17.sp,
                    fontWeight =
                        FontWeight.Bold
                )
            }
        }


        Spacer(
            modifier = Modifier.height(12.dp)
        )


        // ------------------------------------------------
        // SMALL INSTRUCTION
        // ------------------------------------------------

        Text(
            text =
                "Select one answer before continuing.",

            modifier =
                Modifier.fillMaxWidth(),

            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
    }
}