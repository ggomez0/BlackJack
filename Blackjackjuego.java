import java.util.Scanner;

public class Blackjackjuego {
	private Scanner sc = new Scanner(System.in);
	private int usuarios; 
	private Jugador[] jugadores;
	private Mazo mazo;
	private Dealer dealer = new Dealer();

	// Comienza el juego y se muestran las reglas
	public void ComienzoJuego(){
		String nombres;
		System.out.println(" _     _            _    _            _ \n" + 
				"| |   | |          | |  (_)          | | \n" + 
				"| |__ | | __ _  ___| | ___  __ _  ___| | __\n" + 
				"| '_ \\| |/ _` |/ __| |/ / |/ _` |/ __| |/ /\n" + 
				"| |_) | | (_| | (__|   <| | (_| | (__|   < \n" + 
				"|_.__/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\\n" + 
				"  V.1.0                _/ |\n" + 
				"                      |__/ ");
		System.out.println("");
		System.out.println("  \nREGLAS DEL BLACKJACK AMERICANO: ");
		System.out.println("	-A cada jugadoBlackjackr se le da 2 cartas. Al dealer se le dan dos cartas, una boca arriba y otra boca abajo.");
		System.out.println("	-Las cartas son iguales a su valor. La j,q y la k tienen valor 10 y el as tiene valor 1 o 11");
		System.out.println("	-Las cartas del jugador que van siendo agregadas son sumadas al total.");
		System.out.println("	-Si el jugador decide pedir, el dealer le otorgara otra carta del mazo. En cambio los jugadores que deciden quedarse mantienen el total de sus cartas actual");
		System.out.println("	-El dealer tiene que pedir cartas hasta que iguala o supera el 17.");
		System.out.println("	-El objetivo es tener cartas mas altas que el dealer sin pasarse de 21.");
		System.out.println("	-Si el total de cartas del jugador son las mismas que las del dealer, es un empate y cada uno se queda con su apuesta."); 
		System.out.println("	-Los jugadores ganan su apuesta si superan al dealer. Los jugadores ganan un 1.5x si obtienen un blackjack que es llegar al 21.");
		System.out.println("        -Si el dealer saca un As o un 10 en su primera carta debe descubrir la segunda carta que tiene. En caso de que con ambas cartas tenga blackjack el juego acaba de inmediato, los jugadores que también tengan blackjack pueden mantener apuestas, los demás la pierden.\n");
		// Cantidad de jugadores y los crea (en una mesa regular de blackjack hay 6 espacios para jugar)
		do {
			System.out.print("Cuantos jugadores van a jugar (1-6)? ");
			usuarios = sc.nextInt();
			} 
		while (usuarios > 6 || usuarios <= 0);
			jugadores = new Jugador[usuarios];
			mazo = new Mazo();

		// Pedir el nombre de los jugadores y asignarlos
		for (int i = 0; i < usuarios; i++) {
			System.out.print("Cual es el nombre del jugador " + (i + 1) + "? ");
			nombres = sc.next();
			jugadores[i] = new Jugador();
			jugadores[i].setNombre(nombres);
			}
		}
	
	// Mezclar cartas
	public void mezclar() throws ExcepcionCartaMazo, ExcepcionInvalidoCartaPalo, ExcepcionInvalidoValorCarta {
		mazo.barajar();	
	}

	// Pedir apuestas a los jugadores
	public void getApuestas(){
		int ValorApuesta;
		for (int i =0; i < usuarios; i++) {  	
			if (jugadores[i].getBanca() > 0) {
				do {
					System.out.print("Cuanto quieres apostar " + jugadores[i].getNombre()  + (" (1-" + jugadores[i].getBanca()) + ")? " );
					ValorApuesta = sc.nextInt();
					jugadores[i].setApuesta(ValorApuesta);} 
				while (!( ValorApuesta > 0 && ValorApuesta <= jugadores[i].getBanca()));
					System.out.println("");
			}
		}
	}
	
	// Reparte las cartas al dealer y a los jugadores
	public void ReparteCartas(){
		for (int j = 0; j < 2; j++) {
			for (int i =0; i < usuarios; i++) {
				if (jugadores[i].getBanca() > 0) {
				jugadores[i].agregaCarta(mazo.nextCarta());
				}
			}
			dealer.agregarCarta(mazo.nextCarta());
		}
	}
	
	// 1er vez q comprueba si hay blackjack
	public void ComprobarBlackjack(){
		if (dealer.EsBlackjack() ) {
			System.out.println("El dealer obtuvo un Blackjack!");
			for (int i=0; i<usuarios; i++) {
				if (jugadores[i].getTotal() == 21 ) {
					System.out.println(jugadores[i].getNombre() + " empata");
					jugadores[i].empate();
				} else {
					System.out.println(jugadores[i].getNombre() + " pierde");
					jugadores[i].perdida();
				}	
			}
		} else {
			if (dealer.peek()) {
				System.out.println("Dealer agarra carta y no tiene BlackJack");
			}

			for (int i=0; i<usuarios; i++) {
				if (jugadores[i].getTotal() == 21 ) {
					System.out.println(jugadores[i].getNombre() + " obtuvo blackjack!");
					jugadores[i].blackjack();
				}
			}
		}		
	}
	
	// Codigo donde el usuario pide o se queda
	public void PedirQuedarse() {
		String comando;
		char c;
		for (int i = 0; i < usuarios; i++) {
			if ( jugadores[i].getApuesta() > 0 ) {
				System.out.println();
				System.out.println(jugadores[i].getNombre() + " tiene " + jugadores[i].getManoString());

				do {
					do {
						System.out.print(jugadores[i].getNombre() + " --> (P)edir o (Q)uedarse ");
						comando = sc.next();
						c = comando.toUpperCase().charAt(0);
					} while ( ! ( c == 'P' || c == 'Q' ) );
					if ( c == 'P' ) {
						jugadores[i].agregaCarta(mazo.nextCarta());
						System.out.println(jugadores[i].getNombre() + " tiene " + jugadores[i].getManoString());
					}
				} while (c != 'Q' && jugadores[i].getTotal() <= 21 );
			}
		}
	}
	
	// Codigo del dealer para jugar
	public void dealerjuega() {
		boolean jugadorjugando = false;
		for (int i=0; i<usuarios && jugadorjugando == false; i++){
			if (jugadores[i].getApuesta() > 0 && jugadores[i].getTotal() <= 21 ) {
				jugadorjugando = true;
			}
		}
		if (jugadorjugando){
			dealer.JugadaDealer(mazo);
		}
	}
	
	// Calcula todas las posibilidades de jugada y agrega o quita las apuesta de los jugadores.
	public void AceptaApuesta() {
		System.out.println();
		for (int i = 0; i < usuarios; i++) {
			if (jugadores[i].getApuesta() > 0 ) {
				if( jugadores[i].getTotal() > 21 ) {
					System.out.println(jugadores[i].getNombre() + " se paso");
					jugadores[i].perdida();
				} else if ( jugadores[i].getTotal() == dealer.calculaTotal() ) {
					System.out.println(jugadores[i].getNombre() + " han empatado");
					jugadores[i].empate();
				} else if ( jugadores[i].getTotal() < dealer.calculaTotal() && dealer.calculaTotal() <= 21 ) {
					System.out.println(jugadores[i].getNombre() + " ha perdido");
					jugadores[i].perdida();
				} else if (jugadores[i].getTotal() == 21) {
					System.out.println(jugadores[i].getNombre() + " gano con blackjack!");
					jugadores[i].blackjack();
				} else {
					System.out.println(jugadores[i].getNombre() + " gano");
					jugadores[i].gano();
				}
			}
		}
	}

	// Imprime las cartas del jugador
	public void imprimeestado() {
		for (int i = 0; i < usuarios; i++) {
			if(jugadores[i].getBanca() > 0)
			{
			System.out.println(jugadores[i].getNombre() + " tiene " + jugadores[i].getManoString());
			}
		}
		System.out.println("El dealer tiene " + dealer.getManoString(true, true));
	}
	
	// Muestra el dinero de los jugadores y dice si estan fuera de juego o no
	public void ImprimeDinero() {
		for (int i = 0; i < usuarios; i++) {
			if(jugadores[i].getBanca() > 0)
			{
			System.out.println(jugadores[i].getNombre() + " tiene " + jugadores[i].getBanca());
			}
			if(jugadores[i].getBanca() == 0)
			{
			System.out.println(jugadores[i].getNombre() + " tiene " + jugadores[i].getBanca() + " y esta fuera del juego.");
			jugadores[i].SacadelJuego();
			}
		}
	}

	// Reinicia todas las manos
	public void ReiniciaMano() {
		for (int i = 0; i < usuarios; i++) {
			jugadores[i].limpiamano();
		}
		dealer.limpiaMano();
	}
	
	// Decide si termina el juego o se sigue jugando
	public boolean Jugardenuevo() {
		String comando;
		char c;
		Boolean EstJugar = true;
		if(TerminarJuego()) {
			EstJugar = false;	
		}
		else {
			do {
				System.out.print("\nQueres jugar de nuevo (S)i o (N)o ? ");
				comando = sc.next();
				c = comando.toUpperCase().charAt(0);
			} while ( ! ( c == 'S' || c == 'N' ) );
			if(c == 'N')
			{
				EstJugar = false;
			}
		}
		return EstJugar;
	}
	
	// Dice si o no de terminar el juego
	public boolean TerminarJuego() {
		boolean end = false;
		int endCont = 0;
		
		for (int i = 0; i < usuarios; i++) {
			if(jugadores[i].getBanca() == -1){
				endCont++;
			}
		}
		if(endCont == usuarios){
			end = true;
		}
		if(end){
			System.out.println("\nTodos los jugadores terminaron de jugar.");
		}
		return end;
	}
	
	// Codigo final si los jugadores pierden o deciden parar de jugar.
		public void TerminaJuego() {
			int MontoFinal;
			String endEstado = " no cambio.";
			System.out.println("");
			for (int i = 0; i < usuarios; i++) {
				if(jugadores[i].getBanca() == -1) {
					jugadores[i].reiniciaBanca();
				}
				MontoFinal = jugadores[i].getBanca() - 100;
				if(MontoFinal > 0) {
					endEstado = " ganado ";
				}
				else if(MontoFinal < 0) {
					endEstado = " perdido ";
				}
				System.out.println(jugadores[i].getNombre() + " ha terminado el juego con " + jugadores[i].getBanca() + ".");
				if(endEstado != " no cambio.") {
				System.out.println("Ha" + endEstado + Math.abs(MontoFinal) + " en total.");
				}
				else {
				System.out.println("No hay cambios con el valor inicial.");	
				}
				System.out.println("");
			}
			System.out.println("¡Gracias por jugar!");
		}
}