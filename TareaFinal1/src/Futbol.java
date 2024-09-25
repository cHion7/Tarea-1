import java.util.Random;
import java.util.Scanner;

public class Futbol {
	public static void main(String[] args) {
		Scanner S = new Scanner(System.in);
		Random R = new Random();
		boolean acceso = false;

		do {
			System.out.println("Ingrese su nombre, apellido y correo electrónico:");
			String input = S.nextLine();
			String[] datos = input.split("[\\s\\.]");

			if (datos.length >= 4) {
				int longitud = datos[2].length() + datos[3].length();

				if (datos[2].contains("@") && datos[3].equals("com") && longitud >= 10) {
					if (datos[0].equalsIgnoreCase("rayo") && datos[1].equalsIgnoreCase("veloz")) {
						acceso = true;
						System.out.println("¡Bienvenido Rayo Veloz!");
					} else {
						System.out.println("Acceso denegado.jNo eres Rayo Veloz.");
					}
				} else {
					System.out.println("Vuelve a introducir tus datos correctamente.");
				}
			} else {
				System.out.println("Vuelve a introducir tus datos correctamente.");
			}
		} while (!acceso);

		if (acceso == true) {
			int[][] matriz = new int[5][6]; // Ajustado para contener solo 6 estadísticas por jugador
			int count = 0;
			System.out.println("Dime los jugadores que usted quiere ver sus estadísticas");
			String input = S.nextLine();
			String[] jugadores = input.split(" ");

			// Generar estadísticas aleatorias para cada jugador
			for (int i = 0; i < matriz.length; i++) {
				for (int j = 0; j < matriz[i].length; j++) {
					matriz[i][j] = R.nextInt(11);
				}
			}

			// Imprimir encabezados
			System.out.println("Jugador\tNumJugador\tGoles\tAsistencias\tTarjetasA\tTarjetasR\tJugadasDef");

			// Mostrar las estadísticas por jugador
			for (int i = 0; i < jugadores.length; i++) {
				System.out.printf("%-8s\t%-12d", jugadores[i], (i + 1));

				// Imprimir las 6 estadísticas
				for (int j = 0; j < 6; j++) {
					System.out.printf("%-10d", matriz[i][j]);
				}
				System.out.println();
			}

			System.out.println("Goles totales: " + golestotal(matriz));
			System.out.println(asistotal(matriz));

		}
	}

	private static String asistotal(int[][] matriz) {
		int columnaAsistencias = 3; // El índice de columna para "Asistencias"
		int maxAsistencias = matriz[0][columnaAsistencias];
		int jugadorMaxAsistencias = 1;

		// Encontrar el jugador con más asistencias
		for (int i = 1; i < matriz.length; i++) {
			if (matriz[i][columnaAsistencias] > maxAsistencias) {
				maxAsistencias = matriz[i][columnaAsistencias];
				jugadorMaxAsistencias = i + 1; // Sumar 1 porque los índices comienzan desde 0
			}
		}

		// Devolver la información como un String
		return "\nJugador con más asistencias:\n" + "Jugador " + jugadorMaxAsistencias + ": " + maxAsistencias
				+ " asistencias";
	}

	private static int golestotal(int[][] matriz) {
		int columnaGoles = 2; // El índice de columna para "Goles"
		int totalGoles = 0;

		// Calcular la cantidad total de goles
		for (int i = 0; i < matriz.length; i++) {
			totalGoles += matriz[i][columnaGoles];
		}

		return totalGoles;
	}

}
