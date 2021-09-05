package tierraMedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TierraMedia {

	static ArrayList<Atraccion> atraccionesDB = new ArrayList<Atraccion>();
	static ArrayList<Producto> promocionesDB = new ArrayList<Producto>();
	static ArrayList<Producto> productosDB = new ArrayList<Producto>();
	static ArrayList<Usuario> usuariosDB = new ArrayList<Usuario>();
	static Scanner sc = new Scanner(System.in);

	public static ArrayList<Producto> ofrecerSugerencia(Usuario usuario) {
		ArrayList<Producto> sugerencia = new ArrayList<Producto>();
		ArrayList<Producto> noPreferidas = new ArrayList<Producto>();

		for (Producto prod : productosDB) {
			int aux = 0;
			for (Atraccion atr : prod.getAtraccionesIncluidas()) {
				if (usuario.getHistorialDeAtracciones().contains(atr)) {
					aux++;
				}
			}
			if (prod.getCosto() <= usuario.getPresupuesto() && prod.getDuracion() <= usuario.getTiempoDisponible()
					&& prod.getCupo() > 0 && aux == 0) {
				if (prod.getTipo() == usuario.getPreferencia()) {
					sugerencia.add(prod);
				} else {
					noPreferidas.add(prod);
				}
			}
		}

		// Funciona! No tocar! xD
		Comparator<Producto> ordenarSugerencia = Comparator.comparing(Producto::isPromo)
				.thenComparing(Producto::getCosto).thenComparing(Producto::getDuracion)
				.thenComparing(Producto::getNombre);
		Collections.sort(sugerencia, ordenarSugerencia.reversed());
		Collections.sort(noPreferidas, ordenarSugerencia.reversed());

		sugerencia.addAll(noPreferidas);
		return sugerencia;
	}

	public static void crearNuevaAtraccion() {
		int opcionAtraccion = 0;
		TipoAtraccion tipoAtr = TipoAtraccion.AVENTURA;
		System.out.println("¿Cuál será el tipo de atracción?");
		System.out.println("1 - Aventura");
		System.out.println("2 - Degustación");
		System.out.println("3 - Paisajes");

		int flagAtracion = 0;
		do {
			System.out.println("Ingrese el número correspondiente a la opción");

			try {
				opcionAtraccion = sc.nextInt();
			} catch (InputMismatchException ime) {
				System.err.println("Obligatoriamente debe ingresar un número");
				sc.next();
				flagAtracion = 0;
			}

			if (opcionAtraccion >= 1 && opcionAtraccion <= 3) {
				flagAtracion = 1;
			} else {
				System.out.println("La opción escogida no es correcta");
			}

			if (opcionAtraccion == 1) {
				tipoAtr = TipoAtraccion.AVENTURA;
			} else if (opcionAtraccion == 2) {
				tipoAtr = TipoAtraccion.DEGUSTACION;
			} else if (opcionAtraccion == 3) {
				tipoAtr = TipoAtraccion.PAISAJES;
			}

		} while (flagAtracion != 1);

		String nombre = "";
		System.out.println("Ingrese el nombre para la nueva atracción");
		sc.nextLine();// revisar!!!
		nombre += sc.nextLine();
		System.out.println("Ingrese el costo para la nueva atracción");

		double costo = (double) sc.nextDouble();
		System.out.println("Ingrese la duración de la nueva atracción");
		double duracion = (double) sc.nextDouble();
		System.out.println("Ingrese el cupo máximo para la nueva atracción");
		int cupo = sc.nextInt();

		Atraccion atrac = new Atraccion(nombre, tipoAtr, costo, duracion, cupo);

		System.out.println("Se ha creado una nueva atraccion con los siguientes datos");
		System.out.println(atrac + "\n");
		System.out.println("Desea agregarla a la Base de Datos?\n" + "1-NO      2-SI");
		int agregar = sc.nextInt();
		if (agregar == 2) {
			atraccionesDB.add(atrac);
			System.out.println("La nueva atracción se ha agregado correctamente");
		}

	}

	public static void crearOferta() {
		System.out.println("Por favor seleccione un usario para hacerle una oferta: ");
		System.out.printf("%-6s%-15s%-15s%-15s%-10s", "Nro", "NOMBRE", "PREFERENCIA", "PRESUPUESTO",
				"TIEMPO DISPONIBLE\n");
		for (int i = 0; i < usuariosDB.size(); i++) {
			System.out.printf("%-6s%-15s%-15s%-15.0f%-10s%n", i + " - ", usuariosDB.get(i).getNombre(),
					usuariosDB.get(i).getPreferencia(), usuariosDB.get(i).getPresupuesto(),
					usuariosDB.get(i).getTiempoDisponible());
		}
		;
		System.out.print("\nIngrese el número correspondiente al usuario escogido: ");
		int usuario = sc.nextInt();
		Usuario usr = usuariosDB.get(usuario);

		do {
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("Por favor, " + usr.getNombre() + " escoja una oferta");
			System.out.println(
					"Aún dispone de " + usr.getPresupuesto() + "Monedas y de " + usr.getTiempoDisponible() + " horas.");
			System.out.println("-----------------------------------------------------------------------");
			for (int i = 0; i < ofrecerSugerencia(usr).size(); i++) {
				System.out.println(i + 1 + "- " + ofrecerSugerencia(usr).get(i));
				System.out.println("--");
			}
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("[0] Si no desea adquirir más ofertas ingrese 0(cero)");
			;

			int eleccion1 = sc.nextInt();

			if (eleccion1 == 0) {
				break;
			} else if (eleccion1 > 0 && eleccion1 <= ofrecerSugerencia(usr).size()) {
				usr.aceptarSugerencia(ofrecerSugerencia(usr).get(eleccion1 - 1));

			}

		} while (ofrecerSugerencia(usr).size() > 0);

		System.out.println(usr.itinerarioToString());
	}

	public static void crearPromocionAxB() {
		System.out.println("-----------------------------------------");
		System.out.println("HA ESCOGIDO CREAR UNA NUEVA PROMOCION AxB");
		System.out.println("Por favor escoja el tipo para la nueva Promoción");

		TipoAtraccion tipo = TipoAtraccion.AVENTURA;

		int eleccionTipo = 0;
		System.out.println("1 - Aventura");
		System.out.println("2 - Degustacion");
		System.out.println("3 - Paisajes");

		int tipoAux = 0;
		do {
			try {
				eleccionTipo = sc.nextInt();
			} catch (InputMismatchException ime) {
				System.err.println("Obligatoriamente debe ingresar un número");
				sc.next();
				tipoAux = 0;
			}

			if (eleccionTipo == 1) {
				tipo = TipoAtraccion.AVENTURA;
				tipoAux = 1;
			} else if (eleccionTipo == 2) {
				tipo = TipoAtraccion.DEGUSTACION;
				tipoAux = 1;
			} else if (eleccionTipo == 3) {
				tipo = TipoAtraccion.PAISAJES;
				tipoAux = 1;
			} else {
				System.out.println("La opción ingresada no existe. Ingresa nuevamente");
			}
		} while (tipoAux != 1);

		System.out.println("Por favor Ingrese un nombre para la nueva Promoción");
		String nombre;
		sc.nextLine();
		nombre = sc.nextLine();

		// Atraccion 1 ------------
		Atraccion a1 = null;

		ArrayList<Atraccion> atraccionesPorTipo = new ArrayList<Atraccion>();

		for (int i = 0; i < atraccionesDB.size(); i++) {
			if (atraccionesDB.get(i).getTipo() == tipo) {
				atraccionesPorTipo.add(atraccionesDB.get(i));
			}
		}

		for (int i = 0; i < atraccionesPorTipo.size(); i++) {
			System.out.println(
					i + 1 + "- " + atraccionesPorTipo.get(i).getNombre() + ", " + atraccionesPorTipo.get(i).getTipo());
		}

		System.out.println("Elija una atracción para la nueva Promocion");
		int eleccionA1 = 0;
		int eleccionFlag1 = 0;
		int eleccionFlag2 = 0;
		int eleccionFlag3 = 0;
		do {
			try {
				eleccionA1 = sc.nextInt();
			} catch (InputMismatchException ime) {
				System.err.println("Obligatoriamente debe ingresar un número");
				sc.next();
				eleccionA1 = 0;
			}

			if (eleccionA1 >= 1 && eleccionA1 <= atraccionesPorTipo.size()) {
				a1 = atraccionesPorTipo.get(eleccionA1 - 1);
				eleccionFlag1 = 1;
				atraccionesPorTipo.remove(eleccionA1 - 1);
			} else {
				System.out.println("La opcion ingresada no existe. Por favor ingrese nuevamente");
			}

		} while (eleccionFlag1 != 1);

		// Atraccion 2 ------------
		Atraccion a2 = null;

		for (int i = 0; i < atraccionesPorTipo.size(); i++) {
			System.out.println(
					i + 1 + "- " + atraccionesPorTipo.get(i).getNombre() + ", " + atraccionesPorTipo.get(i).getTipo());
		}

		System.out.println("Elija una atracción para la nueva Promocion");
		int eleccionA2 = 0;
		do {
			try {
				eleccionA2 = sc.nextInt();
			} catch (InputMismatchException ime) {
				System.err.println("Obligatoriamente debe ingresar un número");
				sc.next();
				eleccionA2 = 0;
			}

			if (eleccionA2 >= 1 && eleccionA2 <= atraccionesPorTipo.size()) {
				a2 = atraccionesPorTipo.get(eleccionA2 - 1);
				eleccionFlag2 = 1;
				atraccionesPorTipo.remove(eleccionA2 - 1);
			} else {
				System.out.println("La opcion ingresada no existe. Por favor ingrese nuevamente");
			}

		} while (eleccionFlag2 != 1);

		// Atraccion 3 ------------
		Atraccion a3 = null;

		for (int i = 0; i < atraccionesPorTipo.size(); i++) {
			System.out.println(
					i + 1 + "- " + atraccionesPorTipo.get(i).getNombre() + ", " + atraccionesPorTipo.get(i).getTipo());
		}

		System.out.println("Elija una atracción para la nueva Promocion");
		int eleccionA3 = 0;
		do {
			try {
				eleccionA3 = sc.nextInt();
			} catch (InputMismatchException ime) {
				System.err.println("Obligatoriamente debe ingresar un número");
				sc.next();
				eleccionA3 = 0;
			}

			if (eleccionA3 >= 1 && eleccionA3 <= atraccionesPorTipo.size()) {
				a3 = atraccionesPorTipo.get(eleccionA3 - 1);
				eleccionFlag3 = 1;
				atraccionesPorTipo.remove(eleccionA3 - 1);
			} else {
				System.out.println("La opcion ingresada no existe. Por favor ingrese nuevamente");
			}

		} while (eleccionFlag3 != 1);

		double costo = a1.getCosto() + a2.getCosto();
		double duracion = a1.getDuracion() + a2.getDuracion() + a3.getDuracion();

		PromocionAxB promoAB = new PromocionAxB(nombre, tipo, a1, a2, a3);

		System.out.println("Se ha creado la siguiente promocion");
		System.out.println(promoAB);
	}

	public static void main(String[] args) {
		Database db = new Database();

		atraccionesDB = db.cargarAtraccionesDB();
		promocionesDB = db.cargarPromoionesDB();
		usuariosDB = db.cargarUsuariosDB();

		productosDB.addAll(atraccionesDB);
		productosDB.addAll(promocionesDB);

		int opcion = 0;

		System.out.println("---------- BIENVENIDOS A TIERRA MEDIA -------------");
		System.out.println("---------------------------------------------------");
		do {
			System.out.println("Que acción desea realizar?\n");
			System.out.println("1 - Crear Nueva Atracción");
			System.out.println("2 - Crear Nueva Promocion AxB");
			System.out.println("3 - Crear Nueva Promoción Porcentual");
			System.out.println("4 - Crear Nueva Promocion Absoluta");
			System.out.println("5 - Crear Nuevo Usuario");
			System.out.println("6 - Ofrecer Sugerencias a Usuario");
			System.out.println("7 - Salir\n");

			int opcionFlag = 0;
			do {
				System.out.println("Por favor seleccione una opción");
				try {
					opcion = sc.nextInt();
				} catch (InputMismatchException ime) {
					System.err.println("Obligatoriamente debe ingresar un número");
					sc.next();
					opcionFlag = 0;
				}

				if (opcion >= 1 && opcion <= 7) {
					opcionFlag = 1;
				} else {
					System.out.println("La opcion ingresada no existe!!");
					opcionFlag = 0;
				}

			} while (opcionFlag != 1);

			switch (opcion) {
			case 1:
				System.out.println("-------------------------------------");
				System.out.println("HA ESCOGIDO CREA UNA NUEVA ATRACCIÓN");
				crearNuevaAtraccion();
				break;
			case 2:
				crearPromocionAxB();
//				System.out.println("NOT IMPLEMENTED YET");
				break;
			case 3:
				System.out.println("NOT IMPLEMENTED YET");
				break;
			case 4:
				System.out.println("NOT IMPLEMENTED YET");
				break;
			case 5:
				System.out.println("NOT IMPLEMENTED YET");
				break;
			case 6:
				crearOferta();
				break;
			case 7:
				System.exit(0);
				break;
			default:
				System.out.println("Opcion no disponible");
				break;
			}
		} while (opcion != 7);

	}
}
