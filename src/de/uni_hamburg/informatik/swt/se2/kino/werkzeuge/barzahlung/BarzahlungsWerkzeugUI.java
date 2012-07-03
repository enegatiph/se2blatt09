package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.barzahlung;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Klasse BarzahlungsWerkzeugUI ist die GUI für die Barbezahlung. Sie besteht
 * aus drei großen Eingabefeldern, von denen das mittlere zur Eingabe gedacht
 * ist.
 * 
 * @author SE2-Team
 * @version SoSe 2012
 */
class BarzahlungsWerkzeugUI
{
    private static final Color SCHRIFTFARBE_NORMAL = new Color(95, 247, 0);
    private static final Color SCHRIFTFARBE_FEHLER = new Color(255, 148, 148);
    private static final Color HINTERGRUNDFARBE = new Color(30, 30, 30);
    private static final Font SCHRIFTART_GROSS = new Font("Monospaced",
            Font.BOLD, 28);
    private static final Font SCHRIFTART_KLEIN = new Font("Monospaced",
            Font.BOLD, 22);
    private static final int TEXTFELDBREITE = 12;

    private JDialog _dialog;
    private JTextField _preisTextfield;
    private JTextField _gegebenTextfield;
    private JTextField _restTextfield;
    private JButton _geldErhaltenButton;
    private JButton _abbrechenButton;

    /**
     * Konstruktor der Klasse BarzahlungsWerkzeugUI.java
     */
    public BarzahlungsWerkzeugUI()
    {
        initDialog();
    }

    /**
     * Zeigt das Fenster an
     */
    public void zeigeAn()
    {
        _dialog.setLocationRelativeTo(null);
        _dialog.setVisible(true);
    }

    /**
     * Verbirgt das Fenster.
     */
    public void verberge()
    {
        _dialog.setVisible(false);
    }

    /**
     * Markiert das Gegeben-Textfeld normal (grün) oder als Fehler (rot)
     * 
     * @param fehler true, wenn es als fehlerfahft markiert werden soll, sonst
     *            false
     */
    public void markiereGegebenTextfeld(boolean fehler)
    {
        markiereTextfield(_gegebenTextfield, fehler);
    }

    /**
     * Markiert das Rest-Textfeld normal (grün) oder als Fehler (rot)
     * 
     * @param fehler true, wenn es als fehlerfahft markiert werden soll, sonst
     *            false
     */
    public void markiereRestTextfeld(boolean fehler)
    {
        markiereTextfield(_restTextfield, fehler);
    }

    /**
     * Markiert ein gegebenes Textfield.
     * 
     * @param textfield ein zu markierendes Textfield
     * @param fehler true, wenn ein Fehler vorliegt, sonst false.
     */
    private void markiereTextfield(JTextField textfield, boolean fehler)
    {
        if (fehler)
        {
            textfield.setForeground(BarzahlungsWerkzeugUI.SCHRIFTFARBE_FEHLER);
        }
        else
        {
            textfield.setForeground(BarzahlungsWerkzeugUI.SCHRIFTFARBE_NORMAL);
        }
    }

    /**
     * @return Textfeld mit dem eingegebenen "gegeben"-Text
     */
    public JTextField getGegebenTextfeld()
    {
        return _gegebenTextfield;
    }

    /**
     * @return Textfeld für den Preis
     */
    public JTextField getPreisTextfeld()
    {
        return _preisTextfield;
    }

    /**
     * @return Textfeld für den Restbetrag-Text
     */
    public JTextField getRestTextfeld()
    {
        return _restTextfield;
    }

    /**
     * @return GeldErhalten-Button
     */
    public JButton getGeldErhaltenButton()
    {
        return _geldErhaltenButton;
    }

    /**
     * @return Abbrechen-Button
     */
    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }

    /**
     * @return das Dialogfenster, um zum Beispiel einen WindowListener daran zu
     *         registrieren.
     */
    public JDialog getDialog()
    {
        return _dialog;
    }

    private void initDialog()
    {
        _dialog = new JDialog((Frame) null, "Barzahlung");
        _dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);

        _dialog.setLayout(new BorderLayout());
        _dialog.add(erstelleBetragsPanel(), BorderLayout.CENTER);
        _dialog.add(erstelleButtonPanel(), BorderLayout.SOUTH);

        _dialog.pack();
        _dialog.setResizable(false);
    }

    /**
     * Erstellt das ButtonPanel mit Ok und Abbrechen
     * 
     * @return das ButtonPanel
     */
    private JPanel erstelleButtonPanel()
    {
        JPanel rueckgabePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        _geldErhaltenButton = new JButton("Geld erhalten");
        rueckgabePanel.add(_geldErhaltenButton);
        _abbrechenButton = new JButton("Abbrechen");
        rueckgabePanel.add(_abbrechenButton);
        return rueckgabePanel;
    }

    /**
     * Erstellt das Hauptpanel mit den drei Anzeigen für die Beträge und
     * beschriftet diese sinnvoll.
     * 
     * @return das Panel mit den Anzeigen für die Beträge
     */
    private JPanel erstelleBetragsPanel()
    {
        JPanel betraege = new JPanel();
        betraege.setLayout(new BoxLayout(betraege, BoxLayout.Y_AXIS));
        initPreisTextfeld();
        betraege.add(erstelleLayoutPanel("Preis", _preisTextfield));
        initGegebenTextfield();
        betraege.add(erstelleLayoutPanel("gegeben", _gegebenTextfield));
        initRestTextfield();
        betraege.add(erstelleLayoutPanel("Rest", _restTextfield));

        return betraege;
    }

    private void initRestTextfield()
    {
        _restTextfield = new JTextField(TEXTFELDBREITE);
        _restTextfield.setHorizontalAlignment(JTextField.RIGHT);
        _restTextfield.setBackground(HINTERGRUNDFARBE);
        _restTextfield.setForeground(SCHRIFTFARBE_NORMAL);
        _restTextfield.setEditable(false);
        _restTextfield.setFocusable(false);
        _restTextfield.setFont(SCHRIFTART_KLEIN);
    }

    private void initGegebenTextfield()
    {
        _gegebenTextfield = new JTextField(TEXTFELDBREITE);
        _gegebenTextfield.setCaretColor(SCHRIFTFARBE_NORMAL);
        _gegebenTextfield.setHorizontalAlignment(JTextField.RIGHT);
        _gegebenTextfield.setMargin(new Insets(5, 5, 5, 5));
        _gegebenTextfield.setBackground(HINTERGRUNDFARBE);
        _gegebenTextfield.setForeground(SCHRIFTFARBE_NORMAL);
        _gegebenTextfield.setFont(SCHRIFTART_GROSS);
        _gegebenTextfield.requestFocus();
    }

    private void initPreisTextfeld()
    {
        _preisTextfield = new JTextField(TEXTFELDBREITE);
        _preisTextfield.setHorizontalAlignment(JTextField.RIGHT);
        _preisTextfield.setBackground(HINTERGRUNDFARBE);
        _preisTextfield.setForeground(SCHRIFTFARBE_NORMAL);
        _preisTextfield.setFont(SCHRIFTART_KLEIN);
        _preisTextfield.setEditable(false);
        _preisTextfield.setFocusable(false);
    }

    /**
     * Erzeugt ein Textfeld mit Beschriftung in besonderer Anordnung-
     * 
     * @param titel Beschriftung des Textfeldes
     * @param component das Textfeld (oder eine andere Komponente)
     * @return ein Panel mit dem beschrifteten Textfeld
     */
    private Component erstelleLayoutPanel(String titel, JComponent component)
    {
        JPanel rueckgabePanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(titel);
        label.setHorizontalAlignment(JLabel.RIGHT);
        rueckgabePanel.add(label, BorderLayout.NORTH);
        rueckgabePanel.add(component, BorderLayout.CENTER);

        return rueckgabePanel;
    }

}
