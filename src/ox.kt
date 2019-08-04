import kotlin.system.exitProcess


var player = 'X'
var row : Int = -1
var col : Int = -1
val table = arrayOf(
    arrayOf( '-', '-', '-'),
    arrayOf( '-', '-', '-'),
    arrayOf( '-', '-', '-')
)

fun printWelcome(){
    println("Welcome to OX Game")
}

fun printTable(){
    println(" 1 2 3")
    var rowIndex = 1
    for( row : Array<Char> in table){
        print("$rowIndex ")
        for(col : Char in row){
            print(" $col")
        }
        rowIndex++
        println()
    }
}

fun input(){
    while(true){
        try{
            print("Please input Row Col :")
            val str :String? = readLine()
            val input : List<String>? = str?.split(" ")

            if(input?.size != 2) {
                println("ERR: Must input 2 numbers")
                continue
            }
            row = input[0].toInt()
            col = input[1].toInt()
            if(row !in 1..3 || col !in 1..3){
                println("ERR: Must have no more than 3 numbers")
                continue
            }
            if(!setRowCol()){
                println("ERR: Do not repeat!!")
                continue
            }
            break
        }catch (t: Throwable) {
            println("ERR: ${t.message} ,Must be numbers Row Col (EX: 1 2)")
        }
    }
}

fun setRowCol() : Boolean{
    if(table[row-1][col-1] !=  '-') return false
    table[row-1][col-1] = player
    return true
}

fun printTurn(){
    println(" $player Turn")
}
fun checkLine(r:Int): Boolean{
    var count = 0
    for(c:Int in 0..2){
        if(table[r][c]==player){
            count++
        }
    }
    if(count==3){
        return true
    }
    return false
}

fun checkx1(): Boolean{
    var count = 0
    for (i in 0..2){
        if(table[i][i] == player){
            count++
        }
    }

    if(count == 3) {
        return true
    }
    return false
} //ตั้ง


fun checkx2(): Boolean{
   if(table[0][0] == player && table[1][1] == player &&table[2][2] == player){
      return true
  }
   if(table[2][0] == player && table[1][1] == player &&table[0][2] == player) {
        return true
   }
    return false
}


fun checkWin(): Boolean{
    for(r : Int in 0..2){
        if(checkLine(r)){
            return true
        }
    }
    return false
}


fun switchPlayer(){
    if(player == 'X'){
        player = 'O'
    }else{
        player = 'X'
    }
}
fun printWin(){
    println("$player win!")
}


fun main(){
    printWelcome()
    while (true) {
        printTable()
        printTurn()
        input()
        if(checkWin()){
            break
        }
        switchPlayer()
    }
    printWin()
}