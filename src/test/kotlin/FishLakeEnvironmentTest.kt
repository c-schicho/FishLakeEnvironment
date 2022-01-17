import FishLakeEnvironment.Action
import FishLakeEnvironment.State
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FishLakeEnvironmentTest {

    @BeforeEach
    fun setUpTest() {
        environment = FishLakeEnvironment()
    }

    @Test
    fun testNStates() {
        assertThat(environment.nStates).isEqualTo(N_STATES)
    }

    @Test
    fun testNActions() {
        assertThat(environment.nActions).isEqualTo(N_ACTIONS)
    }

    @Test
    fun testActionSpace() {
        assertThat(environment.actionSpace).containsExactly(
            Action.UP,
            Action.RIGHT,
            Action.DOWN,
            Action.LEFT
        )
    }

    @Test
    fun testDoneFoundFood() {
        repeat(2) {
            environment.step(Action.RIGHT)
        }
        repeat(5) {
            environment.step(Action.DOWN)
        }

        assertThat(environment.done).isFalse()

        val (_, reward, _) = environment.step(Action.RIGHT)

        assertThat(reward).isEqualTo(FOOD_REWARD)
        assertThat(environment.done).isTrue()
    }

    @Test
    fun testDoneGetCaught() {
        repeat(2) {
            environment.step(Action.UP)
        }

        assertThat(environment.done).isFalse()

        val (_, reward, _) = environment.step(Action.RIGHT)

        assertThat(reward).isEqualTo(FISHER_REWARD)
        assertThat(environment.done).isTrue()
    }

    @Test
    fun testReset() {
        val state = environment.reset()

        assertThat(state).isEqualTo(INITIAL_FISH_POSITION)
    }

    @Test
    fun testStepUp() {
        val result = environment.step(Action.UP)

        assertThat(result.done).isFalse()
        assertThat(result.state).isEqualTo(INITIAL_FISH_POSITION.copy(y = 1))
    }

    @Test
    fun testStepDown() {
        val result = environment.step(Action.DOWN)

        assertThat(result.done).isFalse()
        assertThat(result.state).isEqualTo(INITIAL_FISH_POSITION.copy(y = 3))
    }

    @Test
    fun testStepLeft() {
        val result = environment.step(Action.LEFT)

        assertThat(result.done).isFalse()
        assertThat(result.state).isEqualTo(INITIAL_FISH_POSITION.copy(x = 1))
    }

    @Test
    fun testStepRight() {
        val result = environment.step(Action.RIGHT)

        assertThat(result.done).isFalse()
        assertThat(result.state).isEqualTo(INITIAL_FISH_POSITION.copy(x = 3))
    }

    companion object {
        private lateinit var environment: FishLakeEnvironment
        private const val N_STATES = 93
        private const val N_ACTIONS = 4
        private const val FOOD_REWARD = 10
        private const val FISHER_REWARD = -100
        private val INITIAL_FISH_POSITION = State(2, 2)
    }
}
