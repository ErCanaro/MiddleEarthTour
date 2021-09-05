package tierraMedia;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Producto {
	
	private static int contador = 0;
	private int ID=0;
	private String nombre;
	private TipoAtraccion tipo;
	private double costo;
	private double duracion;
	private int cupo;// revisar
	protected boolean isPromo; //renombrar
	protected ArrayList<Atraccion> atraccionesIncluidas = new ArrayList<Atraccion>();

	public Producto(String nombre, TipoAtraccion tipo, double costo, double duracion) {
		this.ID= contador;
		this.contador++;
		this.nombre = nombre; 
		this.tipo = tipo;
		this.costo =  costo;
		this.duracion = duracion;
//		this.cupo = cupo;
	}

	public Producto(String nombre, TipoAtraccion tipo, int cupo) {
		this.ID= contador;
		this.contador++;
		this.nombre = nombre;
		this.tipo = tipo;
//		this.cupo = cupo;
	}
	
	public int getID() {
		return ID;
	}

	public Producto(String nombre, TipoAtraccion tipo, double costo) {
		this.ID= contador;
		this.contador++;
		this.costo = costo;
		this.nombre = nombre;
		this.tipo = tipo;
	}
	
		
	public ArrayList<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoAtraccion getTipo() {
		return tipo;
	}

	public void setTipo(TipoAtraccion tipo) {
		this.tipo = tipo;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public abstract int getCupo();

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public boolean isPromo() {
		return isPromo;
	}

	public void setPromo(boolean isPromo) {
		this.isPromo = isPromo;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", Tipo: " + tipo + ", Costo: " + costo + ", Duracion: " + duracion;
		
	}

	public void toStringSinTitulos() {
		System.out.printf("%-15s%-15s%-15d%-15d", this.nombre, this.tipo, this.costo, this.duracion);
		
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(costo, nombre, tipo);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Double.doubleToLongBits(costo) == Double.doubleToLongBits(other.costo)
				&& Objects.equals(nombre, other.nombre) && tipo == other.tipo;
	}

	public static void main(String[] args) {
		Atraccion at1 = new Atraccion("Prueba 1", TipoAtraccion.AVENTURA, 10, 1, 25);
		System.out.println(at1);
	}
		
	
}
