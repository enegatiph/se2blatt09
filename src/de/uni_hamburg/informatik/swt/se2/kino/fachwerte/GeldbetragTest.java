package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class GeldbetragTest {


	@Test
	void testeCompareTo()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
		Geldbetrag g2 = new Geldbetrag(2000);
        assertTrue(g1.compareTo(g2) < 0);
        assertTrue(g2.compareTo(g1) > 0);
        assertTrue(g1.compareTo(g1) == 0);
	}
	
	@Test
	void testeAdd()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
		Geldbetrag g2 = new Geldbetrag(2000);
        assertEquals( 3500, g1.add(g2));
        assertEquals(3000, g2.add(1000));
        assertEquals(51500, g1.add("500,00"));
	}
	
	@Test
	void testeSub()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
		Geldbetrag g2 = new Geldbetrag(2000);
        assertEquals( 500, g2.sub(g1));
        assertEquals( 500, g1.sub(1000));
        assertEquals( -100, g1.sub("16,00"));
	}

	@Test
	void testeMul()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
        assertEquals( 7500, g1.mul(5));
	}

	@Test
	void testeToString()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
        assertEquals( "15,00", g1.toString());
	}

	@Test
	void testeToInt()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
        assertEquals( 1500, g1.toInt());
	}

	@Test
	void testeSet()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
        assertEquals( 1500, g1.toInt());
        g1.Set(5000);
        assertEquals( 5000, g1.toInt());
        g1.Set("20,12");
        assertEquals( 2012, g1.toInt());
	}

	@Test
	void testeKonstruktoren()
	{
		Geldbetrag g1 = new Geldbetrag(1500);
		Geldbetrag g2 = new Geldbetrag("200,00");
		Geldbetrag g3 = new Geldbetrag("700");
		assertEquals( 1500, g1.toInt());
		assertEquals( 20000, g1.toInt());
		assertEquals( 70000, g1.toInt());
	}
	
	@Test
	void testeIntegerObject()
	{
		Geldbetrag g1 = new Geldbetrag(new Integer(1500));
		assertEquals( 1500, g1.toInt());
	}

	
	@Test
	void testeAntiRoundhouseKick()
	{
		Geldbetrag g1 = new Geldbetrag("Chuck Norris");
        assertEquals( "infinity" , g1.toString());
        assertEquals( Integer.MAX_VALUE , g1.toInt());
	}


}
