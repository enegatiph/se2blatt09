package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/**
 * Ein Geldbetrag wird als Centwert gespeichert (Waehrung ist Euro)
 * Das Klassenobjekt stellt eine Hilfsmethode zur Verfügung um zu prüfen 
 * ob es sich bei einer Zeichenkette um eine gültige Repräsentation eines 
 * Geldbetrages handelt.
 */
public final class Geldbetrag implements Comparable<Geldbetrag>
{
	final int _betrag;
	
	// es sollten noch exceptions abgefangen werden wenn bspw. 
	// eine zahl zu groß fuer nen Integer ist.

	/**
	 * @ensure:
	 *   new Geldbetrag(0).equals(this) == true
	 */
	public Geldbetrag() 
	{
		_betrag = 0; 
	}
	
	/**
	 * @param betrag
	 *   Der Geldbetrag in (Euro-)Cent
	 * @ensure:
	 *   new Geldbetrag(betrag).equals(this) == true
	 */
	public Geldbetrag(int betrag)
	{
		_betrag = betrag;
	}
	
	public int toInt()
	{
		return _betrag;
	}
	
	@Override
	public int compareTo(Geldbetrag g)
	{
		return _betrag - g.toInt();
	}
	
	@Override
	public boolean equals(Object g)
	{
		boolean result = false;
		if((g instanceof Geldbetrag) && (_betrag == ((Geldbetrag) g).toInt()))
		{
			result = true;
		}
		return result;
	}
	
	@Override
	public int hashCode()
	{
		return _betrag % 100;
	}
	
	/**
	 * Addiert zwei Geldbeträge.
	 * 
	 * @param: g
	 *    Geldbetrag welcher mit diesem verrechnet werden soll.
	 * @reqiure:
	 *   g != null
	 * @ensure:
	 *    new Geldbetrag(this.toInt() + g.toInt()).equals(this.add(g));
	 */
	public Geldbetrag add(Geldbetrag g)
	{
		assert g != null : "Vorbedingung veletzt g != null";
		
		return new Geldbetrag(_betrag + g.toInt());
	}
	
	/**
	 * Subtrahiert zwei Geldbeträge.
	 * 
	 * @param: g
	 *    Geldbetrag welcher mit diesem verrechnet werden soll.
	 * @reqiure:
	 *   g != null
	 * @ensure:
	 *    new Geldbetrag(this.toInt() - g.toInt()).equals(this.sub(g));
	 */
	public Geldbetrag sub(Geldbetrag g)
	{
		assert g != null : "Vorbedingung veletzt g != null";
		
		return new Geldbetrag(_betrag - g.toInt());
	}
	
}
