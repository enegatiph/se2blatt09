package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeldbetragTest {

// Material Vorstellung arbeitet mit Geldbetrag
// regex: ((-|+)?\\d{1,7})(,(\\d{0,2}))?
// wenn oder {1,8} und wenn die zahl zu gross ist gleich Integer.MAX_VALUE setzen
// methode istgueltig

// DONE 1. tests verbessern
// DONE 2. klasse verbessern
// 3. refactoring
	
	@Test
	public void testeCompareTo()
	{
		Geldbetrag g1 = new Geldbetrag(Integer.MIN_VALUE);
		Geldbetrag g2 = new Geldbetrag(Integer.MAX_VALUE);
        assertTrue(g1.compareTo(g2) > 0);
        assertTrue(g2.compareTo(g1) < 0);
        assertTrue(g1.compareTo(g1) == 0);
	}
	
	@Test
	public void testeAdd()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
		Geldbetrag g2 = new Geldbetrag(2000);
        assertEquals( new Geldbetrag(3500), g1.add(g2));
	}
	
	@Test
	public void testeSub()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
		Geldbetrag g2 = new Geldbetrag(2000);
        assertEquals( new Geldbetrag(500), g2.sub(g1));
        assertEquals( new Geldbetrag(500), g1.sub(new Geldbetrag(1000)));
	}

	@Test
	public void testeMul()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
        assertEquals( new Geldbetrag(7500), g1.mul(5));
	}
	

	@Test
	public void testeToString()
	{
        assertEquals("15,00", new Geldbetrag(1500).toString());
        assertEquals("0,07", new Geldbetrag(7).toString());
        assertEquals("0,99", new Geldbetrag(99).toString());
        assertEquals("0,00", new Geldbetrag(0).toString());
        assertEquals("0,00", new Geldbetrag(-0).toString());
        assertEquals("-0,99", new Geldbetrag(-99).toString());
        assertEquals("-0,07", new Geldbetrag(-7).toString());
        assertEquals("-15,00", new Geldbetrag(-1500).toString());
	}

	@Test
	public void testeParse()
	{
		assertEquals(new Geldbetrag(1500), Geldbetrag.parse("15,00"));
		assertEquals(new Geldbetrag(1500), Geldbetrag.parse("15,0"));
		assertEquals(new Geldbetrag(1500), Geldbetrag.parse("15,"));
		assertEquals(new Geldbetrag(1500), Geldbetrag.parse("15"));
		assertEquals(new Geldbetrag(7), Geldbetrag.parse("0,07"));
		assertEquals(new Geldbetrag(7), Geldbetrag.parse("00,07"));
		assertEquals(new Geldbetrag(700), Geldbetrag.parse("07"));
		assertEquals(new Geldbetrag(700), Geldbetrag.parse("7"));
		assertEquals(new Geldbetrag(99), Geldbetrag.parse("0,99"));
		assertEquals(new Geldbetrag(99), Geldbetrag.parse("00,99"));
		assertEquals(new Geldbetrag(9900), Geldbetrag.parse("099"));
		assertEquals(new Geldbetrag(9900), Geldbetrag.parse("99"));
		assertEquals(new Geldbetrag(0), Geldbetrag.parse("0"));
		assertEquals(new Geldbetrag(0), Geldbetrag.parse("0000000"));
		assertEquals(new Geldbetrag(0), Geldbetrag.parse("0,0"));
		assertEquals(new Geldbetrag(0), Geldbetrag.parse("0,00"));
		assertEquals(new Geldbetrag(0), Geldbetrag.parse("-0"));
		assertEquals(new Geldbetrag(-99), Geldbetrag.parse("-0,99"));
		assertEquals(new Geldbetrag(-99), Geldbetrag.parse("-00,99"));
		assertEquals(new Geldbetrag(-9900), Geldbetrag.parse("-099"));
		assertEquals(new Geldbetrag(-9900), Geldbetrag.parse("-99"));
		// test mit 1, 2, und 0 kommastellen
	}
	
	/*
	@Test(expected=IllegalArgumentException.class)
	public void testeKonstruktorParse()
	{
		Geldbetrag.parse("Peter");
		
	}
	*/
	/*
	@Test
	public void testeAntiRoundhouseKick()
	{
		Geldbetrag g1 = new Geldbetrag("Chuck Norris");
		assertEquals( "infinity" , g1.toString());
        assertEquals( Integer.MAX_VALUE , g1.toInt());
	}
*/
}
