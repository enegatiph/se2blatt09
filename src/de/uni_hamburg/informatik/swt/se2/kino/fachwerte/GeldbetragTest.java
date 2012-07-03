package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeldbetragTest {

// Material Vorstellung arbeitet mit Geldbetrag
// regex: (-?\\d{1,7})(,(\\d{0,2}))?
// wenn oder {1,8} und wenn die zahl zu gross ist gleich Integer.MAX_VALUE setzen
// methode istgueltig
	
	
	
	
	@Test
	void testeCompareTo()
	{
		Geldbetrag g1 = new Geldbetrag(Integer.MIN_VALUE);
		Geldbetrag g2 = new Geldbetrag(Integer.MAX_VALUE);
        assertTrue(g1.compareTo(g2) < 0);
        assertTrue(g2.compareTo(g1) > 0);
        assertTrue(g1.compareTo(g1) == 0);
	}
	
	@Test
	void testeAdd()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
		Geldbetrag g2 = new Geldbetrag(2000);
        assertEquals( new Geldbetrag(3500), g1.add(g2));
	}
	
	@Test
	void testeSub()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
		Geldbetrag g2 = new Geldbetrag(2000);
        assertEquals( new Geldbetrag(500), g2.sub(g1));
        assertEquals( new Geldbetrag(500), g1.sub(1000));
        assertEquals( new Geldbetrag(-100), g1.sub("16,00"));
	}

	@Test
	void testeMul()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
        assertEquals( new Geldbetrag(7500), g1.mul(5));
	}

	@Test
	void testeToString()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
        assertEquals( "15,00", g1.toString());
        // 99c, 7cent, 0cent, randfaelle halt, negative werte, 008,3 eur, 
	}

	@Test
	void testeToInt() // rm
	{
		Geldbetrag g1 = new Geldbetrag(1500);
        assertEquals( 1500, g1.toInt());
	}

	@Test
	void testeKonstruktoren()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
		Geldbetrag g2 = new Geldbetrag("200,00");
		Geldbetrag g3 = new Geldbetrag("700");
		assertEquals( new Geldbetrag(1500), g1.toInt());
		assertEquals( new Geldbetrag(20000), g1.toInt());
		assertEquals( new Geldbetrag(70000), g1.toInt());
		// test mit 1, 2, und 0 kommastellen
	}
	
	@Test(expected=IllegalArgumentException.class)
	void testeKonstruktorParse()
	{
		Geldbetrag g1 = new Geldbetrag("Peter");
	}
	
	@Test
	void testeAntiRoundhouseKick()
	{
		Geldbetrag g1 = new Geldbetrag("Chuck Norris");
		assertEquals( "infinity" , g1.toString());
        assertEquals( Integer.MAX_VALUE , g1.toInt());
	}

}
