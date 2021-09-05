package tierraMedia;

import java.util.ArrayList;

public class Usuario {

	private String nombre;
	private TipoAtraccion preferencia;
	private double presupuesto;
	private double tiempoDisponible;
	protected ArrayList<Atraccion> historialDeAtracciones = new ArrayList<Atraccion>();
	private ArrayList<Producto> sugerenciasDiariasAceptadas = new ArrayList<Producto>();
	private ArrayList<Producto> itinerario = new ArrayList<Producto>();

	public Usuario(String nombre, TipoAtraccion preferencia, double presupuesto, double tiempoDisponible) {
		this.nombre = nombre;
		this.preferencia = preferencia;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
	}

	public void aceptarSugerencia(Producto sugerencia) {

		if (this.presupuesto >= sugerencia.getCosto() && this.tiempoDisponible >= sugerencia.getDuracion()
				&& sugerencia.getCupo() > 0 && !this.historialDeAtracciones.contains(sugerencia)) {
			this.sugerenciasDiariasAceptadas.add(sugerencia);
			this.historialDeAtracciones.addAll(sugerencia.getAtraccionesIncluidas());

			this.tiempoDisponible -= sugerencia.getDuracion();
			this.presupuesto -= sugerencia.getCosto();
			for (Atraccion atr : sugerencia.getAtraccionesIncluidas()) {
				atr.restarCupo();
			}
		}

	}

	// revisar
	public void crearItinerario() {
		double costoTotal = 0;
		double duracionTotal = 0;
		for (Producto prod : this.sugerenciasDiariasAceptadas) {
			costoTotal += prod.getCosto();
			duracionTotal += prod.getDuracion();
		}

		this.itinerario = this.sugerenciasDiariasAceptadas;
	}

	public String itinerarioToString() {
		double costoTotal = 0;
		double duracionTotal = 0;
		for (Producto prod : this.sugerenciasDiariasAceptadas) {
			costoTotal += prod.getCosto();
			duracionTotal += prod.getDuracion();
		}

		return "\n---------------------- Su Itinerario ------------------------------\n"
				+ "-------------------------------------------------------------------\n" + "Nombre: "
				+ this.getNombre() + "\nPreferencias " + this.getPreferencia() + "\n"
				+ " --------------------** Productos Aquiridos ** -------------------- \n"
				+ this.sugerenciasDiariasAceptadas + "\n"
				+ "------------------------ ***************** -------------------------\n"
				+ "--------------------------------------------------------------------\n" + "COSTO TOTAL= "
				+ costoTotal + "  ----- Tiempo Estimado=  " + duracionTotal
				+ "\n--------------------------------------------------------------------"
				+ "\n GRACIAS POR SU VISITA Y POR DEJARNOS SU DINERO!"
				+ "----------------------------------------------------------------------\n";

	}

	public void generarItinerario() {
		this.itinerario = this.sugerenciasDiariasAceptadas;
	}

	public String imprimirItinerario() {
		return "Diseñar Forma del ticke con datos de Usuario y Productos Aquiridos";
	}

	public ArrayList<Atraccion> getHistorialDeAtracciones() {
		return historialDeAtracciones;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoAtraccion getPreferencia() {
		return preferencia;
	}

	public void setPreferencia(TipoAtraccion preferencia) {
		this.preferencia = preferencia;
	}

	public double getPresupuesto() {
		return presupuesto;
	}

	public void setPresupuesto(double presupuesto) {
		this.presupuesto = presupuesto;
	}

	public double getTiempoDisponible() {
		return tiempoDisponible;
	}

	public void setTiempoDisponible(double tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public ArrayList<Atraccion> getHistorialDeProductos() {
		return historialDeAtracciones;
	}

	public ArrayList<Producto> getSugerenciasDiariasAceptadas() {
		return sugerenciasDiariasAceptadas;
	}

	public ArrayList<Producto> getItinerario() {
		return itinerario;
	}

	public void setItinerario(ArrayList<Producto> itinerario) {
		this.itinerario = itinerario;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Preferencia: " + preferencia + ", Presupuesto: " + presupuesto
				+ ", Tiempo Disponible: " + tiempoDisponible;
	}

	public void toString2() {
		System.out.printf("%-15s%-15s%-10d%-10d", nombre, preferencia, presupuesto, tiempoDisponible);
	}
	
}
