import java.util.Random;

@SuppressWarnings("serial")
public class Mazo extends Exception {
	private int NextCartaIndex;
	Carta[] mazo = new Carta[52];
	
	public Mazo(){
		int cont = 0;
		try {
		for (int i = 1; i <= 13; i++) {
			mazo[cont++] = new Carta('C', i);
		}
		for (int i = 1; i <= 13; i++) {
			mazo[cont++] = new Carta('T', i);
		}
		for (int i = 1; i <= 13; i++) {
			mazo[cont++] = new Carta('P', i);
		}
		for (int i = 1; i <= 13; i++) {
			mazo[cont++] = new Carta('D', i);
		}	
	}
		catch(ExcepcionInvalidoValorCarta | ExcepcionInvalidoCartaPalo exp1) {}
		NextCartaIndex = 0;
	}
	
	private void IndexBueno(int index) throws ExcepcionCartaMazo {
		if (index < 0 || index > 51) {
			throw new ExcepcionCartaMazo(index);
		}
	}
	
	public String toString(){
		String str = "totales";
		for (int i = 0; i < mazo.length; i++) {
			str +=	mazo[i].toString() + " ";
		}
		return str;
	}

	private void IntercambiarCarta(int index1, int index2) throws ExcepcionCartaMazo {	
		Carta aux;
		IndexBueno(index1);
		IndexBueno(index2);
		aux = mazo[index1];
		mazo[index1] = mazo[index2];
		mazo[index2] = aux;
	}

	public void barajar() throws ExcepcionCartaMazo {
		Random rd = new Random();
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < mazo.length; j++) {
				IntercambiarCarta(i, rd.nextInt(52));
			}
		}
		NextCartaIndex = 0;
	}
	
	public Carta getCarta(int index) throws ExcepcionCartaMazo{
		IndexBueno(index);
		return mazo[index];
	}

	public boolean comparacon(Mazo otroMazo) throws ExcepcionCartaMazo {
		for (int i=0; i < mazo.length; i++){
			if (! mazo[i].comparapalovalor(otroMazo.getCarta(i)) ) {
				return false;
			}
		}
		return true;
	}

	public Carta nextCarta() {
		if (NextCartaIndex < 0 || NextCartaIndex > 51) {
			System.out.println("Siguiente excepción, en este lugar");
		}
		return mazo[NextCartaIndex++];
	}
}