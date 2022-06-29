public class Mano {
	private Carta[] LaMano = new Carta[12];
	private int CantCartas = 0;

	// Calcula el total de la mano y tambien decide si el as es 1 o 11
	public int calcularTotal() {
		int total =0;
		boolean BandAS = false;
		for (int i = 0; i < CantCartas; i++) {
			int valor = LaMano[i].getValor();
			if (valor > 10) {
				valor = 10;
			} else if ( valor == 1) {
				BandAS = true;
			}
			total += valor;
		}
		if (BandAS && total + 10 <= 21) {
			total += 10;
		}
		return total;
	}
	
	public String toString(){
		return this.toString(false, false);
	}
	
	public String toString(boolean EsDealer, boolean CartaOculta){
		String str = "";
		int total =0;
		boolean BandAS = false;
		String asString = "";
		for (int i = 0; i < CantCartas; i++) {
			if ( EsDealer && CartaOculta && i == 0) {
				str = "";
			} else {
				int valor = LaMano[i].getValor();
				String valorNom;
				if (valor > 10) {
					valorNom = LaMano[i].getValorNom().substring(0, 1);
				} else if ( valor == 1 ){
					valorNom = "A";
				} else {
					valorNom = Integer.toString(valor);
				}
						str += " " +valorNom + LaMano[i].getPalod();
				if (valor > 10) {
					valor = 10;
				} else if ( valor == 1) {
					BandAS = true;
				}
				total += valor;
			}
		}
		if (BandAS && total + 10 <= 21) {
			asString = " o "+ (total + 10);
		}
		if (CartaOculta) {
			return str;
		} else {
			return str+ " es igual a "+ total + asString;
		}
	}
	
	public void agregarCarta(Carta card) {
		LaMano[CantCartas++] = card;
	}
	
	public void limpiarMano() {
		CantCartas = 0;
	}
	
	public boolean dealerPeek() {
		int valor = LaMano[1].getValor();
		return valor == 1 || valor >= 10;
	}
} 