package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.subwerkzeuge.vorstellung;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import net.java.dev.designgridlayout.DesignGridLayout;
import net.java.dev.designgridlayout.RowGroup;

/**
 * Die GUI für {@link VorstellungWerkzeug}.
 * 
 * @author Jim Martens
 * @version 19.06.2013
 * @copyright 2013 Jim Martens
 */
class VorstellungWerkzeugUI
{
    private JPanel _hauptPanel;
    private JCheckBox _vorstellungCheckbox;
    private JComboBox<FilmFormatierer> _filmBox;
    private JComboBox<FSKFormatierer> _fskBox;
    private JTextField _werbeblockMinuten;
    private JCheckBox _reinigungszeitCheckbox;
    
    private RowGroup _vorstellungsGruppe;
    
    /**
     * Initialisiert die GUI.
     */
    public VorstellungWerkzeugUI()
    {
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
     * Gibt die Film-Box zurück.
     */
    public JComboBox<FilmFormatierer> getFilmBox()
    {
        return _filmBox;
    }
    
    /**
     * Gibt die FSK-Box zurück.
     */
    public JComboBox<FSKFormatierer> getFSKBox()
    {
        return _fskBox;
    }
    
    /**
     * Gibt das Werbeblock-Minuten-Textfeld zurück.
     */
    public JTextField getWerbeblockMinutenInput()
    {
        return _werbeblockMinuten;
    }
    
    /**
     * Gibt die Vorstellungs-Checkbox zurück.
     */
    public JCheckBox getVorstellungCheckBox()
    {
        return _vorstellungCheckbox;
    }
    
    /**
     * Gibt die Vorstellungs-Gruppe zurück.
     */
    public RowGroup getVorstellungGruppe()
    {
        return _vorstellungsGruppe;
    }
    
    /**
     * Gibt die Reinigungszeit-Checkbox zurück.
     */
    public JCheckBox getReinigungszeitCheckBox()
    {
        return _reinigungszeitCheckbox;
    }
    
    /**
     * Erstellt das Hauptpanel.
     */
    private void erstelleHauptPanel()
    {
        // initialisieren der Exemplarvariablen
        _filmBox = new JComboBox<FilmFormatierer>();
        _fskBox = new JComboBox<FSKFormatierer>();
        _vorstellungCheckbox = new JCheckBox("Vorstellung einplanen?");
        _werbeblockMinuten = new JTextField(new PlainDocument()
        {
            private static final long serialVersionUID = 8223350209013132127L;
            
            @Override
            public void insertString(int offset, String s,
                    AttributeSet attributeSet) throws BadLocationException
            {
                try
                {
                    Integer.parseInt(s);
                }
                catch (NumberFormatException ex)
                {
                    return;
                }
                super.insertString(offset, s, attributeSet);
            }
            
        }, "15", 0);
        _reinigungszeitCheckbox = new JCheckBox("Reinigungszeit einplanen?");
        
        _hauptPanel = new JPanel();
        _vorstellungsGruppe = new RowGroup();
        
        DesignGridLayout gridLayout = new DesignGridLayout(_hauptPanel);
        gridLayout.row().grid().add(_vorstellungCheckbox);
        gridLayout.row().group(_vorstellungsGruppe).grid(new JLabel("Film"))
                .add(_filmBox);
        gridLayout.row().group(_vorstellungsGruppe)
                .grid(new JLabel("Werbeblock"))
                .add(new JLabel("Minuten"), _werbeblockMinuten, _fskBox);
        gridLayout.row().group(_vorstellungsGruppe).grid()
                .add(_reinigungszeitCheckbox);
    }
}
