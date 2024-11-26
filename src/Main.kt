var gridSize: Int = 3
var grid = Array(gridSize) { CharArray(gridSize) }
var gameOver: Boolean = false
var player: Int = 1

fun main() {
    print("Insert a grid size(between 3 and 5): ")
    gridSize = readLine()!!.toInt()
    grid = Array(gridSize) { CharArray(gridSize) }

    var cnt: Int = 1
    var alphaCnt: Int = 17

    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (cnt < 10) {
                grid[i][j] = (cnt + '0'.toInt()).toChar()
                cnt++
            } else {
                grid[i][j] = (alphaCnt + '0'.toInt()).toChar()
                alphaCnt++
            }
        }
    }

    while (!gameOver) {
        if (gameOver) break
        setField()
        inputCharacter()
    }
}

fun inputCharacter() {
    val input = readLine()!!
    val XorY: Char = if (player == 1) 'X' else 'O'
    val character: Char
    var charExists = false
    var rowInserted: Int = -1
    var columnInserted: Int = -1

    if (input.length == 1) {
        character = input[0]

        for (i in grid.indices) {
            for (j in grid.indices) {
                if (grid[i][j] == character) {
                    grid[i][j] = XorY
                    charExists = true
                    rowInserted = i // Update rowInserted
                    columnInserted = j // Update columnInserted
                    break
                }
            }
        }

        if (!charExists) {
            println("Invalid input! This cell has already been used! Try again!")
            return
        }

        //if character exists then function doesn't break so we continue working on our operations

        if (checkHorizontally(rowInserted, character) ||
            checkVertically(columnInserted, character) ||
            checkDiagonally(character)) {

            gameOver = true
            println("Player $player won the game!")
            return
        }

        player = if(player == 1) 2 else  1
    }

}

fun setField() {
    println("Player 1 - O \nPlayer 2 - X \nPlayer $player 's turn\n")

    for (i in grid.indices) {
        println(grid[i])
    }

    var row = 0
    for (i in 0 until gridSize * 2 - 1) {
        for (j in 0 until gridSize) {
            if (i % 2 == 0) {
                if (j == gridSize - 1) {
                    print("  ${grid[row][j]}  ")
                } else {
                    print(" ${grid[row][j]}  |")
                }
            } else {
                if (j == gridSize - 1) {
                    print("____")
                } else {
                    print("____|")
                }
            }
        }
        if (i % 2 == 0) {
            row++
        }
        println()
    }
}

fun checkHorizontally(row: Int, character: Char): Boolean {

    //check if horizontal layer contains the same elements
    for(gridCharacter in grid[row]) {
        if(character != gridCharacter) {
            return  false
        }
    }
    return true
}

fun checkVertically(column: Int, character: Char): Boolean {
    for(row in grid.indices) {
        if(character != grid[row][column] ) {
            return  false
        }
    }
    return true
}

fun checkDiagonally(character: Char): Boolean {
    var primaryDiagonal = true
    var secondaryDiagonal = true

    for (i in grid.indices) {
        // Check primary diagonal
        if (character != grid[i][i]) {
            primaryDiagonal = false
        }

        // Check secondary diagonal
        val j = grid.size - 1 - i
        if (character != grid[i][j]) {
            secondaryDiagonal = false
        }
    }

    // Return true if either diagonal matches
    return primaryDiagonal || secondaryDiagonal
}
