import FishLakeEnvironment.Action
import FishLakeEnvironment.State
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow

class GridTest {

    @BeforeEach
    fun setUpTest() {
        grid = Grid(GRID_SIZE)
    }

    @Test
    fun testCreatesInitialGrid() {
        assertDoesNotThrow { grid.toString() }
        assertThat(grid.getFishState()).isEqualTo(State(2, 2))
    }

    @Test
    fun testCreateNewGrid() {
        grid.moveFish(Action.RIGHT)
        grid.moveFish(Action.RIGHT)

        grid.createNewGrid()

        assertThat(grid.getFishState()).isEqualTo(State(2, 2))
    }

    @Test
    fun testMoveFishUp() {
        grid.moveFish(Action.UP)

        assertThat(grid.getFishState()).isEqualTo(State(2, 1))
    }

    @Test
    fun testMoveFishUpAgainstObstacle() {
        grid.moveFish(Action.LEFT)
        repeat(20) {
            grid.moveFish(Action.UP)
        }

        assertThat(grid.fishGotCaught).isFalse()
        assertThat(grid.fishReachedFood).isFalse()
        assertThat(grid.getFishState()).isEqualTo(State(1, 2))
    }

    @Test
    fun testMoveFishUpAgainstBorder() {
        repeat(20) {
            grid.moveFish(Action.UP)
        }

        assertThat(grid.fishGotCaught).isFalse()
        assertThat(grid.fishReachedFood).isFalse()
        assertThat(grid.getFishState()).isEqualTo(State(2, 0))
    }

    @Test
    fun testMoveFishDown() {
        grid.moveFish(Action.DOWN)

        assertThat(grid.getFishState()).isEqualTo(State(2, 3))
    }

    @Test
    fun testMoveFishDownAgainstObstacle() {
        repeat(20) {
            grid.moveFish(Action.DOWN)
        }

        assertThat(grid.fishGotCaught).isFalse()
        assertThat(grid.fishReachedFood).isFalse()
        assertThat(grid.getFishState()).isEqualTo(State(2, 4))
    }

    @Test
    fun testMoveFishDownAgainstBorder() {
        grid.moveFish(Action.RIGHT)
        repeat(20) {
            grid.moveFish(Action.DOWN)
        }

        assertThat(grid.fishGotCaught).isFalse()
        assertThat(grid.fishReachedFood).isFalse()
        assertThat(grid.getFishState()).isEqualTo(State(3, 9))
    }

    @Test
    fun testMoveFishLeft() {
        grid.moveFish(Action.LEFT)

        assertThat(grid.getFishState()).isEqualTo(State(1, 2))
    }

    @Test
    fun testMoveFishLeftAgainstObstacle() {
        grid.moveFish(Action.UP)
        repeat(20) {
            grid.moveFish(Action.LEFT)
        }

        assertThat(grid.fishGotCaught).isFalse()
        assertThat(grid.fishReachedFood).isFalse()
        assertThat(grid.getFishState()).isEqualTo(State(2, 1))
    }

    @Test
    fun testMoveFishLeftAgainstBorder() {
        repeat(20) {
            grid.moveFish(Action.LEFT)
        }

        assertThat(grid.fishGotCaught).isFalse()
        assertThat(grid.fishReachedFood).isFalse()
        assertThat(grid.getFishState()).isEqualTo(State(0, 2))
    }

    @Test
    fun testMoveFishRight() {
        grid.moveFish(Action.RIGHT)

        assertThat(grid.getFishState()).isEqualTo(State(3, 2))
    }

    @Test
    fun testMoveFishRightAgainstObstacle() {
        repeat(2) {
            grid.moveFish(Action.DOWN)
        }
        repeat(20) {
            grid.moveFish(Action.RIGHT)
        }

        assertThat(grid.fishGotCaught).isFalse()
        assertThat(grid.fishReachedFood).isFalse()
        assertThat(grid.getFishState()).isEqualTo(State(4, 4))
    }

    @Test
    fun testMoveFishRightAgainstBorder() {
        grid.moveFish(Action.UP)
        repeat(20) {
            grid.moveFish(Action.RIGHT)
        }

        assertThat(grid.fishGotCaught).isFalse()
        assertThat(grid.fishReachedFood).isFalse()
        assertThat(grid.getFishState()).isEqualTo(State(9, 1))
    }

    @Test
    fun testMoveFishOverFood() {
        repeat(2) {
            grid.moveFish(Action.RIGHT)
        }
        repeat(5) {
            grid.moveFish(Action.DOWN)
        }

        assertThat(grid.fishReachedFood).isFalse()
        assertThat(grid.fishGotCaught).isFalse()

        grid.moveFish(Action.RIGHT)

        assertThat(grid.fishReachedFood).isTrue()
        assertThat(grid.fishGotCaught).isFalse()
    }

    @Test
    fun testMoveFishOverFisher() {
        repeat(2) {
            grid.moveFish(Action.UP)
        }

        assertThat(grid.fishGotCaught).isFalse()
        assertThat(grid.fishReachedFood).isFalse()

        grid.moveFish(Action.RIGHT)

        assertThat(grid.fishGotCaught).isTrue()
        assertThat(grid.fishReachedFood).isFalse()
    }

    @Test
    fun testGetFishState() {
        assertThat(grid.getFishState()).isEqualTo(State(2, 2))

        grid.moveFish(Action.UP)

        assertThat(grid.getFishState()).isEqualTo(State(2, 1))

        grid.moveFish(Action.RIGHT)

        assertThat(grid.getFishState()).isEqualTo(State(3, 1))

        grid.moveFish(Action.DOWN)

        assertThat(grid.getFishState()).isEqualTo(State(3, 2))

        grid.moveFish(Action.LEFT)

        assertThat(grid.getFishState()).isEqualTo(State(2, 2))
    }

    @Test
    fun testGetNStones() {
        assertThat(grid.nStones).isEqualTo(N_STONES)
    }

    @Test
    fun testGetNFisher() {
        assertThat(grid.nFischer).isEqualTo(N_FISHER)
    }

    companion object {
        private lateinit var grid: Grid
        private const val GRID_SIZE = 10
        private const val N_FISHER = 4
        private const val N_STONES = 7
    }
}
