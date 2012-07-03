package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge;

/**
 * ToDo für Aufgabe 7: Löschen
 * 
 * Interface für Kontextwerkzeuge, die Subwerkzeuge beobachten möchten.
 * 
 * Werkzeuge, die dieses Interface implementieren, können sich an einem
 * Subwerkzeug, das von {@link BeobachtbaresSubWerkzeug} erbt, als Beobachter
 * registrieren. Sie werden dann vom Subwerkzeug bei Änderungen benachrichtigt,
 * zum Beispiel bei der Auswahl eines Materials in einer Liste durch den
 * Benutzer.
 * 
 * Damit ein Kontextwerkzeug mehrere Subwerkzeuge beobachten und auf deren
 * Nachrichten unterschiedlich reagieren kann, bietet es sich an, dieses
 * Interface in inneren Klassen (inner classes) zu implementieren.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
public interface SubwerkzeugBeobachter
{
    /**
     * Benachrichtigt diesen Beobachter bei einer Änderung in dem beobachteten
     * Subwerkzeug.
     */
    void informiereUeberAenderung();
}
