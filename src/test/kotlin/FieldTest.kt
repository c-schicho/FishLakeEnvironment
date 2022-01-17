import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FieldTest {

    @BeforeEach
    fun setUpTest() {
        field = Field()
    }

    @Test
    fun testInitialFieldState() {
        assertThat(field.isWater()).isTrue()
    }

    @Test
    fun testSetFish() {
        field.setFish()

        assertThat(field.isFisher()).isFalse()
        assertThat(field.isFood()).isFalse()
        assertThat(field.isStone()).isFalse()
        assertThat(field.isWater()).isFalse()
    }

    @Test
    fun testSetFisher() {
        field.setFisher()

        assertThat(field.isFisher()).isTrue()
    }

    @Test
    fun testSetFood() {
        field.setFood()

        assertThat(field.isFood()).isTrue()
    }

    @Test
    fun testSetStone() {
        field.setStone()

        assertThat(field.isStone()).isTrue()
    }

    @Test
    fun testSetWater() {
        field.setFish()
        field.setWater()

        assertThat(field.isWater()).isTrue()
    }

    @Test
    fun testFishToString() {
        field.setFish()

        assertThat(field.toString()).isEqualTo(" F ".colorPurple())
    }

    @Test
    fun testFisherToString() {
        field.setFisher()

        assertThat(field.toString()).isEqualTo(" X ".colorRed())
    }

    @Test
    fun testFoodToString() {
        field.setFood()

        assertThat(field.toString()).isEqualTo(" O ".colorGreen())
    }

    @Test
    fun testStoneToString() {
        field.setStone()

        assertThat(field.toString()).isEqualTo(" # ".colorWhite())
    }

    @Test
    fun testWaterToString() {
        field.setWater()

        assertThat(field.toString()).isEqualTo(" . ".colorBlue())
    }

    companion object {
        private lateinit var field: Field
    }
}