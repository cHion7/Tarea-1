import java.util.Random;
import java.util.Scanner;

public class Main {
	Scanner sc = new Scanner(System.in);

	private String[] pedirDatos() {
		System.out.print("Escribe tus datos (nombre apellido email): ");
		String datos = sc.nextLine();
		return datos.split(" ");
	}

	public boolean autenticacion(String nombre, String Apellido, String email) {
		final String NOMBRE = "RAYO";
		final String APELLIDO = "Veloz";
		final String EMAIL = "rayoveloz@uem.com";

		boolean resultado = true;

		// Comprobación del email
		// 1 - Más de 10
		resultado &= email.length() > 10;
		// 2 - Finaliza en .com
		resultado &= email.endsWith(".com");
		// 3 - Tenga @
		resultado &= email.contains("@");

		// Comprobación de los datos
		resultado &= nombre.equals(NOMBRE);
		resultado &= Apellido.equals(APELLIDO);
		resultado &= email.equals(EMAIL);

		return resultado;
	}

	public String[] pedirJugadores() {
		System.out.print("¿Cuántos jugadores hay?");
		int cantidadJugadores = sc.nextInt();
		String[] respuesta = new String[cantidadJugadores];
		for (int i = 0; i < respuesta.length; i++) {
			System.out.print("Nombre del jugador " + (i + 1) + ": ");
			respuesta[i] = sc.next();
		}
		return respuesta;
	}

	public int[][] generarDatos(int cantidadJugadores) {
		int[][] respuesta = new int[cantidadJugadores][5];
		for (int i = 0; i < respuesta.length; i++) {
			for (int j = 0; j < respuesta[0].length; j++) {
				Random r = new Random();
				respuesta[i][j] = r.nextInt(11);
			}
		}
		return respuesta;
	}

	private void imprimeDatos(String[] jugador, int[][] datos) {
		String[] categorias = { "Nombre", "Goles", "Asist.", "T.Amar.", "T.Rojas", "J.Defensivas" };
		for (int i = 0; i < categorias.length; i++) {
			System.out.print(categorias[i] + "\t");
		}
		System.out.println();
		for (int i = 0; i < datos.length; i++) {
			System.out.print(jugador[i] + "\t");
			for (int j = 0; j < datos[i].length; j++) {
				System.out.print(datos[i][j] + "\t");
			}
			System.out.println();
		}

	}

	public int calculaGoles(int[][] datos) {
		int contador = 0;
		for (int i = 0; i < datos.length; i++) {
			contador += datos[i][0];
		}
		return contador;
	}

	public int maximoAsistencias(int[][] datos) {
		int maximo = datos[0][1];
		int dorsal = 0;
		for (int i = 0; i < datos.length; i++) {
			if (datos[i][1] > maximo) {
				maximo = datos[i][1];
				dorsal = i;
			}
		}
		return dorsal;
	}

	private int cardless(int[][] datos) {
		int minimo = datos[0][2] + datos[0][3];
		int dorsal = 0;
		for (int i = 0; i < datos.length; i++) {
			if (datos[i][2] + datos[i][3] < minimo) {
				minimo = datos[i][2] + datos[i][3];
				dorsal = i;
			}
		}
		return dorsal;
	}

	public int bds(int[][] datos) {
		// Jugadas defensivas * 5 - Tarjestas rojas * 2 + Tarjetajas amarilla
		int maximo = 5 * datos[0][4] - 2 * datos[0][3] - datos[0][2];
		int dorsal = 0;
		for (int i = 0; i < datos.length; i++) {
			if (5 * datos[i][4] - 2 * datos[i][3] - datos[i][2] > maximo) {
				maximo = 5 * datos[i][4] - 2 * datos[i][3] - datos[i][2];
				dorsal = i;
			}
		}
		return dorsal;
	}

	public static void main(String[] args) {
		Main programa = new Main();
		// 0 - Pedir datos
		boolean accesoCorrecto;
//		do {
//			String[] datosAcceso = programa.pedirDatos();
//			// 1 - Acceso
//			accesoCorrecto = programa.autenticacion(datosAcceso[0], datosAcceso[1], datosAcceso[2]);
//			if (!accesoCorrecto) {
//				System.out.println("\tDatos incorrectos, vuelve a intentarlo");
//			}
//		} while (!accesoCorrecto);

		// 2 - Si el acceso es correcto -> Mostrar datos
		String[] jugadores = programa.pedirJugadores();
		int[][] datos = programa.generarDatos(jugadores.length);
		programa.imprimeDatos(jugadores, datos);

		// 3 - Calcular estatidísticas
		int goles = programa.calculaGoles(datos);
		System.out.println("La cantidad de goles es: " + goles);
		int maxAsistencias = programa.maximoAsistencias(datos);
		System.out.println("El jugador con más asistencias es: " + jugadores[maxAsistencias]);
		int cardless = programa.cardless(datos);
		System.out.println("El jugador con menos tarjetas es: " + jugadores[cardless]);
		int bds = programa.bds(datos);
		System.out.println("El mejor defensa es: " + jugadores[bds]);
	}

}