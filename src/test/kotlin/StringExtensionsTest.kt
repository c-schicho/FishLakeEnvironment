import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

class StringExtensionsTest {

    @Test
    fun testColorBlue() {
        assertThat(ANY_STRING.colorBlue()).isEqualTo("$COLOR_BLUE$ANY_STRING$COLOR_RESET")
    }

    @Test
    fun testColorRed() {
        assertThat(ANY_STRING.colorRed()).isEqualTo("$COLOR_RED$ANY_STRING$COLOR_RESET")
    }

    @Test
    fun testColorWhite() {
        assertThat(ANY_STRING.colorWhite()).isEqualTo("$COLOR_WHITE$ANY_STRING$COLOR_RESET")
    }

    @Test
    fun testColorGreen() {
        assertThat(ANY_STRING.colorGreen()).isEqualTo("$COLOR_GREEN$ANY_STRING$COLOR_RESET")
    }

    @Test
    fun testColorPurple() {
        assertThat(ANY_STRING.colorPurple()).isEqualTo("$COLOR_PURPLE$ANY_STRING$COLOR_RESET")
    }

    companion object {
        private const val ANY_STRING = "This is a test string."
        private const val COLOR_RESET = "\u001B[0m"
        private const val COLOR_BLUE = "\u001B[34m"
        private const val COLOR_RED = "\u001B[31m"
        private const val COLOR_WHITE = "\u001B[37m"
        private const val COLOR_GREEN = "\u001B[32m"
        private const val COLOR_PURPLE = "\u001B[35m"
    }
}