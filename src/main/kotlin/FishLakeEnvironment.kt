class FishLakeEnvironment {
    private val grid = Grid(GRID_SIZE)
    private var episodeDone = false

    val nStates
        get() = GRID_SIZE * GRID_SIZE - grid.nStones

    val nActions
        get() = Action.values().size

    val actionSpace
        get() = Action.values()

    val done
        get() = episodeDone

    fun reset(): State {
        grid.createNewGrid()
        episodeDone = false
        return grid.getFishState()
    }

    fun step(action: Action): EnvironmentState {
        if (episodeDone) {
            error("Episode is done, reset the environment to start a new one")
        }

        episodeDone = grid.moveFish(action)

        val reward = if (episodeDone && grid.fishGotCaught) {
            FISHER_REWARD
        } else if (episodeDone && grid.fishReachedFood) {
            FOOD_REWARD
        } else {
            MOVE_REWARD
        }

        return EnvironmentState(grid.getFishState(), reward, episodeDone)
    }

    fun printEnvironment() = println("\n${grid}")

    fun printLegend() = println(
        """
            F ... Fish
            X ... Fisher
            O ... Food
            # ... Stone
            . ... Water
        """.trimIndent()
    )

    companion object {
        private const val GRID_SIZE = 10
        private const val FISHER_REWARD = -100
        private const val FOOD_REWARD = 10
        private const val MOVE_REWARD = 0
    }

    data class EnvironmentState(
        val state: State,
        val reward: Int,
        val done: Boolean
    )

    data class State(
        val x: Int,
        val y: Int
    )

    enum class Action {
        UP, RIGHT, DOWN, LEFT
    }
}
