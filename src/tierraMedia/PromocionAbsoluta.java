package tierraMedia;


public class PromocionAbsoluta extends Producto{
	
	
	public PromocionAbsoluta(String nombre, TipoAtraccion tipo, Atraccion a1, Atraccion a2, double costo) {
		super(nombre, tipo, costo);
		this.setCupo(Math.min(a1.getCupo(), a2.getCupo()));
		this.setDuracion(a1.getDuracion()+a2.getDuracion());
		this.atraccionesIncluidas.add(a1);
		this.atraccionesIncluidas.add(a2);
		super.setPromo(true); 
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
		return "[Promocion: "+ super.getTipo()+ "]"+"'"+super.getNombre() + "', Costo: " + super.getCosto() 
	           + ", Duracion: " + super.getDuracion() + "\n Atracciones Incluidas: [" + atraccionesIncluidas.get(0).getNombre() 
	           + ", "+ atraccionesIncluidas.get(1).getNombre();
	}

}
