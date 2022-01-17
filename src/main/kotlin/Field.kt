class Field {
    private var occupiedBy: FieldState = FieldState.WATER

    fun setFish() {
        occupiedBy = FieldState.FISH
    }

    fun setFisher() {
        occupiedBy = FieldState.FISHER
    }

    fun setFood() {
        occupiedBy = FieldState.FOOD
    }

    fun setStone() {
        occupiedBy = FieldState.STONE
    }

    fun setWater() {
        occupiedBy = FieldState.WATER
    }

    fun isFisher() = occupiedBy == FieldState.FISHER

    fun isFood() = occupiedBy == FieldState.FOOD

    fun isStone() = occupiedBy == FieldState.STONE

    fun isWater() = occupiedBy == FieldState.WATER

    override fun toString() = occupiedBy.symbol

    private enum class FieldState(
        val symbol: String
    ) {
        FISH(" F ".colorPurple()),
        FISHER(" X ".colorRed()),
        FOOD(" O ".colorGreen()),
        STONE(" # ".colorWhite()),
        WATER(" . ".colorBlue());

    }
}
