package tierraMedia;

public class PromoPorcentual extends Producto {

	private double porcentajeDcto;

	
	public PromoPorcentual(String nombre, TipoAtraccion tipo, Atraccion a1, Atraccion a2, double porcentajeDcto) {
		super(nombre, tipo, 0);
		this.setCupo(Math.min(a1.getCupo(), a2.getCupo()));
		this.setCosto((a1.getCosto()+a2.getCosto())*(1-porcentajeDcto/100));
		this.setDuracion(a1.getDuracion()+a2.getDuracion());
		this.porcentajeDcto = porcentajeDcto;
		this.atraccionesIncluidas.add(a1);
		this.atraccionesIncluidas.add(a2);
		super.setPromo(true);
	}

	public double getPorcentajeDcto() {
		return porcentajeDcto;
	}

	public void setPorcentajeDcto(double porcentajeDcto) {
		this.porcentajeDcto = porcentajeDcto;
	}

	@Override
	public int getCupo() {
		if (this.getAtraccionesIncluidas().get(0).getCupo()> 0 
				&& this.getAtraccionesIncluidas().get(1).getCupo()> 0)
			return 1;			
		return 0;
	}

	@Override
	public String toString() {
		return "[Promocion: "+ super.getTipo()+ "]"+"'"+super.getNombre() + "', Costo: " + super.getCosto() +" Duracion: " + super.getDuracion()
	           +"\n Atracciones Incluidas: [" + atraccionesIncluidas.get(0).getNombre() 
	           + ", " + atraccionesIncluidas.get(1).getNombre();
	}

}
