package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.kinosaalauswaehler;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Die GUI des {@link KinosaalAuswaehlWerkzeug}.
 * 
 * @author Jim Martens
 * @version 18.06.2013
 * @copyright 2013 Jim Martens
 */
class KinosaalAuswaehlWerkzeugUI
{
    private JPanel _hauptPanel;
    private JList<KinosaalFormatierer> _kinosaalAuswahlList;
    
    /**
     * Initialisiert die Benutzeroberfläche.
     * 
     */
    public KinosaalAuswaehlWerkzeugUI()
    {
        _hauptPanel = erstellePanel();
    }

    /**
     * Erstellt das Panel.
     */
    private JPanel erstellePanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 4));
        panel.add(new JLabel("Kinosaal:"), BorderLayout.NORTH);
        _kinosaalAuswahlList = new JList<KinosaalFormatierer>();
        _kinosaalAuswahlList.setBorder(BorderFactory.createEmptyBorder(2, 2,
                2, 2));
        _kinosaalAuswahlList
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(_kinosaalAuswahlList), BorderLayout.CENTER);
        return panel;
    }
    
    /**
     * Gibt das Panel zurück, in dem die Widgets angeordnet sind.
     */
    public JPanel getUIPanel()
    {
        return _hauptPanel;
    }
    
    /**
     * Gibt die JList zurück, in der die Kinosäle angezeigt werden.
     */
    public JList<KinosaalFormatierer> getKinosaalAuswahlList()
    {
        return _kinosaalAuswahlList;
    }
}
