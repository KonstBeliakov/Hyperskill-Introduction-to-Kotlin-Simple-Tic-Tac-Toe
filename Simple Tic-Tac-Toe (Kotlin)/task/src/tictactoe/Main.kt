package tictactoe

fun getGrid(): List<MutableList<Char>> {
    val state = readln()
    val grid = List(3) { MutableList(3) { '_' } }
    for (i in 0 until 9) {
        grid[i / 3][i % 3] = state[i]
    }
    return grid
}

fun xoWins(grid: List<MutableList<Char>>, char: Char): Boolean {
    // checking for rows
    if (grid.any{row -> row.all{e -> e == char}}) return true

    // checking for columns
    for(i in 0 until 3){
        if (grid[0][i] == char && grid[1][i] == char && grid[2][i] == char){
            return true
        }
    }

    // checking for diagonals
    if (grid[0][0] == char && grid[1][1] == char && grid[2][2] == char) return true
    return (grid[0][2] == char && grid[1][1] == char && grid[2][0] == char)
}

fun checkGrid(grid: List<MutableList<Char>>): String {
    if (xoWins(grid, 'X') && xoWins(grid, 'O')) return "Impossible"
    if (xoWins(grid, 'X')) return "X wins"
    if (xoWins(grid, 'O')) return "O wins"
    if (grid.any { row -> '_' in row }) return "Game not finished"
    return "Draw"
}

fun printGrid(grid: List<MutableList<Char>>){
    println("---------")
    for (line in grid) {
        print("| ")
        for(cell in line){
            print(if (cell != '_') "$cell " else "  ")
        }
        println("|")
    }
    println("---------")
}

fun main() {
    val grid = List(3) { MutableList(3) {'_'} }
    printGrid(grid)
    var gameOver = false
    var currentPlayerChar = 'X'
    while(!gameOver) {
        var moveMade = false
        while (!moveMade) {
            try {
                val (x, y) = readln().split(" ").map { it.toInt() }
                if (x !in 1..3 || y !in 1..3) {
                    println("Coordinates should be from 1 to 3!")
                } else if (grid[x - 1][y - 1] != '_') {
                    println("This cell is occupied! Choose another one!")
                } else {
                    grid[x - 1][y - 1] = currentPlayerChar
                    currentPlayerChar = if (currentPlayerChar == 'X') 'O' else 'X'
                    moveMade = true
                }
            } catch (e: NumberFormatException) {
                println("You should enter numbers")
            }
        }
        printGrid(grid)
        val state = checkGrid(grid)
        if (state != "Game not finished") {
            gameOver = true
            println(state)
        }
    }
}