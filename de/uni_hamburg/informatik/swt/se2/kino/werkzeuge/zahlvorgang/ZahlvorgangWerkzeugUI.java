package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.zahlvorgang;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Die UI des {@link ZahlvorgangWerkzeug}.
 * 
 * @author Jim Martens, Julian Tobergte, Lina Kaine, Cansu Kartoglu
 * @version 27.06.2013
 */
class ZahlvorgangWerkzeugUI
{
    private JDialog _dialog;
    private JPanel _hauptPanel;
    private JPanel _parent;
    private JFrame _appFrame;
    
    private JButton _okButton;
    private JButton _abbrechenButton;
    private JTextField _gegebenEuroTextField;
    private JTextField _gegebenCentTextField;
    private JLabel _zuZahlenLabel;
    private JLabel _rueckgeldLabel;
    
    /**
     * Initialisiert die UI.
     * 
     * @param elternPanel
     * @param appFrame
     * 
     * @require elternPanel != null
     * @require appFrame != null
     */
    public ZahlvorgangWerkzeugUI(JPanel elternPanel, JFrame appFrame)
    {
        assert elternPanel != null : "Vorbedingung verletzt: elternPanel != null";
        assert appFrame != null : "Vorbedingung verletzt: appFrame != null";
        
        _parent = elternPanel;
        _appFrame = appFrame;
        _dialog = erstelleDialog();
    }
    
    /**
     * Gibt das Gegeben-Euro-Textfeld zurück.
     * 
     * @ensure result != null
     */
    public JTextField getGegebenEuroTextField()
    {
        return _gegebenEuroTextField;
    }
    
    /**
     * Gibt das Gegeben-Cent-Textfeld zurück.
     * 
     * @ensure result != null
     */
    public JTextField getGegebenCentTextField()
    {
        return _gegebenCentTextField;
    }
    
    /**
     * Gibt das zuZahlen-Label zurück.
     * 
     * @ensure result != null
     */
    public JLabel getZuZahlenLabel()
    {
        return _zuZahlenLabel;
    }
    
    /**
     * Gibt das Rückgeld-Label zurück.
     * 
     * @ensure result != null
     */
    public JLabel getRueckgeldLabel()
    {
        return _rueckgeldLabel;
    }
    
    /**
     * Gibt den OK-Button zurück.
     */
    public JButton getOKButton()
    {
        return _okButton;
    }
    
    /**
     * Gibt den Abbrechen-Button zurück.
     */
    public JButton getAbbrechenButton()
    {
        return _abbrechenButton;
    }
    
    /**
     * Zeigt den Dialog.
     */
    public void zeigeDialog()
    {
        _gegebenEuroTextField.setText("0");
        _gegebenCentTextField.setText("00");
        _dialog.setVisible(true);
    }
    
    /**
     * Versteckt den Dialog.
     */
    public void versteckeDialog()
    {
        _dialog.dispose();
    }
    
    /**
     * Erstellt ein Dialog und gibt ihn zurück.
     */
    private JDialog erstelleDialog()
    {
        JDialog dialog = new JDialog(_appFrame);
        dialog.setTitle("Zahlungsvorgang");
        dialog.setModalityType(ModalityType.APPLICATION_MODAL);
        dialog.setAlwaysOnTop(true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setResizable(false);
        
        _hauptPanel = new JPanel(new BorderLayout());
        dialog.add(_hauptPanel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        _okButton = new JButton("OK");
        _abbrechenButton = new JButton("Abbrechen");
        buttonPanel.add(_okButton);
        buttonPanel.add(_abbrechenButton);
        _hauptPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        JPanel inhaltPanel = new JPanel(new GridLayout(3, 2));
        inhaltPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        _gegebenEuroTextField = new JTextField();
        _gegebenEuroTextField.setDocument(new PlainDocument()
        {
            private static final long serialVersionUID = 9046848486896215501L;
            
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
                            // wenn die vorhandene Zahl mit einer 0 beginnt, die
                            // einzufügende Ziffer ungleich 0 ist und der Cursor
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
                                // es können keine führenden Nullen eingefügt
                                // werden
                                Toolkit.getDefaultToolkit().beep();
                            }
                            else
                            {
                                
                                // sorgt dafür, dass die größtmögliche
                                // Integerzahl nicht überschritten wird
                                Integer.parseInt(vorhanden + str);
                                // wenn die vorhandene Zahl nicht mit einer 0
                                // beginnt, darf Beliebiges an beliebigen
                                // Stellen
                                // eingefügt werden
                                super.insertString(offs, str, a);
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
        _gegebenEuroTextField.setHorizontalAlignment(JTextField.RIGHT);
        _gegebenEuroTextField.setText("0");
        _gegebenEuroTextField.setColumns(3);
        
        _gegebenCentTextField = new JTextField();
        _gegebenCentTextField.setDocument(new PlainDocument()
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
                        // Überprüfung auf valide Zahl
                        Integer.parseInt(str);
                        String vorhanden = getText(0, getLength());
                        if (offs == 2 && vorhanden.startsWith("0"))
                        {
                            if (vorhanden.endsWith("0") && !str.equals("0"))
                            {
                                // wenn die vorhandene Zahl mit einer 0 beginnt,
                                // mit einer 0 endet,
                                // die einzufügende Ziffer ungleich 0 ist und
                                // der Cursor am rechten Ende des Eingabefelds
                                // steht, wird
                                // die letzte der Nullen mit der einzufügenden
                                // Ziffer ersetzt
                                super.remove(1, 1);
                                super.insertString(1, str, a);
                            }
                            else if (!vorhanden.endsWith("0"))
                            {
                                // wenn die vorhandene Zahl mit einer 0 beginnt,
                                // nicht mit einer 0 endet und
                                // der Cursor am rechten Ende des Eingabefelds
                                // steht, wird
                                // die erste der Nullen gelöscht und die
                                // einzufügende Ziffer von rechts eingefügt
                                super.remove(0, 1);
                                super.insertString(1, str, a);
                            }
                            else
                            {
                                // trifft keine der vorigen Fälle zu, wird ein
                                // Warnton ausgegeben
                                Toolkit.getDefaultToolkit().beep();
                            }
                        }
                        else if (offs == 1 && !str.equals("0")
                                && vorhanden.startsWith("0"))
                        {
                            // wenn die vorhandene Zahl mit einer 0 beginnt, die
                            // einzufügende Ziffer ungleich 0 ist und der Cursor
                            // in der Mitte des Eingabefelds ist, wird die erste
                            // der Nullen durch die einzufügende Ziffer ersetzt
                            super.remove(0, 1);
                            super.insertString(0, str, a);
                        }
                        else if ((getLength() + str.length()) <= 2)
                        {
                            // wenn die jetzige Länge plus die Länge der
                            // einzufügenden Ziffer zusammen kleiner gleich 2
                            // ist, darf die Ziffer eingefügt werden
                            super.insertString(offs, str, a);
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
                    // wenn die Eingabe ungültig ist, wird ein Warnton
                    // ausgegeben
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
                        if (length == 2 && text.length() == 1)
                        {
                            // wenn alle beiden vorhandenen Ziffern gelöscht
                            // werden und durch eine ersetzt werden, wird eine 0
                            // an erster Stelle eingefügt
                            super.remove(offset, length);
                            insertString(offset, text, attrs);
                            super.insertString(0, "0", attrs);
                        }
                        else if (offset == 1 && length == 1
                                && text.length() == 1)
                        {
                            // wenn die hintere Ziffer gelöscht werden soll und
                            // durch die neue Ziffer ersetzt werden soll, dann
                            // wird die neue Ziffer hinten eingefügt
                            super.remove(offset, length);
                            super.insertString(1, text, attrs);
                        }
                        else
                        {
                            // dies verhält sich anders als der Fall direkt über
                            // diesem, da "unsere" insertString Methode ein paar
                            // Nebenwirkungen hat, die für das Einfügen alleine
                            // toll sind, aber für das Ersetzen blöd
                            super.remove(offset, length);
                            insertString(offset, text, attrs);
                        }
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
                if ((getLength() - length) == 1 && !vorhanden.equals("00"))
                {
                    if (offset == 1)
                    {
                        // wenn nach dem Löschen noch eine Ziffer übrig bleibt,
                        // die vorhandene Zahl ungleich 00 ist
                        // und die hintere Ziffer gelöscht werden soll, wird
                        // diese gelöscht, die linke nach rechts gezogen und
                        // links eine 0 eingefügt
                        super.remove(0, getLength());
                        super.insertString(0, "0" + vorhanden.substring(0, 1),
                                null);
                    }
                    else if (offset == 0)
                    {
                        // wenn nach dem Löschen noch eine Ziffer übrig bleibt,
                        // die vorhandene Zahl ungleich 00 ist
                        // und die vordere Ziffer gelöscht werden soll, wird
                        // diese durch eine 0 ersetzt
                        super.remove(0, 1);
                        super.insertString(0, "0", null);
                    }
                    
                }
                else if ((getLength() - length) == 0 && !vorhanden.equals("00"))
                {
                    // wenn nach dem Löschen keine Ziffer mehr übrig bleibt und
                    // die vorhandene Zahl ungleich 00 ist,
                    // wird die Ziffer 0 zweimal eingefügt
                    super.remove(offset, length);
                    super.insertString(offset, "00", null);
                }
                else
                {
                    // trifft keiner der vorigen Fälle zu, wird ein Warnton
                    // ausgegeben
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        });
        
        _gegebenCentTextField.setHorizontalAlignment(JTextField.RIGHT);
        _gegebenCentTextField.setText("00");
        _gegebenCentTextField.setColumns(2);
        
        _zuZahlenLabel = new JLabel("0,00");
        _rueckgeldLabel = new JLabel("0,00");
        
        JPanel gegebenPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        gegebenPanel.add(_gegebenEuroTextField);
        gegebenPanel.add(new JLabel(", "));
        gegebenPanel.add(_gegebenCentTextField);
        gegebenPanel.add(new JLabel("€"));
        
        JPanel zuZahlenPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        zuZahlenPanel.add(_zuZahlenLabel);
        zuZahlenPanel.add(new JLabel("€"));
        
        JPanel rueckgeldPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rueckgeldPanel.add(_rueckgeldLabel);
        rueckgeldPanel.add(new JLabel("€"));
        
        inhaltPanel.add(new JLabel("Gegeben"));
        inhaltPanel.add(gegebenPanel);
        inhaltPanel.add(new JLabel("Zu Zahlen"));
        inhaltPanel.add(zuZahlenPanel);
        inhaltPanel.add(new JLabel("Rückgeld"));
        inhaltPanel.add(rueckgeldPanel);
        
        _hauptPanel.add(inhaltPanel, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(_parent);
        
        return dialog;
    }
}
