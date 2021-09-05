package tierraMedia;

import static org.junit.Assert.*;


import org.junit.Test;

public class UsuarioTest {

	@Test
	public void notNull() {
		Usuario user = new Usuario ("Elewen", TipoAtraccion.AVENTURA, 50, 8);
		assertNotNull(user);
	}

	@Test
	public void queAlAceptarUnaSugerenciaLasAtraccionesIncluidasSeGuardenEnElHistorial() {
		Usuario user = new Usuario ("Elewen", TipoAtraccion.AVENTURA, 50, 8);
		Atraccion at1 = new Atraccion("Prueba 1", TipoAtraccion.AVENTURA, 10, 1, 25);
		Atraccion at2 = new Atraccion("Prueba 1", TipoAtraccion.AVENTURA, 10, 2, 15);
		Atraccion at3 = new Atraccion("Prueba 5", TipoAtraccion.AVENTURA, 10, 3, 35);
		
		PromocionAxB promoAxB = new PromocionAxB("Hora de Aventura", TipoAtraccion.AVENTURA, at1, at2, at3);
		
		user.aceptarSugerencia(promoAxB);
		assertTrue(user.getHistorialDeAtracciones().contains(at1));
		assertTrue(user.getHistorialDeAtracciones().contains(at2));
		assertTrue(user.getHistorialDeAtracciones().contains(at3));
	}
	
	@Test
	public void queAlAceptarPromocionAxBEstaSeGuardeEnLasSugerenciasDiarias() {
		Usuario user = new Usuario ("Elewen", TipoAtraccion.AVENTURA, 50, 8);
		Atraccion at1 = new Atraccion("Prueba 1", TipoAtraccion.AVENTURA, 10, 1, 25);
		Atraccion at2 = new Atraccion("Prueba 1", TipoAtraccion.AVENTURA, 10, 2, 15);
		Atraccion at3 = new Atraccion("Prueba 5", TipoAtraccion.AVENTURA, 10, 3, 35);
		
		PromocionAxB promoAxB = new PromocionAxB("Hora de Aventura", TipoAtraccion.AVENTURA, at1, at2, at3);
		
		user.aceptarSugerencia(promoAxB);
		
		assertTrue(user.getSugerenciasDiariasAceptadas().contains(promoAxB));
	}
	
	@Test
	public void queAlAceptarUnaSugerenciaQueCuesta22QuedenEnElPresupuesto28() {
		Usuario user = new Usuario ("Elewen", TipoAtraccion.AVENTURA, 50, 8);
		Atraccion at1 = new Atraccion("Prueba 1", TipoAtraccion.AVENTURA, 10, 1, 25);
		Atraccion at2 = new Atraccion("Prueba 1", TipoAtraccion.AVENTURA, 12, 2, 15);
		Atraccion at3 = new Atraccion("Prueba 5", TipoAtraccion.AVENTURA, 15, 3, 35);
		
		PromocionAxB promoAxB = new PromocionAxB("Hora de Aventura", TipoAtraccion.AVENTURA, at1, at2, at3);
		
		user.aceptarSugerencia(promoAxB);
		assertEquals(28, user.getPresupuesto(), 0);
		
		user.aceptarSugerencia(at3);//modificar**
		assertEquals(28, user.getPresupuesto(), 0);
	}
	
	@Test
	public void queSiTieneTiempoYNoTieneDineroNoPuedeComprarProductos() {
		Usuario user = new Usuario ("Elewen", TipoAtraccion.AVENTURA, 50, 12);
		Atraccion at1 = new Atraccion("Tested 1", TipoAtraccion.AVENTURA, 10, 1, 25);
		Atraccion at2 = new Atraccion("No Compila 1", TipoAtraccion.AVENTURA, 12, 2, 15);
		Atraccion at3 = new Atraccion("Prueba 5", TipoAtraccion.AVENTURA, 10, 3, 35);
		Atraccion at4 = new Atraccion("No se que poner", TipoAtraccion.DEGUSTACION, 33, 3, 35);
		
		PromocionAxB promoAxB = new PromocionAxB("Hora de Aventura", TipoAtraccion.AVENTURA, at1, at2, at3);
		user.aceptarSugerencia(promoAxB);
		assertEquals(28, user.getPresupuesto(), 0);
		
		user.aceptarSugerencia(at4);
		assertEquals(28, user.getPresupuesto(), 0);
		assertFalse(user.getHistorialDeAtracciones().contains(at4));
	}
	
	@Test
	public void queSiTieneDineroYNoTieneTiempoNoPuedeComprarProductos() {
		//Tiempo Disponible = 8  --  Tiempo Necesario = 9
		Usuario user = new Usuario ("Elewen", TipoAtraccion.AVENTURA, 50, 8);
		Atraccion at1 = new Atraccion("Tested 1", TipoAtraccion.AVENTURA, 10, 1, 25);
		Atraccion at2 = new Atraccion("No Compila 1", TipoAtraccion.AVENTURA, 12, 2, 15);
		Atraccion at3 = new Atraccion("Prueba 5", TipoAtraccion.AVENTURA, 10, 3, 35);
		Atraccion at4 = new Atraccion("No se que poner", TipoAtraccion.DEGUSTACION, 13, 3, 35);
				
		PromocionAxB promoAxB = new PromocionAxB("Hora de Aventura", TipoAtraccion.AVENTURA, at1, at2, at3);
		
		//Si puede comprar
		user.aceptarSugerencia(promoAxB);
		assertEquals(28, user.getPresupuesto(), 0);
		
		
		//ya no puede comprar
		user.aceptarSugerencia(at4);
		assertEquals(28, user.getPresupuesto(), 0);
	}
	
	@Test
	public void queAlAceptarUnaPromocionSeResteCupoDeLaMismaYDeSusAtraccionesIncluidas() {
		
		Usuario user = new Usuario ("Elewen", TipoAtraccion.AVENTURA, 50, 8);
		Atraccion at1 = new Atraccion("Tested 1", TipoAtraccion.AVENTURA, 10, 1, 5);
		Atraccion at2 = new Atraccion("No Compila 1", TipoAtraccion.AVENTURA, 12, 2, 7);
		Atraccion at3 = new Atraccion("Prueba 5", TipoAtraccion.AVENTURA, 10, 3, 4);
		Atraccion at4 = new Atraccion("No se que poner", TipoAtraccion.DEGUSTACION, 13, 3, 35);
		
		//El cupo de una promo es igual al cupo de la atraccion con menor cupo.
		PromocionAxB promoAxB = new PromocionAxB("Hora de Aventura", TipoAtraccion.AVENTURA, at1, at2, at3);
		
		
		user.aceptarSugerencia(promoAxB);
		assertEquals(28, user.getPresupuesto(), 0);
		assertEquals(3, promoAxB.getAtraccionesIncluidas().get(2).getCupo());
		assertEquals(at3.getCupo(), 3);
		assertEquals(at1.getCupo(), 4);
		assertEquals(at2.getCupo(), 6);
	}
	
	@Test
	public void queNoPuedaAceptarNingunProductoQueContengaUnaAtraccionAntesAdquirida() {
		//Tiempo Disponible = 12  --  Tiempo Necesario = 9
		Usuario user = new Usuario ("Elewen", TipoAtraccion.AVENTURA, 50, 12);
		Atraccion at1 = new Atraccion("Tested 1", TipoAtraccion.AVENTURA, 10, 1, 25);
		Atraccion at2 = new Atraccion("No Compila 1", TipoAtraccion.AVENTURA, 12, 2, 15);
		Atraccion at3 = new Atraccion("Prueba 5", TipoAtraccion.AVENTURA, 10, 3, 35);
		
		PromocionAxB promoAxB = new PromocionAxB("Hora de Aventura", TipoAtraccion.AVENTURA, at1, at2, at3);
		
		//puede comprar porque es la primer compra.
		user.aceptarSugerencia(promoAxB);
		assertEquals(28, user.getPresupuesto(), 0);
		assertTrue(user.getHistorialDeAtracciones().contains(at3));
		
		//No puede comprar porque la atraccion fue adquirida dentro de promoAxB.
		user.aceptarSugerencia(at3);
		assertEquals(28, user.getPresupuesto(), 0);
		assertTrue(user.getHistorialDeAtracciones().contains(at3));
	}
	
}
