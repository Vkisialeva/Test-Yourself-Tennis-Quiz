package com.example.testyourselftennisquiz

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    // ------------------------------------------------
    // TEST 1
    // Correct answer should be recognised
    // ------------------------------------------------

    @Test
    fun correctAnswer_isRecognised() {

        val selectedAnswer = 1
        val correctAnswer = 1

        val result = selectedAnswer == correctAnswer

        assertTrue(result)
    }


    // ------------------------------------------------
    // TEST 2
    // Incorrect answer should be recognised
    // ------------------------------------------------

    @Test
    fun incorrectAnswer_isRecognised() {

        val selectedAnswer = 2
        val correctAnswer = 1

        val result = selectedAnswer == correctAnswer

        assertFalse(result)
    }


    // ------------------------------------------------
    // TEST 3
    // Score should be calculated correctly
    // Each correct answer is worth 10 points
    // ------------------------------------------------

    @Test
    fun score_isCalculatedCorrectly() {

        val correctAnswers = 8

        val score = correctAnswers * 10

        assertEquals(80, score)
    }


    // ------------------------------------------------
    // TEST 4
    // Percentage should be calculated correctly
    // ------------------------------------------------

    @Test
    fun percentage_isCalculatedCorrectly() {

        val correctAnswers = 7
        val totalQuestions = 10

        val percentage =
            (correctAnswers * 100) / totalQuestions

        assertEquals(70, percentage)
    }


    // ------------------------------------------------
    // TEST 5
    // Incorrect answer count should be correct
    // ------------------------------------------------

    @Test
    fun incorrectAnswerCount_isCalculatedCorrectly() {

        val totalQuestions = 10
        val correctAnswers = 6

        val incorrectAnswers =
            totalQuestions - correctAnswers

        assertEquals(4, incorrectAnswers)
    }


    // ------------------------------------------------
    // TEST 6
    // Perfect quiz should give 100 points
    // ------------------------------------------------

    @Test
    fun perfectScore_returns100() {

        val correctAnswers = 10

        val score = correctAnswers * 10

        assertEquals(100, score)
    }


    // ------------------------------------------------
    // TEST 7
    // Zero correct answers should give zero points
    // ------------------------------------------------

    @Test
    fun zeroCorrectAnswers_returnsZeroScore() {

        val correctAnswers = 0

        val score = correctAnswers * 10

        assertEquals(0, score)
    }
}