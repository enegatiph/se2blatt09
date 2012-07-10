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
	
	/**
	 * Liefert den Geldbetrag in Cents als Ganzzahl zurueck.
	 * Ist public damit sie bei anderen Geldbetragobjekt als "this" aufgerufen 
	 * werden kann
	 * 
	 */
	public int toInt()
	{
		return _betrag;
	}
	
	/**
	 * Vergleicht zwei Geldbeträge.
	 * 
	 * @param: g
	 *    Geldbetrag welcher mit diesem verrechnet werden soll.
	 * @reqiure:
	 *   g != null
	 */
	@Override
	public int compareTo(Geldbetrag g)
	{
		assert g != null : "Vorbedingung verletzt g != null";
		
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
	
	/**
	 * Multipliziert diesen Geldbetrag mit einer Ganzzahl als Faktor.
	 * 
	 * @param: a
	 *   Faktor mit welchem dieser Geldbetrag verrechnet werden soll.
	 * @ensure:
	 *    new Geldbetrag(a * this.toInt()).equals(this.mul(a));
	 */
	public Geldbetrag mul(int a)
	{
		return new Geldbetrag(a * _betrag);
	}
	
	/**
	 * Erstellt eine Repräsentation des Geldbetrages als formatierten String
	 * 
	 * @ensure
	 *   result != null
	 */
	@Override
	public String toString()
	{
		String vorzeichen = "";
		String vorkomma = "";
		String nachkomma = "";
		String betragstr = "";
		int abs = _betrag;
		
		if(_betrag < 0)
		{
			vorzeichen = "-";
			abs *= (-1);
		}
		
		
		betragstr = new Integer(abs).toString();
		
		if(betragstr.length() > 2)
		{
			vorkomma = betragstr.substring(0, (betragstr.length() - 2));
			nachkomma = betragstr.substring((betragstr.length() - 2), betragstr.length());
		}
		else
		{
			vorkomma = "0";
			nachkomma = betragstr;
		}
		
		return String.format(vorzeichen + vorkomma + "," + "%02d", Integer.parseInt(nachkomma));
	}
	
	/**
	 * Pruft ob ein gueltiger String vorliegt welcher einen Geldbetrag 
	 * repräsentiert.
	 * Krieterien:
	 *    - Vorzeichen: ohne, "-" oder "+"
	 *    - Ziffernfolge: 1 - 7 Zeichen
	 *    - Komma (optional): ","
	 *    - Ziffernfolge: 0 - 2 Zeichen
	 *    - nach dem ersten Auftauchen eines gueltigen Zeichens, darf 
	 *      kein Leerzeichen auftreten.
	 * @param: g
	 *    Der zu prüfende String
	 * @require:
	 *    g != null
	 */
	public static boolean istGueltigerStr(String g)
	{
		return g.matches("^((-|\\+)?\\d{1,7})(,(\\d{0,2}))?$");
	}
	
	/**
	 * Überprüft ob ein String ausschließlich aus Ziffern besteht.
	 * (Vorzeichen ist erlaubt)
	 * @param s:
	 *    zu prüfender String
	 */
	private static boolean nurZiffern(String s)
	{
		return s.matches("^(-|\\+)?\\d+$");
	}
	
	/**
	 * Überprüft ob ein String genau eine Ziffer nach einem Komma hat.
	 * (Vorzeichen ist erlaubt)
	 *  
	 * @param s:
	 *    zu prüfender String
	 */
	private static boolean hatEineKommastelle(String s)
	{
		return s.matches("^(-|\\+)?\\d+,\\d{1}$");
	}
	
	/**
	 * Überprüft ob ein String genau kein Zeichen nach einem Komma hat.
	 * (Vorzeichen ist erlaubt)
	 *  
	 * @param s:
	 *    zu prüfender String
	 */
	private static boolean hatKeineNachkommaStelle(String s)
	{
		return s.matches("^(-|\\+)?\\d+,$");
	}
	
    /**
     * Erzeugt ein neues Geldbetrag-Objekt aus einem String
     * Wird keine Nachkommastelle angegeben, so wird der Wert als ganzzahliger 
     * Euroanteil gewertet.
     * 
     * @param: gstr
     *   Der zu "konvertierende" String
     * @require: 
     *   istGueltierStr(gstr) == true
     * @ensure:
     *   result != null
     */
    public static Geldbetrag parse(String gstr)
    {
    	assert istGueltigerStr(gstr) == true : "Vorbedingung verletzt: istGueltigerStr(gstr) == true";
    	
    	if(nurZiffern(gstr) || hatKeineNachkommaStelle(gstr))
    	{
    		gstr += "00";
    	}
    	else if(hatEineKommastelle(gstr))
    	{
    		gstr += "0";
    	}
    	return new Geldbetrag(Integer.parseInt(gstr.replaceAll(",", "")));
    }
}
