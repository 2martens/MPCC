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
    
    private JLabel _film;
    private JLabel _minuten;
    private JLabel _werbeblock;
    private RowGroup _vorstellungGruppe;
    private int _hideCount;
    
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
     * Gibt die Reinigungszeit-Checkbox zurück.
     */
    public JCheckBox getReinigungszeitCheckBox()
    {
        return _reinigungszeitCheckbox;
    }
    
    /**
     * Zeigt die Vorstellungsdetails.
     */
    public void zeigeVorstellungsdetails()
    {
        for (int i = 0; i < _hideCount; i++)
        {
            _vorstellungGruppe.show();
        }
        _hideCount = 0;
    }
    
    /**
     * Versteckt die Vorstellungsdetails.
     */
    public void versteckeVorstellungsdetails()
    {
        _vorstellungGruppe.hide();
        _hideCount++;
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
        _film = new JLabel("Film");
        _minuten = new JLabel("Minuten");
        _werbeblock = new JLabel("Werbeblock");
        
        _werbeblockMinuten.setDocument(new PlainDocument()
        {
            private static final long serialVersionUID = -4365137424980228216L;
            
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException
            {
                try
                {
                    if (!str.isEmpty())
                    {
                        // Überprüfen, ob die Eingabe eine gültige Zahl ist
                        Integer.parseInt(str);
                        String vorhanden = getText(0, getLength());
                        if (offs == 1 && vorhanden.startsWith("0")
                                && !str.equals("0"))
                        {
                            // wenn die vorhandene Zahl mit einer 0 beginnt,
                            // die
                            // einzufügende Ziffer ungleich 0 ist und der
                            // Cursor
                            // ganz rechts steht, wird die
                            // vorhandene erste 0 entfernt und durch die
                            // einzufügende Ziffer ersetzt
                            super.remove(0, 1);
                            super.insertString(0, str, a);
                        }
                        else if (!vorhanden.startsWith("0"))
                        {
                            if (offs == 0 && str.equals("0")
                                    && !vorhanden.isEmpty())
                            {
                                // es können keine führenden Nullen
                                // eingefügt
                                // werden
                                Toolkit.getDefaultToolkit().beep();
                            }
                            else if ((getLength() + str.length()) <= 2)
                            {
                                // wenn die vorhandene Zahl nicht mit einer
                                // 0
                                // beginnt und die fertige Zahl insgesamt nicht
                                // mehr als zwei Ziffern hat, darf Beliebiges an
                                // beliebigen
                                // Stellen
                                // eingefügt werden
                                super.insertString(offs, str, a);
                            }
                            else
                            {
                                // trifft keine der vorigen Fälle zu, wird ein
                                // Warnton ausgegeben
                                Toolkit.getDefaultToolkit().beep();
                            }
                        }
                        else if ((getLength() + str.length()) <= 2)
                        {
                            if (!str.equals("0"))
                            {
                                // wenn die jetzige Länge plus die Länge der
                                // einzufügenden Ziffer zusammen kleiner gleich
                                // 2
                                // ist und die einzufügende Ziffer keine 0 ist,
                                // darf die Ziffer eingefügt werden
                                super.insertString(offs, str, a);
                            }
                            else
                            {
                                // trifft keine der vorigen Fälle zu, wird ein
                                // Warnton ausgegeben
                                Toolkit.getDefaultToolkit().beep();
                            }
                        }
                        else
                        {
                            // trifft keine der vorigen Fälle zu, wird ein
                            // Warnton ausgegeben
                            Toolkit.getDefaultToolkit().beep();
                        }
                    }
                }
                catch (NumberFormatException nfe)
                {
                    // wenn die Eingabe eine ungültige Zahl ist, wird ein
                    // Warnton ausgegeben
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            
            @Override
            public void replace(int offset, int length, String text,
                    AttributeSet attrs) throws BadLocationException
            {
                try
                {
                    if (!text.isEmpty())
                    {
                        // Überprüfung auf valide Zahl
                        Integer.parseInt(text);
                        // wenn dem so ist (keine Exception geworfen) wird der
                        // zu ersetzende Text gelöscht und durch den neuen Text
                        // ersetzt
                        super.remove(offset, length);
                        insertString(offset, text, attrs);
                    }
                    else
                    {
                        // wenn vorhandene Zahl gegen leere Eingabe ersetzt
                        // werden soll, wird ein Warnton ausgegeben
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
                catch (NumberFormatException nfe)
                {
                    // wenn die vorhandene Zahl durch eine ungültige Eingabe
                    // ersetzt werden soll, wird ein Warnton ausgegeben
                    Toolkit.getDefaultToolkit().beep();
                }
            }
            
            @Override
            public void remove(int offset, int length)
                    throws BadLocationException
            {
                String vorhanden = getText(0, getLength());
                if ((getLength() - length) > 0)
                {
                    // nur wenn die Länge der jetzigen Zahl abzüglich der zu
                    // löschenden Ziffern größer 0 ist, darf bedenkenlos
                    // gelöscht werden
                    super.remove(offset, length);
                }
                else if ((getLength() - length) == 0 && !vorhanden.equals("0"))
                {
                    // ist die verbleibende Zahl nach dem Löschen von der Länge
                    // 0 und ist die vorhandene Zahl ungleich 0, wird eine 0
                    // eingefügt
                    super.remove(offset, length);
                    super.insertString(offset, "0", null);
                }
                else
                {
                    // soll mehr gelöscht werden als vorhanden ist, wird ein
                    // Warnton ausgegeben
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        _werbeblockMinuten.setText("0");
        _werbeblockMinuten.setColumns(2);
        _werbeblockMinuten.setHorizontalAlignment(JTextField.RIGHT);
        _reinigungszeitCheckbox = new JCheckBox("Reinigungszeit einplanen?");
        
        _vorstellungGruppe = new RowGroup();
        
        _hauptPanel = new JPanel();
        DesignGridLayout gridLayout = new DesignGridLayout(_hauptPanel);
        gridLayout.row().grid().add(_vorstellungCheckbox);
        gridLayout.row().group(_vorstellungGruppe).grid(_film).add(_filmBox);
        gridLayout.row().group(_vorstellungGruppe).grid(_werbeblock)
                .add(_minuten, _werbeblockMinuten, _fskBox);
        gridLayout.row().group(_vorstellungGruppe).grid()
                .add(_reinigungszeitCheckbox);
    }
}
