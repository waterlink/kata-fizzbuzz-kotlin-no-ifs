package fizzbuzz

import org.junit.Assert.assertEquals
import org.junit.Test

class FizzBuzzTest {

    private val fizzBuzz = FizzBuzz()

    @Test
    fun `encode - returns the number as is when not multiple of 3 or 5`() {
        assertEquals("unchanged number", "1", fizzBuzz.encode(1))
    }

    @Test
    fun `encode - returns the another number as is when not multiple of 3 or 5`() {
        assertEquals("unchanged number", "2", fizzBuzz.encode(2))
    }

    @Test
    fun `encode - returns fizz when number is multiple of 3`() {
        assertEquals("fizz-number is multiple of 3", "fizz", fizzBuzz.encode(3))
    }

    @Test
    fun `encode - returns fizz when another number is multiple of 3`() {
        assertEquals("fizz-number is multiple of 3", "fizz", fizzBuzz.encode(6))
    }

    @Test
    fun `encode - returns buzz when number is multiple of 5`() {
        assertEquals("buzz-number is multiple of 5", "buzz", fizzBuzz.encode(5))
    }

    @Test
    fun `encode - returns buzz when other number is multiple of 5`() {
        assertEquals("buzz-number is multiple of 5", "buzz", fizzBuzz.encode(10))
    }

    @Test
    fun `encode - returns fizzbuzz when number is multiple of 5 and 3`() {
        assertEquals("fizzbuzz-number is multiple of 3 and 5", "fizzbuzz", fizzBuzz.encode(15))
    }

    @Test
    fun `encode - returns fizzbuzz when other number is multiple of 5 and 3`() {
        assertEquals("fizzbuzz-number is multiple of 3 and 5", "fizzbuzz", fizzBuzz.encode(60))
    }

    @Test
    fun `encode - returns fizz when number contains second digit 3`() {
        assertEquals("fizz-number is multiple of 3", "fizz", fizzBuzz.encode(13))
    }

    @Test
    fun `encode - returns fizz when other number contains second digit 3`() {
        assertEquals("fizz-number is multiple of 3", "fizz", fizzBuzz.encode(23))
    }

    @Test
    fun `encode - returns fizz when number contains first digit 3`() {
        assertEquals("fizz-number is multiple of 3", "fizz", fizzBuzz.encode(31))
    }

    @Test
    fun `encode - returns fizz when other number contains first digit 3`() {
        assertEquals("fizz-number is multiple of 3", "fizz", fizzBuzz.encode(32))
    }

    @Test
    fun `encode - returns fizz when other number contains both digit 3`() {
        assertEquals("fizz-number is multiple of 3", "fizz", fizzBuzz.encode(33))
    }

    @Test
    fun `encode - returns fizzbuzz when number contains both digit 3 and is a multiple of 5`() {
        assertEquals("fizzbuzz-number is multiple of 5 and contains 3", "fizzbuzz", fizzBuzz.encode(35))
    }

    @Test
    fun `encode - returns fizzbuzz when number contains both digit 5 and is a multiple of 3`() {
        assertEquals("fizzbuzz-number is multiple of 3 and contains 5", "fizzbuzz", fizzBuzz.encode(54))
    }


}
