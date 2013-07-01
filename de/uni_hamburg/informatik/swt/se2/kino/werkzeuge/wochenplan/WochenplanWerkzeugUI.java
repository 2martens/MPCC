package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.java.dev.designgridlayout.DesignGridLayout;

/**
 * Die GUI von {@link WochenplanWerkzeug}.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
class WochenplanWerkzeugUI
{
    private JPanel _hauptPanel;
    private DesignGridLayout _layout;
    
    // Label für Datumsanzeigen
    private JLabel _labelDatum1;
    private JLabel _labelDatum2;
    private JLabel _labelDatum3;
    private JLabel _labelDatum4;
    private JLabel _labelDatum5;
    private JLabel _labelDatum6;
    private JLabel _labelDatum7;
    
    // Label für Wochentagsnamen
    private JLabel _labelTag1;
    private JLabel _labelTag2;
    private JLabel _labelTag3;
    private JLabel _labelTag4;
    private JLabel _labelTag5;
    private JLabel _labelTag6;
    private JLabel _labelTag7;
    
    // Panel der Subwerkzeuge
    private JPanel _panelTag1;
    private JPanel _panelTag2;
    private JPanel _panelTag3;
    private JPanel _panelTag4;
    private JPanel _panelTag5;
    private JPanel _panelTag6;
    private JPanel _panelTag7;
    
    // Konstanten für Wochentage
    private static final String MONTAG = "Montag";
    private static final String DIENSTAG = "Dienstag";
    private static final String MITTWOCH = "Mittwoch";
    private static final String DONNERSTAG = "Donnerstag";
    private static final String FREITAG = "Freitag";
    private static final String SAMSTAG = "Samstag";
    private static final String SONNTAG = "Sonntag";
    
    /**
     * Initialisiert die UI.
     * 
     * @param panelTag1
     *            Das UI-Panel des ersten Tages.
     * @param panelTag2
     *            Das UI-Panel des zweiten Tages.
     * @param panelTag3
     *            Das UI-Panel des dritten Tages.
     * @param panelTag4
     *            Das UI-Panel des vierten Tages.
     * @param panelTag5
     *            Das UI-Panel des fünften Tages.
     * @param panelTag6
     *            Das UI-Panel des sechsten Tages.
     * @param panelTag7
     *            Das UI-Panel des siebten Tages.
     * 
     * @require panelTag1 != null
     * @require panelTag2 != null
     * @require panelTag3 != null
     * @require panelTag4 != null
     * @require panelTag5 != null
     * @require panelTag6 != null
     * @require panelTag7 != null
     */
    public WochenplanWerkzeugUI(JPanel panelTag1, JPanel panelTag2,
            JPanel panelTag3, JPanel panelTag4, JPanel panelTag5,
            JPanel panelTag6, JPanel panelTag7)
    {
        assert panelTag1 != null : "Vorbedingung verletzt: panelTag1 != null";
        assert panelTag2 != null : "Vorbedingung verletzt: panelTag2 != null";
        assert panelTag3 != null : "Vorbedingung verletzt: panelTag3 != null";
        assert panelTag4 != null : "Vorbedingung verletzt: panelTag4 != null";
        assert panelTag5 != null : "Vorbedingung verletzt: panelTag5 != null";
        assert panelTag6 != null : "Vorbedingung verletzt: panelTag6 != null";
        assert panelTag7 != null : "Vorbedingung verletzt: panelTag7 != null";
        
        _panelTag1 = panelTag1;
        _panelTag2 = panelTag2;
        _panelTag3 = panelTag3;
        _panelTag4 = panelTag4;
        _panelTag5 = panelTag5;
        _panelTag6 = panelTag6;
        _panelTag7 = panelTag7;
        
        _hauptPanel = erstellePanel();
    }
    
    /**
     * Gibt das Panel zurück, in dem die Widgets angeordnet sind.
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }
    
    /**
     * Aktualisiert die Datums-Label.
     * 
     * @param datumsTexte
     *            Eine Liste der neuen Datumsanzeigen.
     * 
     * @require datumsTexte.size() == 7
     */
    public void aktualisiereDatumsLabel(List<String> datumsTexte)
    {
        assert datumsTexte.size() == 7 : "Vorbedingung verletzt: datumsTexte.size() == 7";
        
        _labelDatum1.setText(datumsTexte.get(0));
        _labelDatum2.setText(datumsTexte.get(1));
        _labelDatum3.setText(datumsTexte.get(2));
        _labelDatum4.setText(datumsTexte.get(3));
        _labelDatum5.setText(datumsTexte.get(4));
        _labelDatum6.setText(datumsTexte.get(5));
        _labelDatum7.setText(datumsTexte.get(6));
    }
    
    /**
     * Erstellt das Panel.
     */
    private JPanel erstellePanel()
    {
        JPanel panel = new JPanel();
        _layout = new DesignGridLayout(panel);
        
        // Label initialisieren
        erzeugeDatumsLabel();
        erzeugeWochentagsLabel();
        
        // Datums-Zeile erstellen
        _layout.row()
                .grid()
                .add(_labelDatum1, _labelDatum2, _labelDatum3, _labelDatum4,
                        _labelDatum5, _labelDatum6, _labelDatum7);
        
        // Wochentags-Zeile erstellen
        _layout.row()
                .grid()
                .add(_labelTag1, _labelTag2, _labelTag3, _labelTag4,
                        _labelTag5, _labelTag6, _labelTag7);
        
        _layout.row()
                .grid()
                .add(_panelTag1, _panelTag2, _panelTag3, _panelTag4,
                        _panelTag5, _panelTag6, _panelTag7);
        return panel;
    }
    
    /**
     * Erzeugt die Datums-Label.
     */
    private void erzeugeDatumsLabel()
    {
        _labelDatum1 = new JLabel();
        _labelDatum2 = new JLabel();
        _labelDatum3 = new JLabel();
        _labelDatum4 = new JLabel();
        _labelDatum5 = new JLabel();
        _labelDatum6 = new JLabel();
        _labelDatum7 = new JLabel();
    }
    
    /**
     * Erzeugt die Wochentags-Label.
     */
    private void erzeugeWochentagsLabel()
    {
        _labelTag1 = new JLabel(DONNERSTAG);
        _labelTag2 = new JLabel(FREITAG);
        _labelTag3 = new JLabel(SAMSTAG);
        _labelTag4 = new JLabel(SONNTAG);
        _labelTag5 = new JLabel(MONTAG);
        _labelTag6 = new JLabel(DIENSTAG);
        _labelTag7 = new JLabel(MITTWOCH);
    }
}
