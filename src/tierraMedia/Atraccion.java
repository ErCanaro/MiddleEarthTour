package tierraMedia;

public class Atraccion extends Producto {
	
	private int cupo;
	
	public Atraccion(String nombre, TipoAtraccion tipo, double costo, double duracion, int cupo) {
	super(nombre, tipo, costo, duracion);
	this.cupo = cupo;
	this.atraccionesIncluidas.add(this);//a revisar!
	}

	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public void restarCupo() {
		this.cupo -=1;
	}

}
