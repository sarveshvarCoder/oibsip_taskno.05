data class Question(val text: String, val options: List<String>, val answerIndex: Int)

class Quiz(val questions: List<Question>) {
    private var currentQuestionIndex: Int = 0
    private var score: Int = 0

    fun displayCurrentQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        println("Question ${currentQuestionIndex + 1}: ${currentQuestion.text}")
        currentQuestion.options.forEachIndexed { index, option ->
            println("${index + 1}. $option")
        }
    }

    fun submitAnswer(answerIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]
        if (answerIndex == currentQuestion.answerIndex) {
            println("Correct!")
            score++
        } else {
            println("Incorrect!")
        }
        currentQuestionIndex++
    }

    fun showResult() {
        val totalQuestions = questions.size
        println("Quiz complete!")
        println("Your score: $score out of $totalQuestions")
    }
}

fun main() {
    val questions = listOf(
        Question("What is the capital of France?", listOf("London", "Paris", "Rome", "Berlin"), 2),
        Question("Which country is known as the 'Land of the Rising Sun'?", listOf("Japan", "China", "South Korea", "Thailand"), 1),
        Question("What is the largest planet in our solar system?", listOf("Jupiter", "Saturn", "Neptune", "Mars"), 1)
    )

    val quiz = Quiz(questions)

    while (quiz.questions.size > quiz.currentQuestionIndex) {
        quiz.displayCurrentQuestion()
        print("Enter your answer (1-${quiz.questions[quiz.currentQuestionIndex].options.size}): ")
        val answerIndex = readLine()?.toIntOrNull()

        if (answerIndex != null && answerIndex in 1..quiz.questions[quiz.currentQuestionIndex].options.size) {
            quiz.submitAnswer(answerIndex - 1)
        } else {
            println("Invalid input. Please enter a valid option.")
        }
    }

    quiz.showResult()
}