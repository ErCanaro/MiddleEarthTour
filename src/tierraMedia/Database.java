package tierraMedia;

import java.util.ArrayList;

public class Database {
	
//	static ArrayList<Atraccion> atraccionesDB= new ArrayList<Atraccion>();
//	static ArrayList<Producto> promocionesDB= new ArrayList<Producto>();
//	static ArrayList<Usuario> usuariosDB= new ArrayList<Usuario>();

	
	public ArrayList<Atraccion>  cargarAtraccionesDB() {
		ArrayList<Atraccion> atracciones = new ArrayList<>();
		
		Atraccion moria = new Atraccion("Moria", TipoAtraccion.AVENTURA, 6.0, 2.0, 10);
		Atraccion mordor = new Atraccion("Mordor", TipoAtraccion.AVENTURA, 25.0, 3.0, 4);
		Atraccion bosque = new Atraccion("Bosque Negro", TipoAtraccion.AVENTURA, 3.0, 4.0, 12);
		
		Atraccion avenCara = new Atraccion("Aventura mas Cara", TipoAtraccion.AVENTURA, 37.0, 2.0, 12);
		
		Atraccion tirith = new Atraccion("Minas Tirith", TipoAtraccion.PAISAJES, 5.0, 2.5, 25);
		Atraccion helm = new Atraccion("Abismo de Helm", TipoAtraccion.PAISAJES, 5.0, 2.0, 15);
		Atraccion erebor = new Atraccion("Erebor", TipoAtraccion.PAISAJES, 12.0, 3.0, 32);
		
		Atraccion paisaCara = new Atraccion("Paisaje Mas Cara", TipoAtraccion.PAISAJES, 29.0, 3.0, 32);
		
		Atraccion comarca = new Atraccion("La Comarca", TipoAtraccion.DEGUSTACION, 3.0, 6.5, 150);
		Atraccion lothlorien = new Atraccion("Lothlorien", TipoAtraccion.DEGUSTACION, 35.0, 1, 30);
		
		Atraccion degusCara = new Atraccion("Degustacion mas Cara", TipoAtraccion.DEGUSTACION, 51.0, 1.5, 150);
		
		
		atracciones.add(moria);
		atracciones.add(mordor);
		atracciones.add(bosque);
		atracciones.add(avenCara);
		atracciones.add(tirith);
		atracciones.add(helm);
		atracciones.add(erebor);
		atracciones.add(paisaCara);
		atracciones.add(comarca);
		atracciones.add(lothlorien);
		atracciones.add(degusCara);
		
		
		return atracciones;
	}
	
	public ArrayList<Producto>  cargarPromoionesDB() {
		ArrayList<Atraccion> atraccionesDB = this.cargarAtraccionesDB();
		
		ArrayList<Producto> promocionesDB = new ArrayList<>();
		
		PromocionAxB p1 = new PromocionAxB("Aventura 3x2", TipoAtraccion.AVENTURA, atraccionesDB.get(0), atraccionesDB.get(2), atraccionesDB.get(1));
		PromocionAxB p2 = new PromocionAxB("Paisajes 3x2", TipoAtraccion.PAISAJES, atraccionesDB.get(6), atraccionesDB.get(4), atraccionesDB.get(5));
		PromocionAxB p3 = new PromocionAxB("Degustacion 3x2", TipoAtraccion.DEGUSTACION, atraccionesDB.get(10), atraccionesDB.get(8), atraccionesDB.get(9));
 
		PromoPorcentual p4 = new PromoPorcentual("-20% en Aventura", TipoAtraccion.AVENTURA, atraccionesDB.get(3), atraccionesDB.get(0), 20.0);
		PromoPorcentual p5 = new PromoPorcentual("-25% en Paisajes", TipoAtraccion.PAISAJES, atraccionesDB.get(5), atraccionesDB.get(6), 25.0);
		PromoPorcentual p6 = new PromoPorcentual("-15% en Degustacion", TipoAtraccion.DEGUSTACION, atraccionesDB.get(9), atraccionesDB.get(8), 15.0);
		
		PromocionAbsoluta p7 = new PromocionAbsoluta("-5 Monedas en Aventura", TipoAtraccion.AVENTURA, atraccionesDB.get(3), atraccionesDB.get(0), 38.0);
		PromocionAbsoluta p8 = new PromocionAbsoluta("-3 Monedas en Paisajes", TipoAtraccion.PAISAJES, atraccionesDB.get(5), atraccionesDB.get(6), 6.0);
		PromocionAbsoluta p9 = new PromocionAbsoluta("-4 Monedas en Degustacion", TipoAtraccion.DEGUSTACION, atraccionesDB.get(9), atraccionesDB.get(8), 35.0);
		
		
		promocionesDB.add(p1);
		promocionesDB.add(p2);
		promocionesDB.add(p3);
		promocionesDB.add(p4);
		promocionesDB.add(p5);
		promocionesDB.add(p6);
		promocionesDB.add(p7);
		promocionesDB.add(p8);
		promocionesDB.add(p9);
			
		return promocionesDB;
	}
	
	public ArrayList<Usuario> cargarUsuariosDB() {

		ArrayList<Usuario> usuariosDB = new ArrayList<>();
		Usuario eowyn = new Usuario("Eowyn", TipoAtraccion.AVENTURA, 10.0, 8.0);
		Usuario gandalf = new Usuario("Gandalf", TipoAtraccion.PAISAJES, 30.0, 30.0);
		Usuario sam = new Usuario("Sam", TipoAtraccion.DEGUSTACION, 36.0, 8);
		Usuario galadriel = new Usuario("Galadriel", TipoAtraccion.PAISAJES, 10.0, 8.0);
		Usuario jose = new Usuario("Jose", TipoAtraccion.AVENTURA, 40.0, 10.0);
		Usuario maria = new Usuario("Maria", TipoAtraccion.PAISAJES, 25.0, 10.0);
		Usuario marta = new Usuario("Marta", TipoAtraccion.DEGUSTACION, 80, 22.0);

		usuariosDB.add(eowyn);
		usuariosDB.add(gandalf);
		usuariosDB.add(galadriel);
		usuariosDB.add(sam);
		usuariosDB.add(jose);
		usuariosDB.add(maria);
		usuariosDB.add(marta);

		return usuariosDB;
	}
	
	
}
