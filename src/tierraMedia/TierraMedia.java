package tierraMedia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		System.out.println("3 - Paisajer");
		
		int flagAtracion = 0;
		do {
			System.out.println("Ingrese el número correspondiente a la opción");

			opcionAtraccion = sc.nextInt();
			
			if (opcionAtraccion <= 3 && opcionAtraccion >=1) {
				flagAtracion = 1;
			} else {System.out.println("La opción escogida no es correcta");}
			
			if (opcionAtraccion == 1) {
				tipoAtr = TipoAtraccion.AVENTURA;
			} else if (opcionAtraccion == 2) {
				tipoAtr = TipoAtraccion.DEGUSTACION;
			} else if (opcionAtraccion == 3) {
				tipoAtr = TipoAtraccion.PAISAJES;
			} 
			
		}while (flagAtracion != 1);
		
		String nombre ="";
		System.out.println("Ingrese el nombre para la nueva atracción");
		sc.nextLine();//revisar!!!
		nombre+= sc.nextLine();
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
		System.out.printf("%-6s%-15s%-15s%-15s%-10s","Nro", "NOMBRE", "PREFERENCIA", "PRESUPUESTO", "TIEMPO DISPONIBLE\n");
		for (int i = 0; i < usuariosDB.size(); i++) {
			System.out.printf("%-6s%-15s%-15s%-15.0f%-10s%n", i+" - ", usuariosDB.get(i).getNombre(), usuariosDB.get(i).getPreferencia(), usuariosDB.get(i).getPresupuesto(), usuariosDB.get(i).getTiempoDisponible());
		};
		System.out.print("\nIngrese el número correspondiente al usuario escogido: ");
		int usuario = sc.nextInt();
		Usuario usr = usuariosDB.get(usuario);
		
		do {
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("Por favor, " + usr.getNombre() + " escoja una oferta");
			System.out.println("Aún dispone de " + usr.getPresupuesto() + "Monedas y de " + usr.getTiempoDisponible() + " horas.");
			System.out.println("-----------------------------------------------------------------------");
			for (int i = 0; i < ofrecerSugerencia(usr).size(); i++) {
				System.out.println(i+1 +"- "+ ofrecerSugerencia(usr).get(i));
				System.out.println("--");
			}
			System.out.println("-----------------------------------------------------------------------");
			System.out.print("[0] Si no desea adquirir más ofertas ingrese 0(cero)");
			;
			
			int eleccion1 = sc.nextInt();
			
			if (eleccion1 == 0 ) {
				break;
			} else if (eleccion1 > 0 && eleccion1 <= ofrecerSugerencia(usr).size()){
				usr.aceptarSugerencia(ofrecerSugerencia(usr).get(eleccion1-1));
				
			}
			
		}while (ofrecerSugerencia(usr).size() > 0);
		
		System.out.println(usr.itinerarioToString());
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

			System.out.println("Por favor seleccione una opción");
			opcion = sc.nextInt();

			switch (opcion) {
			case 1:
				crearNuevaAtraccion();
				break;
			case 2:
				System.out.println("NOT IMPLEMENTED YET");
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
