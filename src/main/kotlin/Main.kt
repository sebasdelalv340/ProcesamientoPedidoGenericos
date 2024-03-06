/**
 * Interfaz genérica para procesar pedidos de cualquier tipo.
 */
interface ProcesadorPedido<T> {
    /**
     * Método para procesar un pedido de tipo T.
     * @param pedido el pedido a procesar.
     */
    fun procesar(pedido: T)
}

/**
 * Implementación del procesador para pedidos digitales.
 */
class ProcesadorDigital: ProcesadorPedido<PedidoDigital> {
    override fun procesar(pedido: PedidoDigital) {
        println("Procesando pedido digital: ${pedido.detalle} con url '${pedido.ulrDescarga}'.")
    }

}

/**
 * Implementación del procesador para pedidos físicos.
 */
class ProcesadorFisico: ProcesadorPedido<PedidoFisico> {
    override fun procesar(pedido: PedidoFisico) {
        println("Procesando pedido físico: ${pedido.detalle} con dirección '${pedido.direccionEnvio}'.")
    }
}

/**
 * Implementación del procesador para pedidos de suscripción.
 */
class ProcesadorSuscripcion: ProcesadorPedido<PedidoSuscripcion> {
    override fun procesar(pedido: PedidoSuscripcion) {
        println("Procesando pedido suscripción: ${pedido.detalle}, duración = ${pedido.duraccionMeses} meses.")
    }
}

/**
 * Datos de un pedido digital.
 * @property id el identificador del pedido.
 * @property tipo el tipo de pedido.
 * @property detalle una descripción detallada del pedido.
 * @property ulrDescarga la URL de descarga para pedidos digitales.
 */
data class PedidoDigital(val id: Int, val tipo: String, val detalle: String, val ulrDescarga: String)

/**
 * Datos de un pedido físico.
 * @property id el identificador del pedido.
 * @property tipo el tipo de pedido.
 * @property detalle una descripción detallada del pedido.
 * @property direccionEnvio la dirección de envío para pedidos físicos.
 */
data class PedidoFisico(val id: Int, val tipo: String, val detalle: String, val direccionEnvio: String)

/**
 * Datos de un pedido de suscripción.
 * @property id el identificador del pedido.
 * @property tipo el tipo de pedido.
 * @property detalle una descripción detallada del pedido.
 * @property duraccionMeses la duración en meses de la suscripción.
 */
data class PedidoSuscripcion(val id: Int, val tipo: String, val detalle: String, val duraccionMeses: Int)


/**
* Clase para gestionar pedidos de cualquier tipo.
* @param procesador el procesador específico para los pedidos.
*/
class GestorPedidos<T>(val procesador: ProcesadorPedido<T>) {
    /**
     * Método para procesar un pedido de cualquier tipo.
     * @param pedido el pedido a procesar.
     */
    fun procesarPedido(pedido: T) {
        procesador.procesar(pedido)
    }
}

fun main() {
    // Creación de gestores de pedidos para cada tipo de procesador
    val gestorPedidosDigital = GestorPedidos(ProcesadorDigital())
    val gestorPedidosFisico = GestorPedidos(ProcesadorFisico())
    val gestorPedidosSuscripcion = GestorPedidos(ProcesadorSuscripcion())

    // Ejemplos de pedidos de diferentes tipos
    val pedido1 = PedidoDigital(1, "digital", "E-book de kotlin", "www.descargas.com")
    gestorPedidosDigital.procesarPedido(pedido1)

    val pedido2 = PedidoFisico(2, "fisico", "Libro impreso de kotlin", "c/Goya, 12")
    gestorPedidosFisico.procesarPedido(pedido2)

    val pedido3 = PedidoSuscripcion(3, "suscripcion", "Suscripcion a curso de kotlin", 12)
    gestorPedidosSuscripcion.procesarPedido(pedido3)

    /**
     * El código aceptará cualquier tipo de pedido nuevo que creemos, ya que pasan por el interfaz genérico.
     * Solo deberemos añadir una clase de procesador que gestione ese tipo nuevo de pedido.
     */
}