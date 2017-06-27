package fizzbuzz

class FizzBuzz {

    private val factoriesForThree = mapOf<NumberClassification, NumberFactory>(
            NumberClassification(three = 0, five = 1) to FizzNumberFactory(),
            NumberClassification(three = 1, five = 0) to BuzzNumberFactory(),
            NumberClassification(three = 0, five = 0) to FizzBuzzNumberFactory(),
            NumberClassification(three = 1, five = 1) to UnchangedNumberFactory()
    )

    fun encode(number: Int): String {
        val classification = NumberClassification(three = number.remainder(3), five = number.remainder(5))
        return factoriesForThree[classification]!!.create(number).encode()
    }

}

private fun Int.remainder(modulo: Int): Int {
    return rem(modulo)
}

class NumberClassification(val three: Int, val five: Int) {

    override fun equals(other: Any?): Boolean {
        val otherAsClassification = other as NumberClassification

        return ((otherAsClassification.three > 0 && three > 0) ||
                (other.three == 0 && three == 0)) &&

                ((otherAsClassification.five > 0 && five > 0) ||
                (other.five == 0 && five == 0))
    }

    override fun hashCode(): Int {
        return 0
    }

}

class UnchangedNumber(private val number: Int) : OurNumber {

    override fun encode(): String {
        return number.toString()
    }

}

class UnchangedNumberFactory : NumberFactory {

    override fun create(number: Int): OurNumber {
        return UnchangedNumber(number)
    }

}

class FizzNumber : OurNumber {

    override fun encode(): String {
        return "fizz"
    }

}

class FizzNumberFactory : NumberFactory {

    override fun create(number: Int): OurNumber {
        return FizzNumber()
    }

}

class BuzzNumber : OurNumber {

    override fun encode(): String {
        return "buzz"
    }

}

class BuzzNumberFactory : NumberFactory {

    override fun create(number: Int): OurNumber {
        return BuzzNumber()
    }

}

class FizzBuzzNumber : OurNumber {

    override fun encode(): String {
        return "fizzbuzz"
    }

}

class FizzBuzzNumberFactory : NumberFactory {

    override fun create(number: Int): OurNumber {
        return FizzBuzzNumber()
    }

}

interface NumberFactory {
    fun create(number: Int): OurNumber
}

interface OurNumber {
    fun encode(): String
}
