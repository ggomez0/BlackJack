public class Jugador {
	private int banca;
	private int apuesta;
	private String nombre;
	private Mano mano;
	
	// Crea el Objeto Jugador.
	public Jugador() {
		banca = 100;
		mano = new Mano();
	}
	
	// Obtiene la cantidad de dinero de un jugador.
	public int getBanca() {
		return banca;
	}
	
	// Saca lo que aposto el jugador si pierde.
	public void perdida() {
		banca -= apuesta;
		apuesta = 0;
	}
	// Agrega lo que aposto el jugador a su banca si gana.
	public void gano() {
		banca += apuesta;
		apuesta = 0;
	}
	
	// Pone la banca del jugador en -1. A -1 no se puede llegar entonces se lo saca del juego
	public void SacadelJuego() {
		banca = -1;
	}
	
	// Reinicia la banca del jugador a 0. Usado para reiniciar las bancas de los jugadores de -1 a 0.
	public void reiniciaBanca() {
		banca = 0;
	}
	
	// Calcula la ganancia de la apuesta cuando sale Blackjack
	public void blackjack() {
		banca += apuesta * 1.5;
		apuesta = 0;
	}
	
	// Convierte la apuesta del jugador en 0 cuando se empata
	public void empate() {
		apuesta = 0;
	}
	
	// Pide la apuesta del jugador
	public void setApuesta(int nuevaApuesta) {
		apuesta = nuevaApuesta;
	}
	
	// Ingresa el nombre de un jugador.
	public void setNombre(String nombre1){
		nombre = nombre1;
	}
	
	// Obtener el nombre del jugador.
	public String getNombre() {
		return nombre;
	}
	
	// Tener el total de cartas.
	public int getTotal() {
		return mano.calcularTotal();
	}
	
	// Obtiene la apuesta del jugador.
	public int getApuesta(){
		return this.apuesta;
	}
		
	// Agrega una carta a la mano del jugador.
	public void agregaCarta(Carta carta) {
		mano.agregarCarta(carta);

	}
	
	// Obtiene las cartas de los jugadores como string
	public String getManoString() {
		String str = "Cartas:" + mano.toString();
		return str;
	}
		
	// Limpia la mano del jugador
	public void limpiamano() {
		mano.limpiarMano();
	}
		
}