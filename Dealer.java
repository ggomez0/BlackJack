public class Dealer  {

	private Mano mano = new Mano();

	// Determina si el dealer tiene blackjack
	public boolean EsBlackjack(){
		if (mano.calcularTotal() == 21){
			return true;
		} else {
			return false;
		}
	}
	
	// Automatiza las jugadas del dealer
	public void JugadaDealer(Mazo mazo){
		System.out.println();
		while (mano.calcularTotal() <= 16) {
			System.out.println("El dealer tiene " + mano.calcularTotal()+ " y pide carta");
			mano.agregarCarta(mazo.nextCarta());
			System.out.println("Dealer " + this.getManoString(true, false));
		} 
		if ( mano.calcularTotal() > 21) {
			System.out.println("El dealer se paso. " + this.getManoString(true, false));
		} else {
			System.out.println("El dealer se queda. " + this.getManoString(true, false));
		}
	}
	
	// Agrega una carta al dealer
	public void agregarCarta(Carta carta) {
		mano.agregarCarta(carta);
	}
	
	// Convierte la mano del dealer en string
	public String getManoString(boolean EsDealer, boolean CartaOculta ) {
		String str = "Cartas: " + mano.toString(EsDealer, CartaOculta);
		return str;
	}
	
	// Calcula el total de cartas del dealer
	public int calculaTotal() {
		return mano.calcularTotal();
	}
	
	// Limpia la mano del dealer
	public void limpiaMano() {
		mano.limpiarMano();
	}
	
	// Voltea carta oculta del dealer
	public boolean peek() {
		return mano.dealerPeek();
	}
} 