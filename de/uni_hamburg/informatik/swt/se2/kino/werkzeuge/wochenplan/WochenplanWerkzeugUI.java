package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    
    // 15:00 Zeile
    private JPanel _tag1Panel1500;
    private JPanel _tag2Panel1500;
    private JPanel _tag3Panel1500;
    private JPanel _tag4Panel1500;
    private JPanel _tag5Panel1500;
    private JPanel _tag6Panel1500;
    private JPanel _tag7Panel1500;
    private JComboBox<FilmFormatierer> _tag1FilmBox1500;
    private JComboBox<FilmFormatierer> _tag2FilmBox1500;
    private JComboBox<FilmFormatierer> _tag3FilmBox1500;
    private JComboBox<FilmFormatierer> _tag4FilmBox1500;
    private JComboBox<FilmFormatierer> _tag5FilmBox1500;
    private JComboBox<FilmFormatierer> _tag6FilmBox1500;
    private JComboBox<FilmFormatierer> _tag7FilmBox1500;
    private JTextField _tag1WerbeblockMin1500;
    private JTextField _tag2WerbeblockMin1500;
    private JTextField _tag3WerbeblockMin1500;
    private JTextField _tag4WerbeblockMin1500;
    private JTextField _tag5WerbeblockMin1500;
    private JTextField _tag6WerbeblockMin1500;
    private JTextField _tag7WerbeblockMin1500;
    private JComboBox<FSKFormatierer> _tag1FSKBox1500;
    private JComboBox<FSKFormatierer> _tag2FSKBox1500;
    private JComboBox<FSKFormatierer> _tag3FSKBox1500;
    private JComboBox<FSKFormatierer> _tag4FSKBox1500;
    private JComboBox<FSKFormatierer> _tag5FSKBox1500;
    private JComboBox<FSKFormatierer> _tag6FSKBox1500;
    private JComboBox<FSKFormatierer> _tag7FSKBox1500;
    
    // 17:30 Zeile
    private JPanel _tag1Panel1730;
    private JPanel _tag2Panel1730;
    private JPanel _tag3Panel1730;
    private JPanel _tag4Panel1730;
    private JPanel _tag5Panel1730;
    private JPanel _tag6Panel1730;
    private JPanel _tag7Panel1730;
    private JComboBox<FilmFormatierer> _tag1FilmBox1730;
    private JComboBox<FilmFormatierer> _tag2FilmBox1730;
    private JComboBox<FilmFormatierer> _tag3FilmBox1730;
    private JComboBox<FilmFormatierer> _tag4FilmBox1730;
    private JComboBox<FilmFormatierer> _tag5FilmBox1730;
    private JComboBox<FilmFormatierer> _tag6FilmBox1730;
    private JComboBox<FilmFormatierer> _tag7FilmBox1730;
    private JTextField _tag1WerbeblockMin1730;
    private JTextField _tag2WerbeblockMin1730;
    private JTextField _tag3WerbeblockMin1730;
    private JTextField _tag4WerbeblockMin1730;
    private JTextField _tag5WerbeblockMin1730;
    private JTextField _tag6WerbeblockMin1730;
    private JTextField _tag7WerbeblockMin1730;
    private JComboBox<FSKFormatierer> _tag1FSKBox1730;
    private JComboBox<FSKFormatierer> _tag2FSKBox1730;
    private JComboBox<FSKFormatierer> _tag3FSKBox1730;
    private JComboBox<FSKFormatierer> _tag4FSKBox1730;
    private JComboBox<FSKFormatierer> _tag5FSKBox1730;
    private JComboBox<FSKFormatierer> _tag6FSKBox1730;
    private JComboBox<FSKFormatierer> _tag7FSKBox1730;
    
    // 20:00 Zeile
    private JPanel _tag1Panel2000;
    private JPanel _tag2Panel2000;
    private JPanel _tag3Panel2000;
    private JPanel _tag4Panel2000;
    private JPanel _tag5Panel2000;
    private JPanel _tag6Panel2000;
    private JPanel _tag7Panel2000;
    private JComboBox<FilmFormatierer> _tag1FilmBox2000;
    private JComboBox<FilmFormatierer> _tag2FilmBox2000;
    private JComboBox<FilmFormatierer> _tag3FilmBox2000;
    private JComboBox<FilmFormatierer> _tag4FilmBox2000;
    private JComboBox<FilmFormatierer> _tag5FilmBox2000;
    private JComboBox<FilmFormatierer> _tag6FilmBox2000;
    private JComboBox<FilmFormatierer> _tag7FilmBox2000;
    private JTextField _tag1WerbeblockMin2000;
    private JTextField _tag2WerbeblockMin2000;
    private JTextField _tag3WerbeblockMin2000;
    private JTextField _tag4WerbeblockMin2000;
    private JTextField _tag5WerbeblockMin2000;
    private JTextField _tag6WerbeblockMin2000;
    private JTextField _tag7WerbeblockMin2000;
    private JComboBox<FSKFormatierer> _tag1FSKBox2000;
    private JComboBox<FSKFormatierer> _tag2FSKBox2000;
    private JComboBox<FSKFormatierer> _tag3FSKBox2000;
    private JComboBox<FSKFormatierer> _tag4FSKBox2000;
    private JComboBox<FSKFormatierer> _tag5FSKBox2000;
    private JComboBox<FSKFormatierer> _tag6FSKBox2000;
    private JComboBox<FSKFormatierer> _tag7FSKBox2000;
    
    // 20:30 Zeile
    private JPanel _tag1Panel2230;
    private JPanel _tag2Panel2230;
    private JPanel _tag3Panel2230;
    private JPanel _tag4Panel2230;
    private JPanel _tag5Panel2230;
    private JPanel _tag6Panel2230;
    private JPanel _tag7Panel2230;
    private JComboBox<FilmFormatierer> _tag1FilmBox2230;
    private JComboBox<FilmFormatierer> _tag2FilmBox2230;
    private JComboBox<FilmFormatierer> _tag3FilmBox2230;
    private JComboBox<FilmFormatierer> _tag4FilmBox2230;
    private JComboBox<FilmFormatierer> _tag5FilmBox2230;
    private JComboBox<FilmFormatierer> _tag6FilmBox2230;
    private JComboBox<FilmFormatierer> _tag7FilmBox2230;
    private JTextField _tag1WerbeblockMin2230;
    private JTextField _tag2WerbeblockMin2230;
    private JTextField _tag3WerbeblockMin2230;
    private JTextField _tag4WerbeblockMin2230;
    private JTextField _tag5WerbeblockMin2230;
    private JTextField _tag6WerbeblockMin2230;
    private JTextField _tag7WerbeblockMin2230;
    private JComboBox<FSKFormatierer> _tag1FSKBox2230;
    private JComboBox<FSKFormatierer> _tag2FSKBox2230;
    private JComboBox<FSKFormatierer> _tag3FSKBox2230;
    private JComboBox<FSKFormatierer> _tag4FSKBox2230;
    private JComboBox<FSKFormatierer> _tag5FSKBox2230;
    private JComboBox<FSKFormatierer> _tag6FSKBox2230;
    private JComboBox<FSKFormatierer> _tag7FSKBox2230;
    
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
     */
    public WochenplanWerkzeugUI()
    {
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
     * Gibt die Filmlisten zurück.
     */
    public List<JComboBox<FilmFormatierer>> getFilmListen()
    {
        List<JComboBox<FilmFormatierer>> list = new ArrayList<JComboBox<FilmFormatierer>>();
        list.add(_tag1FilmBox1500);
        list.add(_tag2FilmBox1500);
        list.add(_tag3FilmBox1500);
        list.add(_tag4FilmBox1500);
        list.add(_tag5FilmBox1500);
        list.add(_tag6FilmBox1500);
        list.add(_tag7FilmBox1500);
        
        list.add(_tag1FilmBox1730);
        list.add(_tag2FilmBox1730);
        list.add(_tag3FilmBox1730);
        list.add(_tag4FilmBox1730);
        list.add(_tag5FilmBox1730);
        list.add(_tag6FilmBox1730);
        list.add(_tag7FilmBox1730);
        
        list.add(_tag1FilmBox2000);
        list.add(_tag2FilmBox2000);
        list.add(_tag3FilmBox2000);
        list.add(_tag4FilmBox2000);
        list.add(_tag5FilmBox2000);
        list.add(_tag6FilmBox2000);
        list.add(_tag7FilmBox2000);
        
        list.add(_tag1FilmBox2230);
        list.add(_tag2FilmBox2230);
        list.add(_tag3FilmBox2230);
        list.add(_tag4FilmBox2230);
        list.add(_tag5FilmBox2230);
        list.add(_tag6FilmBox2230);
        list.add(_tag7FilmBox2230);
        return list;
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
        
        // Panel initialisieren
        erzeuge1500Panel();
        erzeuge1730Panel();
        erzeuge2000Panel();
        erzeuge2230Panel();
        
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
        
        // 15:00 Uhr Zeile erstellen
        _layout.row()
                .grid(new JLabel("15:00"))
                .add(_tag1Panel1500, _tag2Panel1500, _tag3Panel1500,
                        _tag4Panel1500, _tag5Panel1500, _tag6Panel1500,
                        _tag7Panel1500);
        
        // 17:30 Uhr Zeile erstellen
        _layout.row()
                .grid(new JLabel("17:30"))
                .add(_tag1Panel1730, _tag2Panel1730, _tag3Panel1730,
                        _tag4Panel1730, _tag5Panel1730, _tag6Panel1730,
                        _tag7Panel1730);
        
        // 20:00 Uhr Zeile erstellen
        _layout.row()
                .grid(new JLabel("20:00"))
                .add(_tag1Panel2000, _tag2Panel2000, _tag3Panel2000,
                        _tag4Panel2000, _tag5Panel2000, _tag6Panel2000,
                        _tag7Panel2000);
                        
        // 22:30 Uhr Zeile erstellen
        _layout.row()
                .grid(new JLabel("22:30"))
                .add(_tag1Panel2230, _tag2Panel2230, _tag3Panel2230,
                        _tag4Panel2230, _tag5Panel2230, _tag6Panel2230,
                        _tag7Panel2230);
        return panel;
    }
    
    /**
     * Erzeugt die 22:30 Uhr Panel.
     */
    private void erzeuge2230Panel()
    {
        erzeuge2230FilmBoxen();
        erzeuge2230WerbeblockTextfelder();
        erzeuge2230WerbeblockFSKBoxen();
        
        // Tag 1
        _tag1Panel2230 = new JPanel();
        DesignGridLayout gridLayout1 = new DesignGridLayout(_tag1Panel2230);
        gridLayout1.row().grid(new JLabel("Film")).add(_tag1FilmBox2230);
        gridLayout1
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag1WerbeblockMin2230,
                        _tag1FSKBox2230);
        // Tag 2
        _tag2Panel2230 = new JPanel();
        DesignGridLayout gridLayout2 = new DesignGridLayout(_tag2Panel2230);
        gridLayout2.row().grid(new JLabel("Film")).add(_tag2FilmBox2230);
        gridLayout2
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag2WerbeblockMin2230,
                        _tag2FSKBox2230);
        // Tag 3
        _tag3Panel2230 = new JPanel();
        DesignGridLayout gridLayout3 = new DesignGridLayout(_tag3Panel2230);
        gridLayout3.row().grid(new JLabel("Film")).add(_tag3FilmBox2230);
        gridLayout3
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag3WerbeblockMin2230,
                        _tag3FSKBox2230);
        
        // Tag 4
        _tag4Panel2230 = new JPanel();
        DesignGridLayout gridLayout4 = new DesignGridLayout(_tag4Panel2230);
        gridLayout4.row().grid(new JLabel("Film")).add(_tag4FilmBox2230);
        gridLayout4
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag4WerbeblockMin2230,
                        _tag4FSKBox2230);
        
        // Tag 5
        _tag5Panel2230 = new JPanel();
        DesignGridLayout gridLayout5 = new DesignGridLayout(_tag5Panel2230);
        gridLayout5.row().grid(new JLabel("Film")).add(_tag5FilmBox2230);
        gridLayout5
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag5WerbeblockMin2230,
                        _tag5FSKBox2230);
        // Tag 6
        _tag6Panel2230 = new JPanel();
        DesignGridLayout gridLayout6 = new DesignGridLayout(_tag6Panel2230);
        gridLayout6.row().grid(new JLabel("Film")).add(_tag6FilmBox2230);
        gridLayout6
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag6WerbeblockMin2230,
                        _tag6FSKBox2230);
        
        // Tag 7
        _tag7Panel2230 = new JPanel();
        DesignGridLayout gridLayout7 = new DesignGridLayout(_tag7Panel2230);
        gridLayout7.row().grid(new JLabel("Film")).add(_tag7FilmBox2230);
        gridLayout7
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag7WerbeblockMin2230,
                        _tag7FSKBox2230);
    }
    
    /**
     * Erzeugt die 20:00 Uhr Panel.
     */
    private void erzeuge2000Panel()
    {
        erzeuge2000FilmBoxen();
        erzeuge2000WerbeblockTextfelder();
        erzeuge2000WerbeblockFSKBoxen();
        
        // Tag 1
        _tag1Panel2000 = new JPanel();
        DesignGridLayout gridLayout1 = new DesignGridLayout(_tag1Panel2000);
        gridLayout1.row().grid(new JLabel("Film")).add(_tag1FilmBox2000);
        gridLayout1
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag1WerbeblockMin2000,
                        _tag1FSKBox2000);
        // Tag 2
        _tag2Panel2000 = new JPanel();
        DesignGridLayout gridLayout2 = new DesignGridLayout(_tag2Panel2000);
        gridLayout2.row().grid(new JLabel("Film")).add(_tag2FilmBox2000);
        gridLayout2
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag2WerbeblockMin2000,
                        _tag2FSKBox2000);
        // Tag 3
        _tag3Panel2000 = new JPanel();
        DesignGridLayout gridLayout3 = new DesignGridLayout(_tag3Panel2000);
        gridLayout3.row().grid(new JLabel("Film")).add(_tag3FilmBox2000);
        gridLayout3
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag3WerbeblockMin2000,
                        _tag3FSKBox2000);
        
        // Tag 4
        _tag4Panel2000 = new JPanel();
        DesignGridLayout gridLayout4 = new DesignGridLayout(_tag4Panel2000);
        gridLayout4.row().grid(new JLabel("Film")).add(_tag4FilmBox2000);
        gridLayout4
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag4WerbeblockMin2000,
                        _tag4FSKBox2000);
        
        // Tag 5
        _tag5Panel2000 = new JPanel();
        DesignGridLayout gridLayout5 = new DesignGridLayout(_tag5Panel2000);
        gridLayout5.row().grid(new JLabel("Film")).add(_tag5FilmBox2000);
        gridLayout5
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag5WerbeblockMin2000,
                        _tag5FSKBox2000);
        // Tag 6
        _tag6Panel2000 = new JPanel();
        DesignGridLayout gridLayout6 = new DesignGridLayout(_tag6Panel2000);
        gridLayout6.row().grid(new JLabel("Film")).add(_tag6FilmBox2000);
        gridLayout6
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag6WerbeblockMin2000,
                        _tag6FSKBox2000);
        
        // Tag 7
        _tag7Panel2000 = new JPanel();
        DesignGridLayout gridLayout7 = new DesignGridLayout(_tag7Panel2000);
        gridLayout7.row().grid(new JLabel("Film")).add(_tag7FilmBox2000);
        gridLayout7
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag7WerbeblockMin2000,
                        _tag7FSKBox2000);
    }
    
    /**
     * Erzeugt die 17:30 Uhr Panel.
     */
    private void erzeuge1730Panel()
    {
        erzeuge1730FilmBoxen();
        erzeuge1730WerbeblockTextfelder();
        erzeuge1730WerbeblockFSKBoxen();
        
        // Tag 1
        _tag1Panel1730 = new JPanel();
        DesignGridLayout gridLayout1 = new DesignGridLayout(_tag1Panel1730);
        gridLayout1.row().grid(new JLabel("Film")).add(_tag1FilmBox1730);
        gridLayout1
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag1WerbeblockMin1730,
                        _tag1FSKBox1730);
        // Tag 2
        _tag2Panel1730 = new JPanel();
        DesignGridLayout gridLayout2 = new DesignGridLayout(_tag2Panel1730);
        gridLayout2.row().grid(new JLabel("Film")).add(_tag2FilmBox1730);
        gridLayout2
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag2WerbeblockMin1730,
                        _tag2FSKBox1730);
        // Tag 3
        _tag3Panel1730 = new JPanel();
        DesignGridLayout gridLayout3 = new DesignGridLayout(_tag3Panel1730);
        gridLayout3.row().grid(new JLabel("Film")).add(_tag3FilmBox1730);
        gridLayout3
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag3WerbeblockMin1730,
                        _tag3FSKBox1730);
        
        // Tag 4
        _tag4Panel1730 = new JPanel();
        DesignGridLayout gridLayout4 = new DesignGridLayout(_tag4Panel1730);
        gridLayout4.row().grid(new JLabel("Film")).add(_tag4FilmBox1730);
        gridLayout4
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag4WerbeblockMin1730,
                        _tag4FSKBox1730);
        
        // Tag 5
        _tag5Panel1730 = new JPanel();
        DesignGridLayout gridLayout5 = new DesignGridLayout(_tag5Panel1730);
        gridLayout5.row().grid(new JLabel("Film")).add(_tag5FilmBox1730);
        gridLayout5
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag5WerbeblockMin1730,
                        _tag5FSKBox1730);
        // Tag 6
        _tag6Panel1730 = new JPanel();
        DesignGridLayout gridLayout6 = new DesignGridLayout(_tag6Panel1730);
        gridLayout6.row().grid(new JLabel("Film")).add(_tag6FilmBox1730);
        gridLayout6
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag6WerbeblockMin1730,
                        _tag6FSKBox1730);
        
        // Tag 7
        _tag7Panel1730 = new JPanel();
        DesignGridLayout gridLayout7 = new DesignGridLayout(_tag7Panel1730);
        gridLayout7.row().grid(new JLabel("Film")).add(_tag7FilmBox1730);
        gridLayout7
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag7WerbeblockMin1730,
                        _tag7FSKBox1730);
    }
    
    /**
     * Erzeugt die 15:00 Panel.
     * TODO: Reinigungszeit
     */
    private void erzeuge1500Panel()
    {
        erzeuge1500FilmBoxen();
        erzeuge1500WerbeblockTextfelder();
        erzeuge1500WerbeblockFSKBoxen();
        
        // Tag 1
        _tag1Panel1500 = new JPanel();
        DesignGridLayout gridLayout1 = new DesignGridLayout(_tag1Panel1500);
        gridLayout1.row().grid(new JLabel("Film")).add(_tag1FilmBox1500);
        gridLayout1
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag1WerbeblockMin1500,
                        _tag1FSKBox1500);
        // Tag 2
        _tag2Panel1500 = new JPanel();
        DesignGridLayout gridLayout2 = new DesignGridLayout(_tag2Panel1500);
        gridLayout2.row().grid(new JLabel("Film")).add(_tag2FilmBox1500);
        gridLayout2
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag2WerbeblockMin1500,
                        _tag2FSKBox1500);
        // Tag 3
        _tag3Panel1500 = new JPanel();
        DesignGridLayout gridLayout3 = new DesignGridLayout(_tag3Panel1500);
        gridLayout3.row().grid(new JLabel("Film")).add(_tag3FilmBox1500);
        gridLayout3
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag3WerbeblockMin1500,
                        _tag3FSKBox1500);
        
        // Tag 4
        _tag4Panel1500 = new JPanel();
        DesignGridLayout gridLayout4 = new DesignGridLayout(_tag4Panel1500);
        gridLayout4.row().grid(new JLabel("Film")).add(_tag4FilmBox1500);
        gridLayout4
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag4WerbeblockMin1500,
                        _tag4FSKBox1500);
        
        // Tag 5
        _tag5Panel1500 = new JPanel();
        DesignGridLayout gridLayout5 = new DesignGridLayout(_tag5Panel1500);
        gridLayout5.row().grid(new JLabel("Film")).add(_tag5FilmBox1500);
        gridLayout5
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag5WerbeblockMin1500,
                        _tag5FSKBox1500);
        // Tag 6
        _tag6Panel1500 = new JPanel();
        DesignGridLayout gridLayout6 = new DesignGridLayout(_tag6Panel1500);
        gridLayout6.row().grid(new JLabel("Film")).add(_tag6FilmBox1500);
        gridLayout6
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag6WerbeblockMin1500,
                        _tag6FSKBox1500);
        
        // Tag 7
        _tag7Panel1500 = new JPanel();
        DesignGridLayout gridLayout7 = new DesignGridLayout(_tag7Panel1500);
        gridLayout7.row().grid(new JLabel("Film")).add(_tag7FilmBox1500);
        gridLayout7
                .row()
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _tag7WerbeblockMin1500,
                        _tag7FSKBox1500);
    }
    
    /**
     * Erzeugt die 15:00 Uhr Film-Auswahllisten.
     */
    private void erzeuge1500FilmBoxen()
    {
        _tag1FilmBox1500 = new JComboBox<FilmFormatierer>();
        _tag2FilmBox1500 = new JComboBox<FilmFormatierer>();
        _tag3FilmBox1500 = new JComboBox<FilmFormatierer>();
        _tag4FilmBox1500 = new JComboBox<FilmFormatierer>();
        _tag5FilmBox1500 = new JComboBox<FilmFormatierer>();
        _tag6FilmBox1500 = new JComboBox<FilmFormatierer>();
        _tag7FilmBox1500 = new JComboBox<FilmFormatierer>();
    }
    
    /**
     * Erzeugt die 15:00 Uhr Werbeblock Minuten-Textfelder.
     */
    private void erzeuge1500WerbeblockTextfelder()
    {
        _tag1WerbeblockMin1500 = new JTextField("15");
        _tag2WerbeblockMin1500 = new JTextField("15");
        _tag3WerbeblockMin1500 = new JTextField("15");
        _tag4WerbeblockMin1500 = new JTextField("15");
        _tag5WerbeblockMin1500 = new JTextField("15");
        _tag6WerbeblockMin1500 = new JTextField("15");
        _tag7WerbeblockMin1500 = new JTextField("15");
    }
    
    /**
     * Erzeugt die 15:00 Uhr Werbeblock FSK-Boxen.
     */
    private void erzeuge1500WerbeblockFSKBoxen()
    {
        _tag1FSKBox1500 = new JComboBox<FSKFormatierer>();
        _tag2FSKBox1500 = new JComboBox<FSKFormatierer>();
        _tag3FSKBox1500 = new JComboBox<FSKFormatierer>();
        _tag4FSKBox1500 = new JComboBox<FSKFormatierer>();
        _tag5FSKBox1500 = new JComboBox<FSKFormatierer>();
        _tag6FSKBox1500 = new JComboBox<FSKFormatierer>();
        _tag7FSKBox1500 = new JComboBox<FSKFormatierer>();
    }
    
    /**
     * Erzeugt die 17:30 Uhr Film-Auswahllisten.
     */
    private void erzeuge1730FilmBoxen()
    {
        _tag1FilmBox1730 = new JComboBox<FilmFormatierer>();
        _tag2FilmBox1730 = new JComboBox<FilmFormatierer>();
        _tag3FilmBox1730 = new JComboBox<FilmFormatierer>();
        _tag4FilmBox1730 = new JComboBox<FilmFormatierer>();
        _tag5FilmBox1730 = new JComboBox<FilmFormatierer>();
        _tag6FilmBox1730 = new JComboBox<FilmFormatierer>();
        _tag7FilmBox1730 = new JComboBox<FilmFormatierer>();
    }
    
    /**
     * Erzeugt die 17:30 Uhr Werbeblock Minuten-Textfelder.
     */
    private void erzeuge1730WerbeblockTextfelder()
    {
        _tag1WerbeblockMin1730 = new JTextField("15");
        _tag2WerbeblockMin1730 = new JTextField("15");
        _tag3WerbeblockMin1730 = new JTextField("15");
        _tag4WerbeblockMin1730 = new JTextField("15");
        _tag5WerbeblockMin1730 = new JTextField("15");
        _tag6WerbeblockMin1730 = new JTextField("15");
        _tag7WerbeblockMin1730 = new JTextField("15");
    }
    
    /**
     * Erzeugt die 17:30 Uhr Werbeblock FSK-Boxen.
     */
    private void erzeuge1730WerbeblockFSKBoxen()
    {
        _tag1FSKBox1730 = new JComboBox<FSKFormatierer>();
        _tag2FSKBox1730 = new JComboBox<FSKFormatierer>();
        _tag3FSKBox1730 = new JComboBox<FSKFormatierer>();
        _tag4FSKBox1730 = new JComboBox<FSKFormatierer>();
        _tag5FSKBox1730 = new JComboBox<FSKFormatierer>();
        _tag6FSKBox1730 = new JComboBox<FSKFormatierer>();
        _tag7FSKBox1730 = new JComboBox<FSKFormatierer>();
    }
    
    /**
     * Erzeugt die 20:00 Uhr Film-Auswahllisten.
     */
    private void erzeuge2000FilmBoxen()
    {
        _tag1FilmBox2000 = new JComboBox<FilmFormatierer>();
        _tag2FilmBox2000 = new JComboBox<FilmFormatierer>();
        _tag3FilmBox2000 = new JComboBox<FilmFormatierer>();
        _tag4FilmBox2000 = new JComboBox<FilmFormatierer>();
        _tag5FilmBox2000 = new JComboBox<FilmFormatierer>();
        _tag6FilmBox2000 = new JComboBox<FilmFormatierer>();
        _tag7FilmBox2000 = new JComboBox<FilmFormatierer>();
    }
    
    /**
     * Erzeugt die 20:00 Uhr Werbeblock Minuten-Textfelder.
     */
    private void erzeuge2000WerbeblockTextfelder()
    {
        _tag1WerbeblockMin2000 = new JTextField("15");
        _tag2WerbeblockMin2000 = new JTextField("15");
        _tag3WerbeblockMin2000 = new JTextField("15");
        _tag4WerbeblockMin2000 = new JTextField("15");
        _tag5WerbeblockMin2000 = new JTextField("15");
        _tag6WerbeblockMin2000 = new JTextField("15");
        _tag7WerbeblockMin2000 = new JTextField("15");
    }
    
    /**
     * Erzeugt die 20:00 Uhr Werbeblock FSK-Boxen.
     */
    private void erzeuge2000WerbeblockFSKBoxen()
    {
        _tag1FSKBox2000 = new JComboBox<FSKFormatierer>();
        _tag2FSKBox2000 = new JComboBox<FSKFormatierer>();
        _tag3FSKBox2000 = new JComboBox<FSKFormatierer>();
        _tag4FSKBox2000 = new JComboBox<FSKFormatierer>();
        _tag5FSKBox2000 = new JComboBox<FSKFormatierer>();
        _tag6FSKBox2000 = new JComboBox<FSKFormatierer>();
        _tag7FSKBox2000 = new JComboBox<FSKFormatierer>();
    }
    
    /**
     * Erzeugt die 22:30 Uhr Film-Auswahllisten.
     */
    private void erzeuge2230FilmBoxen()
    {
        _tag1FilmBox2230 = new JComboBox<FilmFormatierer>();
        _tag2FilmBox2230 = new JComboBox<FilmFormatierer>();
        _tag3FilmBox2230 = new JComboBox<FilmFormatierer>();
        _tag4FilmBox2230 = new JComboBox<FilmFormatierer>();
        _tag5FilmBox2230 = new JComboBox<FilmFormatierer>();
        _tag6FilmBox2230 = new JComboBox<FilmFormatierer>();
        _tag7FilmBox2230 = new JComboBox<FilmFormatierer>();
    }
    
    /**
     * Erzeugt die 22:30 Uhr Werbeblock Minuten-Textfelder.
     */
    private void erzeuge2230WerbeblockTextfelder()
    {
        _tag1WerbeblockMin2230 = new JTextField("15");
        _tag2WerbeblockMin2230 = new JTextField("15");
        _tag3WerbeblockMin2230 = new JTextField("15");
        _tag4WerbeblockMin2230 = new JTextField("15");
        _tag5WerbeblockMin2230 = new JTextField("15");
        _tag6WerbeblockMin2230 = new JTextField("15");
        _tag7WerbeblockMin2230 = new JTextField("15");
    }
    
    /**
     * Erzeugt die 22:30 Uhr Werbeblock FSK-Boxen.
     */
    private void erzeuge2230WerbeblockFSKBoxen()
    {
        _tag1FSKBox2230 = new JComboBox<FSKFormatierer>();
        _tag2FSKBox2230 = new JComboBox<FSKFormatierer>();
        _tag3FSKBox2230 = new JComboBox<FSKFormatierer>();
        _tag4FSKBox2230 = new JComboBox<FSKFormatierer>();
        _tag5FSKBox2230 = new JComboBox<FSKFormatierer>();
        _tag6FSKBox2230 = new JComboBox<FSKFormatierer>();
        _tag7FSKBox2230 = new JComboBox<FSKFormatierer>();
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
