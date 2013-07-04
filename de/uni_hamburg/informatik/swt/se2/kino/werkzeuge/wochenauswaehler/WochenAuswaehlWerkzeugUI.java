package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.wochenauswaehler;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Die GUI des {@link WochenAuswaehlWerkzeug}.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
class WochenAuswaehlWerkzeugUI
{
    private JPanel _hauptPanel;
    private JButton _weiterButton;
    private JButton _zurueckButton;
    private JLabel _wochenLabel;
    
    /**
     * Initialisiert die Benutzeroberfläche.
     * 
     * @param startWocheString
     *            Der String, der zu Anfang als ausgewählte Woche angezeigt
     *            wird.
     */
    public WochenAuswaehlWerkzeugUI(String startWocheString)
    {
        _hauptPanel = erstellePanel(startWocheString);
    }
    
    /**
     * Erstellt das Panel.
     * 
     * @param startWocheString
     *            Der String, der zu Anfang als ausgewählte Woche angezeigt
     *            wird.
     */
    private JPanel erstellePanel(String startWocheString)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        
        _wochenLabel = new JLabel(startWocheString, SwingConstants.CENTER);
        
        panel.add(_wochenLabel, new GridBagConstraints(0, 0, 2, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(
                        2, 0, 2, 0), 0, 0));
        
        Icon zurueckIcon = new ImageIcon(getClass().getResource(
                "/images/go-previous.png"));
        _zurueckButton = new JButton(zurueckIcon);
        panel.add(_zurueckButton, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 0, 2, 5), 0, 0));
        
        Icon weiterIcon = new ImageIcon(getClass().getResource(
                "/images/go-next.png"));
        _weiterButton = new JButton(weiterIcon);
        panel.add(_weiterButton, new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(2, 5, 2, 0), 0, 0));
        
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        return panel;
    }
    
    /**
     * Gibt den Button für die Auswahl der vorherigen Woche zurück.
     */
    public JButton getZurueckButton()
    {
        return _zurueckButton;
    }
    
    /**
     * Gibt den Button für die Auswahl der nachfolgenden Woche zurück.
     */
    public JButton getWeiterButton()
    {
        return _weiterButton;
    }
    
    /**
     * Gibt das Label zurück, in dem die derzeit ausgewählte Woche angezeigt
     * wird.
     */
    public JLabel getDatumLabel()
    {
        return _wochenLabel;
    }
    
    /**
     * Gibt das Panel zurück, in dem die Widgets angeordnet sind.
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }
}
