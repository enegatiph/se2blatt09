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
		assert istSubMoeglich(g) : "Vorbedingung veletzt istSubMoeglich(g)";
		
		return _betrag - g._betrag;
	}
	
	
	@Override
	public boolean equals(Object g)
	{
		boolean result = false;
		if((g instanceof Geldbetrag) && (_betrag == ((Geldbetrag) g)._betrag))
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
	 *    new Geldbetrag(this._betrag + g._betrag).equals(this.add(g));
	 */
	public Geldbetrag add(Geldbetrag g)
	{
		assert istAddMoeglich(g) : "Vorbedingung veletzt istAddMoeglich(g)";
		assert g != null : "Vorbedingung veletzt g != null";
		
		return new Geldbetrag(_betrag + g._betrag);
	}
	
	/**
	 * Subtrahiert zwei Geldbeträge.
	 * 
	 * @param: g
	 *    Geldbetrag welcher mit diesem verrechnet werden soll.
	 * @reqiure:
	 *   g != null
	 * @ensure:
	 *    new Geldbetrag(this._betrag - g._betrag).equals(this.sub(g));
	 */
	public Geldbetrag sub(Geldbetrag g)
	{
		assert g != null : "Vorbedingung veletzt g != null";
		assert istSubMoeglich(g) : "Vorbedingung veletzt istSubMoeglich(g)";
		return new Geldbetrag(_betrag - g._betrag);
	}
	
	/**
	 * Multipliziert diesen Geldbetrag mit einer Ganzzahl als Faktor.
	 * 
	 * @param: a
	 *   Faktor mit welchem dieser Geldbetrag verrechnet werden soll.
	 * @ensure:
	 *    new Geldbetrag(a * this._betrag).equals(this.mul(a));
	 */
	public Geldbetrag mul(int a)
	{
		assert istMulMoeglich(a) : "Vorbedingung veletzt istMulMoeglich(g)";
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
		int vorkomma = Math.abs( _betrag / 100);
		int nachkomma = Math.abs( _betrag % 100 );
		
		if (_betrag < 0)
		{
			vorzeichen = "-";
		}
			
		return String.format("%s%d,%02d", vorzeichen, vorkomma, nachkomma);
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
    
	/**
	 * Überprüft ob Addieren möglich ist (Wegen Werteberreichs Überlauf)
	 * @param betrag:
	 *    zu prüfender Betrag
	 */
    public boolean istAddMoeglich(Geldbetrag betrag)
    {
    	long i= betrag._betrag;
    	i+= _betrag;
    	return (i <= Integer.MAX_VALUE && i>= Integer.MIN_VALUE);
    }
    
	/**
	 * Überprüft ob Subtrahieren möglich ist (Wegen Werteberreichs Überlauf)
	 * @param betrag:
	 *    zu prüfender Betrag
	 */
    public boolean istSubMoeglich(Geldbetrag betrag)
    {
    	long i= _betrag;
    	i-= betrag._betrag;
    	return (i <= Integer.MAX_VALUE && i>= Integer.MIN_VALUE);
    }
    
	/**
	 * Überprüft ob Multiplizieren möglich ist (Wegen Werteberreichs Überlauf)
	 * @param n:
	 *    zu prüfender Int
	 */
    public boolean istMulMoeglich(int n)
    {
    	long i= _betrag;
    	i= i*n;
    	return (i <= Integer.MAX_VALUE && i>= Integer.MIN_VALUE);
    }
    
    
}
