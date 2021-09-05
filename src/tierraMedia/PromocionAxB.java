package tierraMedia;

import java.util.ArrayList;

public class PromocionAxB extends Producto {

	public PromocionAxB(String nombre, TipoAtraccion tipo, Atraccion a1, Atraccion a2, Atraccion a3) {
		super(nombre, tipo, 0);
		setearAtributos(a1, a2, a3);
	}

	public void setearAtributos(Atraccion a1, Atraccion a2, Atraccion a3) {
		this.setCosto(a1.getCosto() + a2.getCosto());
		this.setDuracion(a1.getDuracion() + a2.getDuracion() + a3.getDuracion());
		super.setPromo(true);
		if (verificarTipoDeAtraccion(a1, a2, a3)) {
			this.atraccionesIncluidas.add(a1);
			this.atraccionesIncluidas.add(a2);
			this.atraccionesIncluidas.add(a3);
		} else {
			throw new Error("Al menos una atracción no coincide con el tipo de Promocion");
		}

	}

	@Override
	public int getCupo() {
		if (this.getAtraccionesIncluidas().get(0).getCupo() > 0 && this.getAtraccionesIncluidas().get(1).getCupo() > 0
				&& this.getAtraccionesIncluidas().get(2).getCupo() > 0)
			return 1;
		return 0;
	}

	public boolean verificarTipoDeAtraccion(Atraccion a1, Atraccion a2, Atraccion a3) {
		return this.getTipo() == a1.getTipo() && this.getTipo() == a2.getTipo() && this.getTipo() == a3.getTipo();
	}

	@Override
	public ArrayList<Atraccion> getAtraccionesIncluidas() {
		return atraccionesIncluidas;
	}

	@Override
	public String toString() {
		return "[Promocion: " + super.getTipo() + "]" + "'" + super.getNombre() + "', Costo: " + super.getCosto()
				+ " Duracion: " + super.getDuracion() + ".\n" + "Atracciones Incluidas: ["
				+ atraccionesIncluidas.get(0).getNombre() + ", " + atraccionesIncluidas.get(1).getNombre() + ", "
				+ atraccionesIncluidas.get(2).getNombre() + "]";
	}

	public static void main(String[] args) {
		Atraccion at1 = new Atraccion("Prueba 1", TipoAtraccion.AVENTURA, 10, 1, 25);
		Atraccion at2 = new Atraccion("Prueba 2", TipoAtraccion.AVENTURA, 12, 2, 15);
		Atraccion at3 = new Atraccion("Prueba 5", TipoAtraccion.AVENTURA, 15, 3, 35);

		PromocionAxB promoAxB = new PromocionAxB("Hora de Aventura", TipoAtraccion.AVENTURA, at1, at2, at3);

		System.out.println(promoAxB.getAtraccionesIncluidas());
		System.out.println(promoAxB);

	}

}
