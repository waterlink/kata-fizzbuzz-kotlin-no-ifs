package fizzbuzz

import fizzbuzz.NumberClassification.Companion.IS_FIVE
import fizzbuzz.NumberClassification.Companion.IS_THREE
import fizzbuzz.NumberClassification.Companion.MULTIPLE_OF_FIVE
import fizzbuzz.NumberClassification.Companion.MULTIPLE_OF_THREE
import fizzbuzz.NumberClassification.Companion.NON_MULTIPLE
import fizzbuzz.NumberClassification.Companion.WHATEVER

class FizzBuzz {
    private val factoriesForThree = mapOf<NumberClassification, NumberFactory>(
            NumberClassification(MULTIPLE_OF_THREE, NON_MULTIPLE, firstDigit = WHATEVER, secondDigit = WHATEVER) to FizzNumberFactory(),
            NumberClassification(MULTIPLE_OF_THREE, NON_MULTIPLE, firstDigit = WHATEVER, secondDigit = IS_THREE) to FizzNumberFactory(),
            NumberClassification(NON_MULTIPLE, NON_MULTIPLE, firstDigit = WHATEVER, secondDigit = IS_THREE) to FizzNumberFactory(),
            NumberClassification(NON_MULTIPLE, NON_MULTIPLE, firstDigit = IS_THREE, secondDigit = WHATEVER) to FizzNumberFactory(),
            NumberClassification(MULTIPLE_OF_THREE, NON_MULTIPLE, firstDigit = IS_THREE, secondDigit = IS_THREE) to FizzNumberFactory(),
            NumberClassification(NON_MULTIPLE, MULTIPLE_OF_FIVE, firstDigit = IS_THREE, secondDigit = IS_FIVE) to FizzBuzzNumberFactory(),
            NumberClassification(NON_MULTIPLE, MULTIPLE_OF_FIVE, firstDigit = WHATEVER, secondDigit = WHATEVER) to BuzzNumberFactory(),
            NumberClassification(MULTIPLE_OF_THREE, MULTIPLE_OF_FIVE, firstDigit = WHATEVER, secondDigit = WHATEVER) to FizzBuzzNumberFactory(),
            NumberClassification(NON_MULTIPLE, NON_MULTIPLE, firstDigit = WHATEVER, secondDigit = WHATEVER) to
                    UnchangedNumberFactory(),
            NumberClassification(MULTIPLE_OF_THREE, NON_MULTIPLE, firstDigit = IS_FIVE, secondDigit = WHATEVER) to
                    FizzBuzzNumberFactory(),

            NumberClassification(NON_MULTIPLE, MULTIPLE_OF_FIVE, firstDigit=WHATEVER, secondDigit=IS_FIVE) to
                    BuzzNumberFactory(),

            NumberClassification(MULTIPLE_OF_THREE, MULTIPLE_OF_FIVE, firstDigit=WHATEVER, secondDigit=IS_FIVE) to
                    FizzBuzzNumberFactory()
    )

    fun encode(number: Int): String {
        val classification = NumberClassification.create(number)

        val numberFactory = factoriesForThree.getOrElse(classification) {
            throw UnableToFindClassification(classification)
        }

        return numberFactory.create(number).encode()
    }

}

class UnableToFindClassification(classification: NumberClassification) : RuntimeException("Unable to find " +
        "classification $classification")

private fun Int.remainder(modulo: Int): Int {
    return rem(modulo)
}

data class NumberClassification(val three: Named?,
                                val five: Named?,
                                val firstDigit: Named?,
                                val secondDigit: Named?) {

    data class Named(val name: String)

    companion object {
        val MULTIPLE_OF_THREE = Named("MULTIPLE_OF_THREE")
        val MULTIPLE_OF_FIVE = Named("MULTIPLE_OF_FIVE")
        val NON_MULTIPLE = Named("NON_MULTIPLE")
        val WHATEVER = Named("WHATEVER")
        val IS_THREE = Named("IS_THREE")
        val IS_FIVE = Named("IS_FIVE")

        private val FIVE_MULTIPLE_REPRESENTATIONS = mapOf(
                0 to MULTIPLE_OF_FIVE,
                1 to NON_MULTIPLE,
                2 to NON_MULTIPLE,
                3 to NON_MULTIPLE,
                4 to NON_MULTIPLE
        )

        private val THREE_MULTIPLE_REPRESENTATIONS = mapOf(
                0 to MULTIPLE_OF_THREE,
                1 to NON_MULTIPLE,
                2 to NON_MULTIPLE
        )

        private val DIGIT_REPRESENTATIONS = mapOf(
                0 to WHATEVER,
                1 to WHATEVER,
                2 to WHATEVER,
                3 to IS_THREE,
                4 to WHATEVER,
                5 to IS_FIVE,
                6 to WHATEVER,
                7 to WHATEVER,
                8 to WHATEVER,
                9 to WHATEVER
        )

        fun create(number: Int): NumberClassification {


            return NumberClassification(
                    three = THREE_MULTIPLE_REPRESENTATIONS[number.remainder(3)],
                    five = FIVE_MULTIPLE_REPRESENTATIONS[number.remainder(5)],
                    firstDigit = DIGIT_REPRESENTATIONS[number.div(10)],
                    secondDigit = DIGIT_REPRESENTATIONS[number.remainder(10)]
            )
        }
    }

//    override fun equals(other: Any?): Boolean {
//        other as NumberClassification
//
//        return ((other.three > 0 && three > 0) ||
//                (other.three == 0 && three == 0)) &&
//
//                ((other.five > 0 && five > 0) ||
//                        (other.five == 0 && five == 0)) &&
//
//                ((other.secondDigit == 3 && secondDigit == 3) ||
//                        other.secondDigit != 3 && secondDigit != 3) &&
//
//                ((other.firstDigit == 3 && firstDigit == 3) ||
//                        other.firstDigit != 3 && firstDigit != 3) &&
//
//                ((other.firstDigit == 5 && firstDigit == 5) ||
//                        other.firstDigit != 5 && firstDigit != 5)
//    }

//    override fun hashCode(): Int {
//        return 0
//    }

    override fun toString(): String {
        return "NumberClassification(${three?.name}, " +
                "${five?.name}, " +
                "firstDigit=${firstDigit?.name}, " +
                "secondDigit=${secondDigit?.name})"
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
