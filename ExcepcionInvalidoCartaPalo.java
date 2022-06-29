@SuppressWarnings("serial")
public class ExcepcionInvalidoCartaPalo extends Exception {
	private char PaloID = '?';

	public ExcepcionInvalidoCartaPalo (char PaloInvalido) {
		PaloID = PaloInvalido;
		System.out.println("Palo invalido " + PaloInvalido);
	}

	@SuppressWarnings("unused")
	private ExcepcionInvalidoCartaPalo() {
		System.out.println("Palo Invalido");
	}
	
	public String toString(){
		return ("Intento crear una carta no valida " + this.PaloID);
	}
	
	public char getPaloDesignator() {
		return PaloID;
	}
}