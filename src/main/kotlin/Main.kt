import kotlin.random.Random

fun main() {
    println("==============================")
    println("Bienvenido al juego de dados")
    println("==============================")
    println()
    println("Tu contrincante soy yo (tu computadora)")
    // Variables
    val juego = JuegoDados()
    while (juego.turnoActual <= juego.numeroTurnos){
        println("Inicia el set número ${juego.turnoActual}")
        // Turno de jugador
        println("Dados del Jugador")
        for(i in 1..juego.numeroDados){
            juego.resultadoJugador+= juego.tirarDado(i)
        }

        println("Dados de la computadora")
        // Turno computadora
        for(j in 1..juego.numeroDados){
            juego.resultadoComputadora+= juego.tirarDado(j)
        }
        // Declarar ganador de la partida
        juego.declararGanadorPartida()
        if(juego.turnoActual == juego.numeroTurnos){
            juego.declararGanadorJuego()

            var opcionMarcada = false
            while (!opcionMarcada){
                println("¿Desea iniciar un nuevo juego (1 - SI / 2 - NO)?")
                val opcion = readln()
                opcionMarcada = when(opcion){
                    "1" -> {
                        juego.restablecerDatosJuego()
                        true
                    }
                    "2" ->  {
                        juego.turnoActual++
                        true
                    }
                    else -> {
                        println("Debe ingresar una opción válida...")
                        false
                    }
                }
            }

        }else{
            println("Presione una tecla para continuar con el siguiente set")
            readln()
            // Restablecer los datos del set
            juego.restablecerDatosPartida()
            // Sumar turno
            juego.turnoActual++
        }
    }

    println("Hasta una próxima oportunidad. Estaré esperándota para un nuevo duelo :)")
}

class JuegoDados(val numeroDados: Int = 2, val numeroTurnos: Int = 3){
    var turnoActual: Int = 1
    var resultadoJugador: Int = 0
    var resultadoComputadora: Int = 0
    var puntosJugador: Int = 0
    var puntosComputadora: Int = 0

    init {
        require(numeroDados > 0){ "El número de dados debe ser mayor a 0. Valor actual = $numeroDados"}
        require(numeroTurnos > 0){ "El número de turnos debe ser mayor a 0. Valor actual = $numeroTurnos"}
        println("Presiona una tecla para comenzar el juego")
        readln()
    }

    fun tirarDado(numeroDado: Int): Int {
        val numero = Random.nextInt(1, 7)
        println("El dado $numeroDado ha sido lanzado. Ha salido el número $numero")
        return numero
    }

    fun declararGanadorPartida(){
        val esGanadorJugador =  resultadoJugador > resultadoComputadora
        println()
        println("¡El ganador de la partida ${if(esGanadorJugador) "eres tú! Sigue así!!!." else "soy yo, tu computadora! Aún no te rindas!!!"}")
        if(esGanadorJugador) puntosJugador+=1 else puntosComputadora+=1
    }

    fun declararGanadorJuego(){
        println()
        println("El juego ha terminado")
        println("Verificando resultados....")
        println()
        val esGanadorJugador =  puntosJugador > puntosComputadora
        println("¡El ganador del juego ${if(esGanadorJugador) "ERES TÚ! FELICITACIONES, LO HAS LOGRADO!!! XD." else "soy yo, TU COMPUTADORA! Mejor suerte para la próxima :("}")
        println()
        println()
    }

    fun restablecerDatosPartida(){
        this.resultadoJugador = 0
        this.resultadoComputadora = 0
    }

    fun restablecerDatosJuego(){
        restablecerDatosPartida()
        this.turnoActual = 1
        this.puntosJugador = 0
        this.puntosComputadora = 0
    }

}