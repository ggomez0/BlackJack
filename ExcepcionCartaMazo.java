@SuppressWarnings("serial")
public class ExcepcionCartaMazo extends Exception {
	private int posicionID = 0;

	public ExcepcionCartaMazo(int PosicionInvalida) {
		posicionID = PosicionInvalida;
		System.out.println("Posicion invalida" + PosicionInvalida);
	}
	@SuppressWarnings("unused")
	private ExcepcionCartaMazo() {
		System.out.println("Posicion invalida");
	}

	public String toString() {
		return ("Intento obtener una carta de una posicion que no estaba en el mazo " + this.posicionID);
	}

	public int getPosicionValor() {
		return posicionID;
	}
}