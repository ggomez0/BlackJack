@SuppressWarnings("serial")
public class ExcepcionInvalidoValorCarta extends Exception{
	private int IdValor = 0;

	public ExcepcionInvalidoValorCarta(int ValorInvalido) {
		IdValor = ValorInvalido;
		System.out.println("Valor invalido " + ValorInvalido);
	}

	@SuppressWarnings("unused")
	private ExcepcionInvalidoValorCarta() {
		System.out.println("Valor invalido");
	}

	public String toString() {
		return ("Intento crear una carta no valida" + this.IdValor);
	}

	public int getValor() {
		return IdValor;
	}
}