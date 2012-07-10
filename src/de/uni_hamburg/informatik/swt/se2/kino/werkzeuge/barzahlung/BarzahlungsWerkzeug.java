package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import de.uni_hamburg.informatik.swt.se2.kino.fachwerte.Geldbetrag;
import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.BeobachtbaresSubWerkzeug;

/**
 * Das Barzahlungswerkzeug behandelt die Kasseneingabe. Es ermöglicht die
 * Eingabe eines gegeben Betrags und ermittelt automatisch den Restbetrag zur
 * Unterstützung des Verkaufs.
 * 
 * Wurde die Bezahlung erfolgreich abgeschlossen, kann dieser Status abgefragt
 * werden. Bei einer erneuten Durchführung einer Bezahlung wird dieser
 * Werkzeugstatus zu Beginn wieder auf nicht erfolgreich (false) gesetzt.
 * 
 * Das Beenden des Bezahlvorgangs (OK oder Abbrechen) führt zu einer
 * Benachrichtigung aller Beobachter, die danach den Status abfragen können.
 * 
 * Das Werkzeug arbeitet mit einem modalen Dialog, d. h. der Programmfluss
 * bleibt in diesem Werkzeug und wird erst nach dem Beenden an den Aufrufer
 * zurückgegeben.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public class BarzahlungsWerkzeug extends BeobachtbaresSubWerkzeug
{

    private BarzahlungsWerkzeugUI _ui;
    private Geldbetrag _preis; //TODO zu gelbetrag
    private boolean _barzahlungErfolgreich;

    /**
     * Initialisiert das Werkzeug. Die Aktivierung erfolgt über eine sparate
     * Methode.
     * 
     */
    public BarzahlungsWerkzeug()
    {
        _ui = new BarzahlungsWerkzeugUI();
        registriereUIAktionen();
    }

    /**
     * Startet den Barzahlungsvorgang. Die UI wird angezeigt. Der Programmfluss
     * kehrt erst nach dem Beenden des Bezahlvorgangs an den Aufrufer zurück.
     */
    public void fuehreBarzahlungDurch(Geldbetrag preis) // DONE GB
    {
        _preis = preis; // DONE GB
        setzeAnfangsstatus();
        _ui.zeigeAn();
    }

    /**
     * @return true, wenn die Barzahlung erfolgreich durchgeführt wurde, sonst
     *         false.
     */
    public boolean barzahlungErfolgreich()
    {
        return _barzahlungErfolgreich;
    }

    /**
     * Registiert alle Listener an den UI-Widgets
     */
    private void registriereUIAktionen()
    {
        registriereAbbrechenAktionen();
        registriereOKAktion();
        registriereTextfeldEingabeAktion();
    }

    /**
     * Registriert einen Listener, der auf den Abbrechen-Button reagiert
     */
    private void registriereAbbrechenAktionen()
    {
        _ui.getAbbrechenButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                bezahlenNichtErfolgreich();
            }

        });
        _ui.getDialog().addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosed(WindowEvent e)
            {
                bezahlenNichtErfolgreich();
            }
        });
    }

    /**
     * Registriert einen Listener, der auf das Drücken des OK-Buttons alias
     * "Verkaufen" reagiert.
     */
    private void registriereOKAktion()
    {
        _ui.getGeldErhaltenButton().addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                bezahlenErfolgreich();
            }
        });
    }

    /**
     * Registriert einen Listener, der auf Änderungen im Gegeben-Textfeld
     * reagiert.
     */
    private void registriereTextfeldEingabeAktion()
    {
        _ui.getGegebenTextfeld().addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyReleased(KeyEvent e)
            {
                reagiereAufEingabe(e);
            }
        });
    }

    /**
     * Setzt einen neuen Status für das Werkzeug auf Basis der gesamten Eingabe
     * und beendet das Bezahlen erfoglreich, sollte der Preis gedeckt und die
     * Entertaste gedrückt worden sein.
     * 
     * @param e ein Key-Event auf das reagiert werden soll.
     */
    private void reagiereAufEingabe(KeyEvent e)
    {
        String eingabe = _ui.getGegebenTextfeld().getText();
        setzeNeuenStatus(eingabe);
        int key = e.getKeyCode();
        if (_ui.getGeldErhaltenButton().isEnabled() && key == KeyEvent.VK_ENTER)
        {
            bezahlenErfolgreich();
        }
    }

    /**
     * Beendet den Bezahlvorgang mit Erfolg.
     */
    private void bezahlenErfolgreich()
    {
        _barzahlungErfolgreich = true;
        _ui.verberge();
        informiereAlleBeobachter();
    }

    /**
     * Bricht den Bezahlvorgang ohne Erfolg ab.
     */
    private void bezahlenNichtErfolgreich()
    {
        _barzahlungErfolgreich = false;
        _ui.verberge();
        informiereAlleBeobachter();
    }

    /**
     * Setzt die UI in einen sinnvollen Anfangszustand.
     */
    private void setzeAnfangsstatus()
    {
        _barzahlungErfolgreich = false;
        setzeFehlermarkierung(false);
        setzePreis();
        loescheGegebenbetrag();
        Geldbetrag eingabeBetrag = new Geldbetrag(0);
        setzeRestbetrag(eingabeBetrag);
        setzeOKButtonStatus(eingabeBetrag);
    }

    /**
     * Löscht den gegebenen Betrag aus der UI.
     */
    private void loescheGegebenbetrag()
    {
        _ui.getGegebenTextfeld().setText("");
    }

    /**
     * Überprüft die Eingabe und setzt einen neuen Anzeigestatus.
     */
    private void setzeNeuenStatus(String eingabe)
    {
        if (istGueltig(eingabe))
        {
            setzeFehlermarkierung(false);
            //Geldbetrag eingabeBetrag = new Geldbetrag(Integer.valueOf(eingabe));
            Geldbetrag eingabeBetrag = Geldbetrag.parse(eingabe); //DONE
            setzeRestbetrag(eingabeBetrag);
            setzeOKButtonStatus(eingabeBetrag);
        }
        else
        {
            setzeFehlermarkierung(true);
        }
    }

    /**
     * Prüft ob ein gegebener String ein gültiger Geldbetrag ist. Gültig sind
     * alle ganzen Zahlen oder Zahlen mit genau zwei Nachkommastellen in
     * Dezimalschreibweise. Dabei muss der Gesamtbetrag kommabereinigt (1,00
     * zählt als 100) innerhalb des Integer-Wertebereichs liegen [−2147483648 -
     * 2147483647].
     * 
     * @param eingabe der zu prüfende String
     * @return true, wenn es sich um einen gültigen Geldbetrag handelt, false,
     *         sonst
     * @require eingabe != null
     */
    public boolean istGueltig(String eingabe)
    {
        assert eingabe != null : "Vorbedingung verletzt: eingabe != null";
        /*boolean result = eingabe.matches("-?[0-9]{1,10}");//DONE scheisse
        if (result)
        {
            long betrag = Long.valueOf(eingabe);//TODO GB
            result = ((Integer.MIN_VALUE <= betrag) && (betrag <= Integer.MAX_VALUE));
        }*/
        return Geldbetrag.istGueltigerStr(eingabe);
    }

    /**
     * Setzt die Fehlerstatusanzeige der Gegeben- und Rückgabe-Textfelder.
     * 
     * @param fehler true, wenn die Felder als fehlerhaft markiert werden
     *            sollen, sonst false.
     */
    private void setzeFehlermarkierung(boolean fehler)
    {
        _ui.markiereGegebenTextfeld(fehler);
        _ui.markiereRestTextfeld(fehler);
        if (fehler)
        {
            _ui.getRestTextfeld().setText(" Err ");

        }
    }

    /**
     * Setzt den OK-Button auf enabled, wenn der eingabeBetrag größer oder
     * gleich dem Preis ist, ansonsten wird dieser auf false gesetzt.
     * 
     * @param eingabeBetrag Ein eingegebener Betrag.
     */
    private void setzeOKButtonStatus(Geldbetrag eingabeBetrag) // TODO GB
    {
        if (eingabeBetrag.compareTo(_preis) >= 0) // TODO GB
        {
            _ui.getGeldErhaltenButton().setEnabled(true);
        }
        else
        {
            _ui.getGeldErhaltenButton().setEnabled(false);
        }
    }

    /**
     * Berechnet die Differenz des eingegeben Betrags zum Preis und setzt das
     * Ergebnis. Ist die Differenz negativ wird das Feld zudem als Fehler
     * markiert.
     * 
     * @param eingabeBetrag ein eingegebener Betrag
     */
    private void setzeRestbetrag(Geldbetrag eingabeBetrag) // TODO GB
    {
        Geldbetrag differenz = eingabeBetrag.sub(_preis); // TODO GB
        _ui.getRestTextfeld().setText("" + differenz);
        if (eingabeBetrag.compareTo(_preis) < 0)
        {
            _ui.markiereRestTextfeld(true);
        }
    }

    /**
     * Setzt den Preis in der UI.
     */
    private void setzePreis()
    {
        _ui.getPreisTextfeld().setText("" + _preis);
    }
}
