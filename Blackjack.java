public class Blackjack {
	public static void main(String[] args) throws Exception {

		Blackjackjuego mijuego = new Blackjackjuego();
		mijuego.ComienzoJuego();
		
		do {
			mijuego.mezclar();
			mijuego.getApuestas();
			mijuego.ReparteCartas();
			mijuego.imprimeestado();
			mijuego.ComprobarBlackjack();
			mijuego.PedirQuedarse();
			mijuego.dealerjuega();
			mijuego.AceptaApuesta();
			mijuego.ImprimeDinero();
			mijuego.ReiniciaMano();
		} 
		while (mijuego.Jugardenuevo());
			mijuego.TerminaJuego();
		
	}
}

