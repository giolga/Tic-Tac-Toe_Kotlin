val grid2 = arrayOf(
    charArrayOf('X', 'X', 'X'),
    charArrayOf('O', 'O', 'X'),
    charArrayOf('X', 'O', 'O')
)

fun main() {
    checkHorizontally2(0, 'X')
    checkHorizontally2(1, 'O')
}

fun checkHorizontally2(row: Int, character: Char): Boolean {
    //check if horizontal layer contains the same elements
    for(column in grid2[row].indices) {
//        println("Checking grid2[$row][$column]: ${grid2[row][column]}")
        if(character != grid2[row][column]) {
            println(false)
            return  false
        }
    }

    println(true)
    return true
}