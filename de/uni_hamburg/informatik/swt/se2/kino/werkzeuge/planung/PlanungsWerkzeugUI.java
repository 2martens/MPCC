package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.planung;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Die GUI für {@link PlanungsWerkzeug}.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
class PlanungsWerkzeugUI
{
    private JPanel _hauptPanel;
    
    private final JPanel _wochenplanPanel;
    private final JPanel _wochenAuswaehlPanel;
    private final JPanel _kinosaalAuswaehlPanel;
    
    /**
     * Initialisiert die UI.
     * 
     * @param wochenplanPanel
     * @param wochenAuswaehlPanel
     * @param kinosaalAuswaehlPanel
     * 
     * @require wochenplanPanel != null
     * @require wochenAuswaehlPanel != null
     * @require kinosaalAuswaehlPanel != null
     */
    public PlanungsWerkzeugUI(JPanel wochenplanPanel,
            JPanel wochenAuswaehlPanel, JPanel kinosaalAuswaehlPanel)
    {
        assert wochenplanPanel != null : "Vorbedingung verletzt: wochenplanPanel != null";
        assert wochenAuswaehlPanel != null : "Vorbedingung verletzt: wochenAuswaehlPanel != null";
        assert kinosaalAuswaehlPanel != null : "Vorbedingung verletzt: kinosaalAuswaehlPanel != null";
       
        _wochenplanPanel = wochenplanPanel;
        _wochenAuswaehlPanel = wochenAuswaehlPanel;
        _kinosaalAuswaehlPanel = kinosaalAuswaehlPanel;
        
        _hauptPanel = erstelleHauptPanel();
    }
    
    /**
     * Gibt das Haupt-Panel der UI zurück.
     * 
     * @ensure result != null
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }

    /**
     * Erstellt das Panel.
     */
    private JPanel erstelleHauptPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        setNoSize(panel);
        panel.add(erstellePlanPanel(), BorderLayout.CENTER);
        panel.add(_kinosaalAuswaehlPanel, BorderLayout.WEST);
        return panel;
    }
    
    private JPanel erstellePlanPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        setNoSize(panel);
        panel.add(_wochenAuswaehlPanel, BorderLayout.NORTH);
        panel.add(_wochenplanPanel, BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Setzt die Größe einer übergebenen Widget-Komponente auf -1.
     */
    private void setNoSize(Component component)
    {
        component.setPreferredSize(new Dimension(-1, -1));
        component.setSize(-1, -1);
    }
}
