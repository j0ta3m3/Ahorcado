var letra = ""
var palabra = ""
/*
*
* Esta función se encarga de lanzar el mensaje para pedir una palabra
* y darla por válida si no es menor que 5
*
* @property palabra La palabra introducida por el usuario
* @return palabra La palabra una vez comprobada que es válida
*/

fun leerPalabra():String {
    do {
        println("Introduce una palabra que contenga mas de 5 letras:")
        palabra = readLine().toString().uppercase()
        if  (palabra.length < 5 ) {
            println("ERROR: Has introducido una palabra menor de 5 letras")
        } else {
            return palabra
        }
    } while (true)
}

fun LeerLetras(): String {
    do {
        println("Introduce una letra ")

        letra = readLine().toString().uppercase()
        if (letra.length > 1){
            println("ERROR:Has introducido más de una letra")
        } else { return letra }
    } while (true)
}

fun main(args: Array<String>){
    val secreta = leerPalabra()
    val oculta = Array(secreta.length) {"_"}
    oculta.forEach { print("$it ") }
    println()
    // GAME
    val letrasUsadas: MutableList<String> = mutableListOf()
    var error = 0
    var acierto = 0
    do { // bucle de pedir letra (hasta victoria o errores = 5)
        val letra = LeerLetras()
        // comprueba si la letra ya se ha utilizado
        if (letrasUsadas.contains(letra)) {
            println("La letra $letra ya la has dicho.")
            print("Letras usadas :")
            letrasUsadas.forEach { print("$it ") }
            println()
            oculta.forEach { print("$it ") }
            println()
        } else {
            letrasUsadas.add(letra)
            //comprueba si la letra es acierto o error
            if (letra in secreta) { //ACIERTO
                acierto++

                for (index in secreta.indices) {
                    if (letra == secreta[index].toString()) oculta[index]=letra[0].toString()
                }
                oculta.forEach { print("$it ") }
                println()
                if (secreta == oculta.joinToString(separator = "")) {
                    println("¡ Has ganado !")
                    break
                }
            } else {

                error++
                println("ERROR $error")
                if (error == 5) println(" Has perdido . La palabra era $secreta")
            }
        }
    } while (error < 5 )
}