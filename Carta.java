public class Carta 
{
	private char palo;
	private int valor;

	@SuppressWarnings("unused")
	private Carta() {
		palo = ' ';
		valor = 0;
	}

	public Carta(char NuevoPalo, int NuevoValor) throws ExcepcionInvalidoValorCarta, ExcepcionInvalidoCartaPalo {
		if (NuevoValor < 1 || NuevoValor > 13) {
			throw new ExcepcionInvalidoValorCarta(NuevoValor);
		} else {
			this.valor = NuevoValor;
		}
		if (NuevoPalo != 'C' && NuevoPalo != 'T' && NuevoPalo != 'P' && NuevoPalo != 'D') {
			throw new ExcepcionInvalidoCartaPalo(NuevoPalo);
		} else {
			this.palo = NuevoPalo;
		}
	}

	public String toString() {
		return getPaloNom() + " " + this.valor;
	}

	public String getPaloNom() {
		String Palo;
		if (this.palo == 'C') {
			Palo = "Corazon";
		}
		else if (this.palo == 'T') {
			Palo = "Trebol";
		}
		else if (this.palo == 'P') {
			Palo = "Pica";
		}
		else if (this.palo == 'D') {
			Palo = "Diamante";
		} else {
			Palo = "Desconocido";
		}
		return Palo;
	}
	
	public char getPalod() {
		return palo;
	}
	
	public String getValorNom(){
		String nombre = "Desconocido";
		if (this.valor == 1) {		
			nombre = "As";
		}
		else if (this.valor == 2) {
			nombre = "Dos";
		}
		else if (this.valor == 3) {
			nombre = "Tres";
		}
		else if (this.valor == 4) {
			nombre = "Cuatro";
		}
		else if (this.valor == 5) {
			nombre = "Cinco";
		}
		else if (this.valor == 6) {
			nombre = "Seis";
		}
		else if (this.valor == 7) {
			nombre = "Siete";
		}
		else if (this.valor == 8) {
			nombre = "Ocho";
		}
		else if (this.valor == 9) {
			nombre = "Nueve";
		}
		else if (this.valor == 10) {
			nombre = "Diez";
		}
		else if (this.valor == 11) {
			nombre = "Jota";
		}
		else if (this.valor == 12) {
			nombre = "Reina";
		}
		else if (this.valor == 13) {
			nombre = "Rey";
		} 
		return nombre;
	}
	
	public int getValor() {
		return this.valor;
	}
	
	public boolean comparaPalos(Carta carta){
		return this.palo == carta.getPalod();
	}
	
	public boolean comparaValores(Carta carta){
		return this.valor == carta.getValor();
	}
	
	public boolean comparapalovalor(Carta carta){
		return this.palo == carta.getPalod() && this.valor == carta.getValor();
	}
} 