import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.regex.Matcher
import java.util.regex.Pattern

class ReactiveCalculator(a: Int, b: Int) {
    val subjectCalc: Subject<ReactiveCalculator> = PublishSubject.create()

    internal var numbers: Pair<Int, Int> = Pair(0, 0)

    init {
        numbers = Pair(a, b)

        subjectCalc.subscribe{
            with(it) {
                calculateAddition()
                calculateSubtraction()
                calculateMultiplication()
                calculateDivision()
            }
        }
        subjectCalc.onNext(this)
    }

    fun calculateAddition() : Int {
        val result = numbers.first + numbers.second
        println("Add = $result")
        return result
    }

    fun calculateSubtraction() : Int {
        val result = numbers.first - numbers.second
        println("Subtract = $result")
        return result
    }

    fun calculateMultiplication() : Int {
        val result = numbers.first * numbers.second
        println("Multiply = $result")
        return result
    }

    fun calculateDivision() : Double {
        val result = numbers.first / (numbers.second * 1.0)
        println("Division = $result")
        return result
    }

    fun modifyNumbers(a: Int = numbers.first, b: Int = numbers.second) {
        numbers = Pair(a, b)
        subjectCalc.onNext(this)
    }

    fun handleInput(inputLine: String?) {
        if(!inputLine.equals("exit")) {
            val pattern: Pattern = Pattern.compile("([a|b])(?:\\s?=(?:\\s)?(\\d*))")

            var a: Int? = null
            var b: Int? = null

            val matcher: Matcher = pattern.matcher(inputLine)

            if(matcher.matches() && matcher.group(1) != null && matcher.group(2) != null) {
                if(matcher.group(1).lowercase() == "a") {
                    a = matcher.group(2).toInt()
                } else if(matcher.group(1).lowercase() == "b") {
                    b = matcher.group(2).toInt()
                }
            }

            when {
                a != null && b != null -> modifyNumbers(a, b)
                a != null -> modifyNumbers(a = a)
                b != null -> modifyNumbers(b = b)
                else -> println("Invalid Input")
            }
        }
    }
}