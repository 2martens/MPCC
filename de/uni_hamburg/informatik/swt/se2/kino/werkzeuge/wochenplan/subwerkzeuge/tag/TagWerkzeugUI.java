package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.subwerkzeuge.tag;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.java.dev.designgridlayout.DesignGridLayout;

/**
 * Die GUI für das {@link TagWerkzeug}.
 * 
 * @author Jim Martens
 * @version 20.06.2013
 * @copyright 2013 Jim Martens
 */
class TagWerkzeugUI
{
    private JPanel _hauptPanel;
    private JPanel _1100Panel;
    private JPanel _1500Panel;
    private JPanel _1730Panel;
    private JPanel _2000Panel;
    private JPanel _2230Panel;
    
    /**
     * Initialisiert die UI.
     * 
     * @param panel1100 Das UI-Panel für 11:00 Uhr.
     * @param panel1500 Das UI-Panel für 15:00 Uhr.
     * @param panel1730 Das UI-Panel für 17:30 Uhr.
     * @param panel2000 Das UI-Panel für 20:00 Uhr.
     * @param panel2230 Das UI-Panel für 22:30 Uhr.
     * 
     * @require panel1100 != null
     * @require panel1500 != null
     * @require panel1730 != null
     * @require panel2000 != null
     * @require panel2230 != null
     */
    public TagWerkzeugUI(JPanel panel1100, JPanel panel1500, JPanel panel1730,
            JPanel panel2000, JPanel panel2230)
    {
        assert panel1100 != null : "Vorbedingung verletzt: panel1100 != null";
        assert panel1500 != null : "Vorbedingung verletzt: panel1500 != null";
        assert panel1730 != null : "Vorbedingung verletzt: panel1730 != null";
        assert panel2000 != null : "Vorbedingung verletzt: panel2000 != null";
        assert panel2230 != null : "Vorbedingung verletzt: panel2230 != null";
        
        _1100Panel = panel1100;
        _1500Panel = panel1500;
        _1730Panel = panel1730;
        _2000Panel = panel2000;
        _2230Panel = panel2230;
        erstelleHauptPanel();
    }
    
    /**
     * Gibt das UI-Panel zurück.
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }
    
    /**
     * Erstellt das Hauptpanel.
     */
    private void erstelleHauptPanel()
    {
        _hauptPanel = new JPanel();
        DesignGridLayout gridLayout = new DesignGridLayout(_hauptPanel);
        gridLayout.row().grid(new JLabel("11:00")).add(_1100Panel);
        gridLayout.row().grid(new JLabel("15:00")).add(_1500Panel);
        gridLayout.row().grid(new JLabel("17:30")).add(_1730Panel);
        gridLayout.row().grid(new JLabel("20:00")).add(_2000Panel);
        gridLayout.row().grid(new JLabel("22:30")).add(_2230Panel);
    }
}
