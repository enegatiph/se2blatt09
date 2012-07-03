package de.uni_hamburg.informatik.swt.se2.kino.startup;

import javax.swing.SwingUtilities;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Datum;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.FSK;
import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Uhrzeit;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Film;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kino;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Kinosaal;
import de.uni_hamburg.informatik.swt.se2.kino.materialien.Vorstellung;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kasse.KassenWerkzeug;

/**
 * Startet die Anwendung.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public class StartupKinoticketverkauf
{
    /**
     * Die Main-Methode.
     * 
     * @param args die Aufrufparameter.
     */
    public static void main(String[] args)
    {
        final Kino kino = erzeugeKinoMitBeispieldaten();
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new KassenWerkzeug(kino);
            }
        });
    }

    /**
     * Erzeugt ein Kino mit einigen Vorstellungen.
     */
    private static Kino erzeugeKinoMitBeispieldaten()
    {
        final Kinosaal[] saele = { new Kinosaal("Saal 1", 20, 25),
                new Kinosaal("Saal 2", 16, 20), new Kinosaal("Saal 3", 10, 16) };

        // Filme: Top-5 Deutschland laut kino.de in der Kalenderwoche 20, 2011.
        Film[] filme = {
                new Film("Pirates of the Caribbean - Fremde Gezeiten", 136,
                        FSK.FSK12, true),
                new Film("Fast & Furious Five", 130, FSK.FSK12, true),
                new Film("Rio", 96, FSK.FSK0, false),
                new Film("Wasser für die Elefanten", 120, FSK.FSK12, false),
                new Film("Thor", 115, FSK.FSK12, false) };

        Uhrzeit nachmittag = new Uhrzeit(17, 30);
        Uhrzeit abend = new Uhrzeit(20, 0);
        Uhrzeit spaet = new Uhrzeit(22, 30);
        Uhrzeit nacht = new Uhrzeit(1, 0);

        Datum d1 = Datum.heute();
        Datum d2 = d1.naechsterTag();
        Datum d3 = d2.naechsterTag();

        final Vorstellung[] vorstellungen = {
                // Heute
                new Vorstellung(saele[0], filme[2], nachmittag, abend, d1, 500),
                new Vorstellung(saele[0], filme[0], abend, spaet, d1, 700),
                new Vorstellung(saele[0], filme[0], spaet, nacht, d1, 700),

                new Vorstellung(saele[1], filme[3], nachmittag, abend, d1, 900),
                new Vorstellung(saele[1], filme[1], spaet, nacht, d1, 800),

                new Vorstellung(saele[2], filme[3], abend, spaet, d1, 1000),
                new Vorstellung(saele[2], filme[4], spaet, nacht, d1, 900),

                // Morgen
                new Vorstellung(saele[0], filme[0], abend, spaet, d2, 500),
                new Vorstellung(saele[0], filme[0], spaet, nacht, d2, 700),

                new Vorstellung(saele[1], filme[2], nachmittag, abend, d2, 900),
                new Vorstellung(saele[1], filme[4], abend, nacht, d2, 800),

                new Vorstellung(saele[2], filme[3], nachmittag, abend, d2, 1000),
                new Vorstellung(saele[2], filme[1], spaet, nacht, d2, 900),

                // Übermorgen
                new Vorstellung(saele[0], filme[1], abend, spaet, d3, 500),
                new Vorstellung(saele[0], filme[1], spaet, nacht, d3, 700),

                new Vorstellung(saele[1], filme[2], nachmittag, abend, d3, 900),
                new Vorstellung(saele[1], filme[0], abend, nacht, d3, 800),

                new Vorstellung(saele[2], filme[3], abend, spaet, d3, 1000),
                new Vorstellung(saele[2], filme[4], spaet, nacht, d3, 900) };

        return new Kino(saele, vorstellungen);
    }
}
