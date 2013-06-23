package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenplan.subwerkzeuge.vorstellung;

import java.awt.Toolkit;

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
        _werbeblockMinuten = new JTextField();
        _werbeblockMinuten.setDocument(new PlainDocument()
        {
            private static final long serialVersionUID = 5900453040208923245L;
            
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException
            {
                try
                {
                    if (!str.isEmpty())
                    {
                        Integer.parseInt(str);
                        if ((getLength() + str.length()) <= 2)
                        {
                            super.insertString(offs, str, a);
                            return;
                        }
                    }
                }
                catch (NumberFormatException nfe)
                {
                    Toolkit.getDefaultToolkit().beep();
                }
                Toolkit.getDefaultToolkit().beep();
            }
            
            @Override
            public void replace(int offset, int length, String text,
                    AttributeSet attrs) throws BadLocationException
            {
                try
                {
                    if (!text.isEmpty())
                    {
                        Integer.parseInt(text);
                        super.remove(offset, length);
                        insertString(offset, text, attrs);
                        return;
                    }
                }
                catch (NumberFormatException nfe)
                {
                    Toolkit.getDefaultToolkit().beep();
                }
                Toolkit.getDefaultToolkit().beep();
            }
            
            @Override
            public void remove(int offset, int length)
                    throws BadLocationException
            {
                if ((this.getLength() - length) > 0)
                {
                    super.remove(offset, length);
                }
                else if ((this.getLength() - length) == 0)
                {
                    super.remove(offset, length);
                    super.insertString(offset, "0", null);
                }
                else
                {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        _werbeblockMinuten.setText("0");
        
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
