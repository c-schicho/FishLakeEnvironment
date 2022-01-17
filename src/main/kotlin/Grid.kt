import FishLakeEnvironment.Action
import FishLakeEnvironment.State

class Grid(
    private val gridSize: Int
) {
    private lateinit var grid: List<List<Field>>
    private lateinit var fishState: State

    init {
        createNewGrid()
    }

    val fishGotCaught
        get() = FISHER_STATES.contains(fishState)

    val fishReachedFood
        get() = FOOD_STATE == fishState

    val nFischer
        get() = FISHER_STATES.size

    val nStones
        get() = STONE_STATES.size

    fun getFishState() = fishState.copy()

    fun createNewGrid() {
        fishState = INITIAL_FISH_STATE

        grid = List(gridSize) { List(gridSize) { Field() } }
        grid[INITIAL_FISH_STATE].setFish()
        grid[FOOD_STATE].setFood()
        FISHER_STATES.forEach { grid[it].setFisher() }
        STONE_STATES.forEach { grid[it].setStone() }
    }

    fun moveFish(action: Action): Boolean {
        return when (action) {
            Action.UP -> performMove(fishState.x, fishState.y - 1)
            Action.RIGHT -> performMove(fishState.x + 1, fishState.y)
            Action.DOWN -> performMove(fishState.x, fishState.y + 1)
            Action.LEFT -> performMove(fishState.x - 1, fishState.y)
        }
    }

    override fun toString(): String {
        return grid.map {
            it.map { it.toString() }
        }.map {
            it.reduce { acc, s -> acc + s }
        }.reduce { acc, s -> acc + "\n" + s }
    }

    private fun performMove(x: Int, y: Int): Boolean {
        if (x < 0 || gridSize <= x || y < 0 || gridSize <= y) {
            return false
        }

        val newState = State(x, y)
        val newStateField = grid[newState]

        return if (newStateField.isFisher()) {
            grid[fishState].setWater()
            fishState = newState
            true
        } else if (newStateField.isWater() || newStateField.isFood()) {
            val isFood = newStateField.isFood()
            grid[fishState].setWater()
            newStateField.setFish()
            fishState = newState
            isFood
        } else {
            false
        }
    }

    companion object {
        private val INITIAL_FISH_STATE = State(2, 2)
        private val FOOD_STATE = State(5, 7)
        private val FISHER_STATES = listOf(
            State(3, 0),
            State(7, 2),
            State(2, 8),
            State(9, 9)
        )
        private val STONE_STATES = listOf(
            State(1, 1),
            State(7, 0),
            State(5, 4),
            State(2, 5),
            State(1, 9),
            State(7, 8),
            State(5, 9)
        )
    }

    private operator fun List<List<Field>>.get(index: State) = this[index.y][index.x]
}
